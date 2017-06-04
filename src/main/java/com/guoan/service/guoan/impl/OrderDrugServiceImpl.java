package com.guoan.service.guoan.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
import org.springframework.transaction.annotation.Transactional;

import com.guoan.dao.shequ.AppDeviceInfoMapper;
import com.guoan.dao.shequ.AppUserAddressMapper;
import com.guoan.dao.shequ.MessageMapper;
import com.guoan.dao.shequ.OrderDrugInfoMapper;
import com.guoan.dao.shequ.OrderDrugMapper;
import com.guoan.dao.shequ.OrderMapper;
import com.guoan.dao.shequ.SysCouponMapper;
import com.guoan.dao.shequ.SysUserMapper;
import com.guoan.dao.work.WorkUserInfoMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.AppUserAddressFull;
import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.entity.shequ.bo.Message;
import com.guoan.entity.shequ.bo.Order;
import com.guoan.entity.shequ.bo.OrderDrug;
import com.guoan.entity.shequ.bo.OrderDrugInfo;
import com.guoan.entity.shequ.bo.OrderDrugInfoCriteria;
import com.guoan.entity.shequ.bo.SysCoupon;
import com.guoan.entity.shequ.bo.SysUser;
import com.guoan.entity.shequ.param.OrderParam;
import com.guoan.entity.work.bo.WorkUserInfo;
import com.guoan.service.guoan.AppSystemService;
import com.guoan.service.guoan.OrderDrugService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.DateUtil;
import com.guoan.utils.OrderConst;
import com.guoan.utils.StringUtils;
import com.guoan.utils.SysConstants;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.order.GenerateOrderSN;
import com.guoan.utils.push.PushUtils;
import com.guoan.utils.redis.RedisContants;

/**
 * @Description: 处方药订单
 * @Create Date: 2015-09-15
 * 
 */
@Service
public class OrderDrugServiceImpl extends OrderService implements OrderDrugService {
	
	//lo4j 打印该类日志
	private static final Logger logger = LoggerFactory.getLogger(OrderDrugServiceImpl.class);

	//app客户端的用户对象
	@Autowired
	private AppUserAddressMapper addrMapper;

	@Autowired
	private AppDeviceInfoMapper appDeviceInfoMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private OrderDrugMapper drugsMapper;

	@Autowired
	private OrderDrugInfoMapper flowStatusMapper;
	
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
	* @MethodName: addOrder
	* @Description: 下单
	* @Version: V1.5
	* @Create Date: 2015-09-14
	* @Parameters: jsonString
	* @Return: result
	*/
	@Override
	@Transactional
	public Result addOrder(String jsonString) {
		// TODO Auto-generated method stub
		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		//json的数据格式{"code":"200","param":"{"telephone":"15149006102"}","password":"32位加密的密码"}
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		//令牌机制
		String token = requestJsonObject.getString("token");
		
		/*取得客户端用户对象*///取得客户端的用户对象，从用户对象中获取用户的id userid
		//
		AppUserInfo user = this.redisService.getRedisUserObject(RedisContants.USER_APP, token, AppUserInfo.class);
		
		if(null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		// 控制java.util.date
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(OrderConst.DATE_FORMAT));
		//将他传过来的参数解析成javaBean
		OrderParam drugsParam = (OrderParam) JSONObject.toBean(paramsObject, OrderParam.class);
	    //创建药品订单对象
		OrderDrug drugsOrder = new OrderDrug();
	    //获得 从客户端获取过来的用户对对象，从这个对象中获得该对象的id'
	    String userId = user.getUserId();
	    //开始拼接订单的id 使用UUID
	    String id = UUID.randomUUID().toString().replace("-","");
	    //drugsOrder继承Order,所有订单都要继承订单基类Order
	    drugsOrder.setOrderId(id);
	    drugsOrder.setDrugsId(id);
		//从jsonString中获取订单的地址id
	    String addrId = drugsParam.getOrderAddressId();
	    //在调用这个mapper看看查询的是什么数据
	    AppUserAddressFull address = this.addrMapper.selectById(addrId);
		
	    //将从数据库查询出来的数据存在drugs对象中，
	    drugsOrder.setOrderAddress(address.getAddress());
	    drugsOrder.setOrderContact(address.getContact());
	    drugsOrder.setOrderTelephone(address.getTelephone());
		Date creatTime = new Date();
		drugsOrder.setCreateTime(creatTime);
		//paramObject是从app传过来的数据-->drugsParam(是将paramObject(json)转换成javaBean对象)
		drugsOrder.setOrderType(drugsParam.getOrderType());
		drugsOrder.setOrderRemark(drugsParam.getOrderRemark());
		drugsOrder.setUserId(userId);
		
		
		Date beginTime = drugsParam.getAppointmentBeginTime();
		Date endTime = DateUtils.addMinutes(beginTime, 30);
		drugsOrder.setAppointmentBeginTime(beginTime);
		drugsOrder.setAppointmentEndTime(endTime);
		drugsOrder.setOrderStatus(OrderConst.ORDER_STATUS_NORMAL);
		drugsOrder.setServiceType(OrderConst.SERVICE_TYPE_DRUG);
		drugsOrder.setOrderSn(GenerateOrderSN.nextCode());
		drugsOrder.setOrderReduce(drugsParam.getOrderReduce());
		drugsOrder.setIsEvaluation(0);
		drugsOrder.setIsCancel(0);
		
		/*初始化订单流程状态*/
		String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.DRUG_STATUS_KEY_PREFIX + OrderConst.STATUS_START_INT);
		drugsOrder.setFlowStatus(OrderConst.STATUS_START_INT);
		drugsOrder.setFlowStatusName(flowStatusStr);
		
		/*根据地址,选择服务人员*/   
		List<SysUser> sysUserList = this.sysUserMapper.getSysUserByArea(address.getAreaId());
		if ( sysUserList != null && sysUserList.size() > 0) {
			SysUser server = sysUserList.get(0);
			drugsOrder.setServiceContact(server.getName());
			drugsOrder.setServiceTelephone(server.getMobilephone());
			drugsOrder.setServiceUserId(String.valueOf(server.getId()));
			drugsOrder.setStoreId(server.getStoreId());
		} else {
			Map<String, Object> storeMap = this.sysService.getStoreByArea(drugsParam.getAreaId());
			drugsOrder.setStoreId((Long) storeMap.get("id"));
		}
		
		Order order = new Order();
		BeanUtils.copyProperties(drugsOrder, order);

		/*插入流程状态表*/
		OrderDrugInfo flowStatus = this.getFlowStatus(id, user, OrderConst.STATUS_START_INT, flowStatusStr);
		this.flowStatusMapper.insertSelective(flowStatus);

		/*修改优惠券的状态为锁定*/
		// update by zhaotong 2015/05/29 09:50
        
		drugsOrder.setOrderReduce(null);
		if (StringUtils.isNotBlank(drugsParam.getCouponId())) {
			SysCoupon sysCoupon = this.couponMapper.selectByPrimaryKey(drugsParam.getCouponId());
			if(null != sysCoupon){
				if(OrderConst.COUPON_LOCK.equals(sysCoupon.getStatus())){
					result.setCode(CodeEnum.CouponIsLock.getValue());
					result.setMessage(CodeEnum.CouponIsLock.getDescription());
					return result;
				}else if(DateUtil.compareDate(new Date(System.currentTimeMillis()/86400000*86400000-(23- Calendar.ZONE_OFFSET)*3600000), sysCoupon.getExpirationDate())) {
					result.setCode(CodeEnum.CouponIsOverdue.getValue());
					result.setMessage(CodeEnum.CouponIsOverdue.getDescription());
					return result;
				}else { // 可以使用
					order.setCouponId(drugsParam.getCouponId());
					sysCoupon.setStatus(OrderConst.COUPON_LOCK);
					this.couponMapper.updateByPrimaryKeySelective(sysCoupon);
					order.setOrderReduce(sysCoupon.getAmount());
					drugsOrder.setOrderReduce(sysCoupon.getAmount());
				}
			}else {
				result.setCode(CodeEnum.CouponNone.getValue());
				result.setMessage(CodeEnum.CouponNone.getDescription());
				return result;
			}
		}
		drugsOrder.setOrderPaid(null);

		
		// 插入 调整位置 update by zhaotong
				this.orderMapper.insertSelective(order);
				this.drugsMapper.insertSelective(drugsOrder);

				// 处方药下单成功
				Message message = new Message();
				message.setMessageId(UUID.randomUUID().toString().replace("-", ""));
				message.setOrderId(order.getOrderId());
				message.setServiceType("drug");
				message.setTitle("处方药下单成功");
				message.setContent("小主~ 国安侠已接下您的处方药订单传令~ 尽快与您联系！");
				message.setUserId(user.getUserId());
				message.setCreateTime(new Date());
				messageMapper.insert(message);

				// 推送消息
				String title_android = "药品下单成功";
				String content_android = "您有一条药品订单！";
				// String content_ios = "洗衣下单成功：您有一条洗衣订单！";
				//接收消息的设备 Token
//				AppDeviceInfo appDeviceInfo = appDeviceInfoMapper.selectByPrimaryKey(user.getUserId());
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
                
				//继承OrderService得到jsonConfig
				JsonConfig jsonConfig = super.getJsonConfig();
				
				result.setCode(CodeEnum.success.getValue());
				result.setMessage(CodeEnum.success.getDescription());

				/*时间日期格式化*/
				JSONObject drugsOrderJson = JSONObject.fromObject(drugsOrder, jsonConfig);

				result.setData(drugsOrderJson);
		return result;
	}

	/**  
	* @MethodName: getOrderList
	* @Description: 获得订单列表
	* @Version: V_1.5
	* @Create Date: 2015-09-06
	* @Parameters: jsonString
	* @Return: result
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
		List<OrderDrug> drugsOrderList = this.drugsMapper.getOrderList(record);

		if(ListUtils.isEmpty(drugsOrderList)){
			result.setCode(CodeEnum.OrderGetNull.getValue());
			result.setMessage(CodeEnum.OrderGetNull.getDescription());
			return result;
		}
		//this.drugsMapper.getOrderList(record);

		int count = this.drugsMapper.getOrderListCount(record);
		int pageCount = (int) Math.ceil((double) count / lineNum);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pageNum", pageNum);
		resultMap.put("pageCount", pageCount);
		resultMap.put("orderList", drugsOrderList);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		/*时间日期格式化*/
		JsonConfig jsonConfig = super.getJsonConfig();
		JSONObject resultMapJson = JSONObject.fromObject(resultMap, jsonConfig);

		result.setData(resultMapJson);

		return result;
	}

	/**  
	* @MethodName: getOrder
	* @Description: 获得某个订单
	* @Version: V_1.5
	* @Create Date: 2015-09-06
	* @Parameters: jsonString
	* @Return: result
	*/
	@Override
	public Result getOrder(String jsonString) {
		
		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		String orderId = paramsObject.getString("id");

		/*从redis里获得订单*/
		String orderKey = super.getOrderRedisKey(orderId);
		OrderDrug order = (OrderDrug) this.redisService.getObject(orderKey, OrderDrug.class);

		if (order == null || StringUtils.isBlank(order.getOrderId())) {

			order = this.drugsMapper.selectByDrugId(orderId);

		}

		if(null == order){
			result.setCode(CodeEnum.OrderGetNull.getValue());
			result.setMessage(CodeEnum.OrderGetNull.getDescription());
			return result;
		}

		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("order", order);

		List<Object> flowStatusJsonList = this.redisService.getList(super.getOrderFlowRedisKey(orderId));

		if (flowStatusJsonList == null || flowStatusJsonList.isEmpty()) {

			OrderDrugInfoCriteria flowStatusCriteria = new OrderDrugInfoCriteria();
			flowStatusCriteria.createCriteria().andOrderIdEqualTo(orderId);
			flowStatusCriteria.setOrderByClause("create_time desc");
			List<OrderDrugInfo> flowStatusList = this.flowStatusMapper.selectByExample(flowStatusCriteria);
			orderMap.put("flowStatus", flowStatusList);

		} else {
			orderMap.put("flowStatus", flowStatusJsonList);
		}

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		/*时间日期格式化*/
		JsonConfig jsonConfig = super.getJsonConfig();
		JSONObject drugsOrderJson = JSONObject.fromObject(orderMap, jsonConfig);

		result.setData(drugsOrderJson);

		return result;
	}
	
	
	/**  
	* @MethodName: getFlowStatus
	* @Description: 返回流程状态实例
	* @Version: V_1.5
	* @Create Date: 2015-09-06
	* @Parameters: orderId,AppUserInfo,orderStatus,orderStatusStr
	* @Return: OrderDrugsInfo
	*/
	private OrderDrugInfo getFlowStatus(String orderId, AppUserInfo user, Integer orderStatus, String orderStatusStr) {

		OrderDrugInfo flowStatus = new OrderDrugInfo();
		flowStatus.setOrderId(orderId);
		flowStatus.setCreateTime(new Date());
		flowStatus.setFlowStatus(orderStatus);
		flowStatus.setUserId(user.getUserId());
		flowStatus.setFlowStatusName(orderStatusStr);
		flowStatus.setUserType(OrderConst.USER);
		
		return flowStatus;



	}
	

}
