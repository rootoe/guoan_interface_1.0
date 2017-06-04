package com.guoan.service.guoan.impl;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.guoan.dao.shequ.*;
import com.guoan.dao.work.WorkUserInfoMapper;
import com.guoan.entity.shequ.bo.*;
import com.guoan.entity.work.bo.WorkUserInfo;
import com.guoan.utils.DateUtil;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.push.PushUtils;
import com.guoan.utils.redis.RedisContants;
import com.tencent.xinge.XingeApp;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.param.OrderParam;
import com.guoan.service.guoan.AppSystemService;
import com.guoan.service.guoan.OrderExpressService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.OrderConst;
import com.guoan.utils.StringUtils;
import com.guoan.utils.SysConstants;
import com.guoan.utils.order.GenerateOrderSN;

@Service
public class OrderExpressServiceImpl extends OrderService implements OrderExpressService {

	private static final Logger logger = LoggerFactory.getLogger(OrderExpressServiceImpl.class);

	@Autowired
	private AppUserAddressMapper addrMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private OrderExpressInfoMapper flowStatusMapper;

	@Autowired
	private OrderExpressMapper expressMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private SysCouponMapper couponMapper;

	@Autowired
	private RedisService redisService;

	@Autowired
	private AppSystemService sysService;

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private WorkUserInfoMapper workUserInfoMapper;

	/**
	 * 下单
	 * @param jsonString
	 */
	@Override
	public Result addOrder(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		String token = requestJsonObject.getString("token");

		/*取得用户对象*/
		AppUserInfo user = this.redisService.getRedisUserObject(RedisContants.USER_APP, token, AppUserInfo.class);

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		// 控制java.util.date
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(OrderConst.DATE_FORMAT));

		OrderParam orderParam = (OrderParam) JSONObject.toBean(paramsObject, OrderParam.class);
		OrderExpress expressOrder = new OrderExpress();

		String id = UUID.randomUUID().toString().replace("-", "");
		expressOrder.setOrderId(id);
		expressOrder.setExpressId(id);

		/*获得地址信息*/
		String addrId = orderParam.getOrderAddressId();
		AppUserAddressFull address = this.addrMapper.selectById(addrId);

		expressOrder.setOrderAddress(address.getAddress());
		expressOrder.setOrderContact(address.getContact());
		expressOrder.setOrderTelephone(address.getTelephone());
		expressOrder.setCreateTime(new Date());
		expressOrder.setOrderType(orderParam.getOrderType());
		expressOrder.setOrderRemark(orderParam.getOrderRemark());
		expressOrder.setUserId(user.getUserId());
		Date beginTime = orderParam.getAppointmentBeginTime();
		Date endTime = DateUtils.addMinutes(beginTime, 30);
		expressOrder.setAppointmentBeginTime(beginTime);
		expressOrder.setAppointmentEndTime(endTime);
		expressOrder.setOrderStatus(OrderConst.ORDER_STATUS_NORMAL);
		expressOrder.setServiceType(OrderConst.SERVICE_TYPE_EXPRESS);
		expressOrder.setCouponId(orderParam.getCouponId());
		expressOrder.setOrderReduce(orderParam.getOrderReduce());
		expressOrder.setIsEvaluation(0);
		expressOrder.setIsCancel(0);
		
		String dispatch = orderParam.getDispatcherType();
		if( StringUtils.isBlank(dispatch) ){
			dispatch = OrderConst.EXPRESS_SEND;
		}
		expressOrder.setDispatcherType(dispatch);
		expressOrder.setOrderSn(GenerateOrderSN.nextCode());

		/*初始化订单流程状态*/
		String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.EXPRESS_STATUS_KEY_PREFIX + OrderConst.STATUS_START_INT);
		expressOrder.setFlowStatus(OrderConst.STATUS_START_INT);
		expressOrder.setFlowStatusName(flowStatusStr);

		/*根据地址,选择服务人员*/
		List<SysUser> sysUserList = this.sysUserMapper.getSysUserByArea(address.getAreaId());
		if (sysUserList != null && sysUserList.size() > 0) {
			SysUser server = sysUserList.get(0);
			expressOrder.setServiceContact(server.getName());
			expressOrder.setServiceTelephone(server.getMobilephone());
			expressOrder.setServiceUserId(String.valueOf(server.getId()));
			expressOrder.setStoreId(server.getStoreId());
		} else {
			Map<String, Object> storeMap = this.sysService.getStoreByArea(orderParam.getAreaId());
			expressOrder.setStoreId((Long) storeMap.get("id"));
		}

		Order order = new Order();
		BeanUtils.copyProperties(expressOrder, order);

		/*插入流程状态表*/
		OrderExpressInfo flowStatus = this.getFlowStatus(id, user, OrderConst.STATUS_START_INT, flowStatusStr);
		this.flowStatusMapper.insertSelective(flowStatus);
		
		/*修改优惠券的状态为锁定*/
		// update by zhaotong 2015/05/29 09:50
		expressOrder.setOrderReduce(null);
		if (StringUtils.isNotBlank(orderParam.getCouponId())) {
			SysCoupon sysCoupon = this.couponMapper.selectByPrimaryKey(orderParam.getCouponId());
			if(null != sysCoupon){
				if(OrderConst.COUPON_LOCK.equals(sysCoupon.getStatus())){
					result.setCode(CodeEnum.CouponIsLock.getValue());
					result.setMessage(CodeEnum.CouponIsLock.getDescription());
					return result;
				}else if(DateUtil.compareDate(new Date(System.currentTimeMillis()/86400000*86400000-(23- Calendar.ZONE_OFFSET)*3600000), sysCoupon.getExpirationDate())) {
					result.setCode(CodeEnum.CouponIsOverdue.getValue());
					result.setMessage(CodeEnum.CouponIsOverdue.getDescription());
					return result;
				}else {
					sysCoupon.setStatus(OrderConst.COUPON_LOCK);
					this.couponMapper.updateByPrimaryKeySelective(sysCoupon);
					order.setOrderReduce(sysCoupon.getAmount());
					expressOrder.setOrderReduce(sysCoupon.getAmount());
				}
			}else {
				result.setCode(CodeEnum.CouponNone.getValue());
				result.setMessage(CodeEnum.CouponNone.getDescription());
				return result;
			}
		}
		expressOrder.setOrderPaid(null);
		// 插入 调整位置 update by zhaotong
		this.orderMapper.insertSelective(order);
		this.expressMapper.insertSelective(expressOrder);

		// 快递下单成功
		Message message = new Message();
		message.setMessageId(UUID.randomUUID().toString().replace("-", ""));
		message.setOrderId(order.getOrderId());
		message.setServiceType("express");
		message.setTitle("发快递下单成功");
		message.setContent("小主~国安侠已接到您的发件指令，尽快与您联系上门取件！");
		message.setUserId(user.getUserId());
		message.setCreateTime(new Date());
		messageMapper.insert(message);

		// 推送消息
		String title_android = "快递下单成功";
		String content_android = "您有一条快递订单！";
		if(StringUtils.isNotEmpty(order.getServiceUserId())){
			WorkUserInfo workUserInfo = workUserInfoMapper.selectByPrimaryKey(Long.valueOf(order.getServiceUserId()));
			String deviceToken = workUserInfo.getRemark();
			if(StringUtils.isNotEmpty(deviceToken)){

				Callable<org.json.JSONObject> callable = new Callable<org.json.JSONObject>() {
					public org.json.JSONObject call() throws Exception {
						return PushUtils.push(title_android, content_android, deviceToken);
					}
				};
				FutureTask<org.json.JSONObject> future = new FutureTask<org.json.JSONObject>(callable);
				new Thread(future).start();
				try {
					future.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			}
		}

		JsonConfig jsonConfig = super.getJsonConfig();
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		/*数据格式化*/
		JSONObject orderJson = JSONObject.fromObject(expressOrder, jsonConfig);

		result.setData(orderJson);

		return result;
	}

	/**
	 * 取得订单列表
	 * 
	 * @author gaochao
	 * @param jsonString
	 */
	@Override
	public Result getOrderList(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		String token = requestJsonObject.getString("token");

		OrderParam orderParam = (OrderParam) JSONObject.toBean(paramsObject, OrderParam.class);

		/*取得用户对象*/
		AppUserInfo user = this.redisService.getRedisUserObject(RedisContants.USER_APP, token, AppUserInfo.class);

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		Map<String, Object> record = new HashMap<String, Object>();
		record.put("orderStatus", 1);
		record.put("userId", user.getUserId());

		/*流程状态作为检索条件*/
		List<Integer> flowStatusList = orderParam.getFlowStatusList();
		if (flowStatusList != null && flowStatusList.size() > 0) {
			record.put("flowStatusList", flowStatusList);
		}

		int pageNum = orderParam.getPageNum();
		int lineNum = orderParam.getLineNum();
		record.put("limitStart", (pageNum - 1) * lineNum);
		record.put("limitEnd", pageNum * lineNum);

		record.put("orderByClause", "create_time desc");

		/*取得订单列表*/
		List<OrderExpress> expressOrderList = this.expressMapper.getOrderList(record);

		if(ListUtils.isEmpty(expressOrderList)){
			result.setCode(CodeEnum.OrderGetNull.getValue());
			result.setMessage(CodeEnum.OrderGetNull.getDescription());
			return result;
		}

		int count = this.expressMapper.getOrderListCount(record);
		int pageCount = (int) Math.ceil((double) count / lineNum);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pageNum", pageNum);
		resultMap.put("pageCount", pageCount);
		resultMap.put("orderList", expressOrderList);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		/*数据格式化*/
		JsonConfig jsonConfig = super.getJsonConfig();
		JSONObject resultMapJson = JSONObject.fromObject(resultMap, jsonConfig);

		result.setData(resultMapJson);

		return result;
	}

	/**
	 * 取得某个订单
	 * 
	 * @author gaochao
	 * @param jsonString
	 */
	@Override
	public Result getOrder(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		String orderId = paramsObject.getString("id");

		/*从redis里获得订单*/
		String orderKey = super.getOrderRedisKey(orderId);
		OrderExpress order = (OrderExpress) this.redisService.getObject(orderKey, OrderExpress.class);

		if (order == null || StringUtils.isBlank(order.getOrderId()) ) {
			
			order = this.expressMapper.selectByExpressId(orderId);

			/*更新redis*/
//			this.redisService.setObject(orderKey, order);
//			this.redisService.setExpire(orderKey, OrderConst.EXPIRE_TIME);
		}

		if(null == order){
			result.setCode(CodeEnum.OrderGetNull.getValue());
			result.setMessage(CodeEnum.OrderGetNull.getDescription());
			return result;
		}

		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("order", order);

		List<Object> flowStatusJsonList = this.redisService.getList(super
				.getOrderFlowRedisKey(orderId));

		if (flowStatusJsonList == null || flowStatusJsonList.isEmpty()) {

			OrderExpressInfoCriteria flowStatusCriteria = new OrderExpressInfoCriteria();
			flowStatusCriteria.createCriteria().andOrderIdEqualTo(orderId);
			flowStatusCriteria.setOrderByClause("create_time desc");
			List<OrderExpressInfo> flowStatusList = this.flowStatusMapper
					.selectByExample(flowStatusCriteria);
			orderMap.put("flowStatus", flowStatusList);
			
		} else {
			orderMap.put("flowStatus", flowStatusJsonList);
		}

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		/*数据格式化*/
		JsonConfig jsonConfig = super.getJsonConfig();
		JSONObject orderJson = JSONObject.fromObject(orderMap, jsonConfig);

		result.setData(orderJson);

		return result;
	}

	/**
	 * 返回流程状态实例
	 * 
	 * @param orderId
	 * @param user
	 * @param orderStatus
	 * @param orderStatusStr
	 * @return
	 */
	private OrderExpressInfo getFlowStatus(String orderId, AppUserInfo user, Integer orderStatus, String orderStatusStr) {

		OrderExpressInfo flowStatus = new OrderExpressInfo();
		flowStatus.setOrderId(orderId);
		flowStatus.setCreateTime(new Date());
		flowStatus.setFlowStatus(orderStatus);
		flowStatus.setUserId(user.getUserId());
		flowStatus.setFlowStatusName(orderStatusStr);
		flowStatus.setUserType(OrderConst.USER);
		return flowStatus;
	}

}
