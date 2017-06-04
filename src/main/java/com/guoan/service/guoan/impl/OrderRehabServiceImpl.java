package com.guoan.service.guoan.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.guoan.utils.redis.RedisContants;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoan.dao.shequ.AppUserAddressMapper;
import com.guoan.dao.shequ.OrderMapper;
import com.guoan.dao.shequ.OrderRehabInfoMapper;
import com.guoan.dao.shequ.OrderRehabMapper;
import com.guoan.dao.shequ.SysServiceProviderMapper;
import com.guoan.dao.shequ.SysUserMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.AppUserAddressFull;
import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.entity.shequ.bo.Order;
import com.guoan.entity.shequ.bo.OrderRehab;
import com.guoan.entity.shequ.bo.OrderRehabInfo;
import com.guoan.entity.shequ.bo.OrderRehabInfoCriteria;
import com.guoan.entity.shequ.bo.SysUser;
import com.guoan.entity.shequ.param.OrderParam;
import com.guoan.service.guoan.OrderRehabService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.OrderConst;
import com.guoan.utils.StringUtils;
import com.guoan.utils.SysConstants;
import com.guoan.utils.order.GenerateOrderSN;

@Service
public class OrderRehabServiceImpl extends OrderService implements OrderRehabService {

	@Autowired
	private AppUserAddressMapper addrMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private OrderRehabInfoMapper flowStatusMapper;

	@Autowired
	private OrderRehabMapper rehabMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private SysServiceProviderMapper providerMapper;

	@Autowired
	private RedisService redisService;

	/**
	 * 下单
	 * 
	 * @author gaochao
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
		OrderRehab rehabOrder = new OrderRehab();

		String id = UUID.randomUUID().toString().replace("-", "");
		rehabOrder.setOrderId(id);
		rehabOrder.setRehabId(id);

		String addrId = orderParam.getOrderAddressId();
		AppUserAddressFull address = this.addrMapper.selectById(addrId);

		rehabOrder.setOrderAddress(address.getAddress());
		rehabOrder.setOrderContact(address.getContact());
		rehabOrder.setOrderTelephone(address.getTelephone());
		Date creatTime = new Date();
		rehabOrder.setCreateTime(creatTime);
		rehabOrder.setOrderType(orderParam.getOrderType());
		rehabOrder.setOrderRemark(orderParam.getOrderRemark());
		rehabOrder.setUserId(user.getUserId());
		rehabOrder.setAppointmentBeginTime(orderParam.getAppointmentBeginTime());
		rehabOrder.setAppointmentEndTime(orderParam.getAppointmentEndTime());
		rehabOrder.setOrderStatus(OrderConst.ORDER_STATUS_NORMAL);
		rehabOrder.setServiceType(OrderConst.SERVICE_TYPE_REHAB);
		rehabOrder.setPackageId(orderParam.getPackageId());
		rehabOrder.setPackageName(orderParam.getPackageName());
		rehabOrder.setPackagePriceId(orderParam.getPackagePriceId());
		rehabOrder.setPackagePriceName(orderParam.getPackagePriceName());
		rehabOrder.setOrderAmount(orderParam.getOrderAmount());
		rehabOrder.setOrderSn(GenerateOrderSN.nextCode());

		/*初始化订单流程状态*/
		String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(
				OrderConst.REHAB_STATUS_KEY_PREFIX + OrderConst.STATUS_START_INT);
		rehabOrder.setFlowStatus(OrderConst.STATUS_START_INT);
		rehabOrder.setFlowStatusName(flowStatusStr);

		Integer providerId = orderParam.getProviderId();
		Map<String, Object> providerMap = this.providerMapper.getServiceProviderById(providerId);
		rehabOrder.setProviderId(providerId);
		rehabOrder.setProviderName(providerMap.get("name").toString());

		/*根据地址,选择服务人员*/
		List<SysUser> sysUserList = this.sysUserMapper.getSysUserByProvider(providerId);
		if (sysUserList.size() > 0) {
			SysUser server = sysUserList.get(0);
			rehabOrder.setServiceContact(server.getName());
			rehabOrder.setServiceTelephone(server.getMobilephone());
			rehabOrder.setServiceUserId(String.valueOf(server.getId()));
		}

		Order order = new Order();
		BeanUtils.copyProperties(rehabOrder, order);

		this.orderMapper.insertSelective(order);
		this.rehabMapper.insertSelective(rehabOrder);

		/*插入流程状态表*/
		OrderRehabInfo flowStatus = this.getFlowStatus(id, user, OrderConst.STATUS_START_INT,
				flowStatusStr);
		this.flowStatusMapper.insertSelective(flowStatus);

		/*存入redis*/
		String orderKey = super.getOrderRedisKey(id);
		this.redisService.setObject(orderKey, rehabOrder);
		this.redisService.setExpire(orderKey, OrderConst.EXPIRE_TIME);

		JsonConfig jsonConfig = super.getJsonConfig();
		String orderFlowKey = super.getOrderFlowRedisKey(id);
		this.redisService.setListLeftPush(orderFlowKey,
				JSONObject.fromObject(flowStatus, jsonConfig).toString());
		this.redisService.setExpire(orderFlowKey, OrderConst.EXPIRE_TIME);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		/*数据格式化*/
		JSONObject orderJson = JSONObject.fromObject(rehabOrder, jsonConfig);

		result.setData(orderJson);

		return result;
	}

	/**
	 * 取消订单
	 * 
	 * @author gaochao
	 * @param jsonString
	 */
	@Override
	public Result cancelOrder(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		String token = requestJsonObject.getString("token");

		String remark = paramsObject.getString("remark");
		String id = paramsObject.getString("id");

		Map<String, Object> record = new HashMap<String, Object>();
		record.put("id", id);
		record.put("remark", remark);

		/*更新化订单流程状态*/
		String orderStatusStr = SysConstants.getOrderStatusConfig().getProperty(
				OrderConst.REHAB_STATUS_KEY_PREFIX + OrderConst.STATUS_CANCEL_INT);
		record.put("status", OrderConst.STATUS_CANCEL_INT);
		record.put("statusName", orderStatusStr);

		this.orderMapper.cancelOrder(record);

		/*取得用户对象*/
		AppUserInfo user = this.redisService.getRedisUserObject(RedisContants.USER_APP, token, AppUserInfo.class);

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		OrderRehabInfo flowStatus = this.getFlowStatus(id, user, OrderConst.STATUS_CANCEL_INT,
				orderStatusStr);

		/*插入订单状态*/
		this.flowStatusMapper.insertSelective(flowStatus);

		/*更新redis*/
		String orderKey = super.getOrderRedisKey(id);
		OrderRehab order = (OrderRehab) this.redisService.getObject(orderKey, OrderRehab.class);
		order.setIsCancel(1);
		order.setCancelRemark(remark);
		order.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
		order.setFlowStatusName(orderStatusStr);
		this.redisService.setObject(orderKey, order);
		this.redisService.setExpire(orderKey, OrderConst.EXPIRE_TIME);

		JsonConfig jsonConfig = super.getJsonConfig();
		String orderFlowKey = super.getOrderFlowRedisKey(id);
		this.redisService.setListLeftPush(orderFlowKey,
				JSONObject.fromObject(flowStatus, jsonConfig).toString());
		this.redisService.setExpire(orderFlowKey, OrderConst.EXPIRE_TIME);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

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
		List<OrderRehab> orderList = this.rehabMapper.getOrderList(record);

		int count = this.rehabMapper.getOrderListCount(record);
		int pageCount = (int) Math.ceil((double) count / lineNum);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pageNum", pageNum);
		resultMap.put("pageCount", pageCount);
		resultMap.put("orderList", orderList);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		/*数据格式化*/
		JsonConfig jsonConfig = super.getJsonConfig();
		JSONObject resultMapJson = JSONObject.fromObject(resultMap, jsonConfig);

		result.setData(resultMapJson);

		return result;
	}

	/**
	 * 更新订单状态
	 * 
	 * @author gaochao
	 * @param jsonString
	 */
	@Override
	public Result updateOrderStatus(String jsonString) {

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

		String id = paramsObject.getString("id");
		int status = paramsObject.getInt("status");

		String orderStatusStr = SysConstants.getOrderStatusConfig().getProperty(
				OrderConst.REHAB_STATUS_KEY_PREFIX + status);

		Map<String, Object> statusMap = new HashMap<String, Object>();
		statusMap.put("id", id);
		statusMap.put("status", status);
		statusMap.put("orderStatusStr", orderStatusStr);

		/*更新订单表状态*/
		int updateCount = this.orderMapper.updateOrderStatus(statusMap);

		if (updateCount < 1) {

			result.setCode(CodeEnum.nullData.getValue());
			result.setMessage(CodeEnum.nullData.getDescription());

			return result;
		}

		OrderRehabInfo flowStatus = this.getFlowStatus(id, user, status, orderStatusStr);

		/*插入订单状态*/
		this.flowStatusMapper.insertSelective(flowStatus);

		/*更新redis*/
		String orderKey = super.getOrderRedisKey(id);
		OrderRehab order = (OrderRehab) this.redisService.getObject(orderKey, OrderRehab.class);
		order.setFlowStatus(status);
		order.setFlowStatusName(orderStatusStr);
		this.redisService.setObject(orderKey, order);
		this.redisService.setExpire(orderKey, OrderConst.EXPIRE_TIME);

		JsonConfig jsonConfig = super.getJsonConfig();
		String orderFlowKey = super.getOrderFlowRedisKey(id);
		this.redisService.setListLeftPush(orderFlowKey,
				JSONObject.fromObject(flowStatus, jsonConfig).toString());
		this.redisService.setExpire(orderFlowKey, OrderConst.EXPIRE_TIME);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 更新订单
	 * 
	 * @param jsonString
	 */
	@Override
	public Result updateOrder(String jsonString) {

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

		OrderRehab order = (OrderRehab) JSONObject.toBean(paramsObject, OrderRehab.class);
		order.setRehabId(paramsObject.getString("orderId"));

		this.rehabMapper.updateByPrimaryKeySelective(order);

		String id = order.getOrderId();
		order = this.rehabMapper.selectByRehabId(paramsObject.getString("orderId"));

		/*更新redis*/
		String orderKey = super.getOrderRedisKey(id);
		this.redisService.setObject(orderKey, order);
		this.redisService.setExpire(orderKey, OrderConst.EXPIRE_TIME);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

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
		OrderRehab rehab = (OrderRehab) this.redisService.getObject(orderKey, OrderRehab.class);

		if (rehab == null || StringUtils.isBlank(rehab.getOrderId())) {

			rehab = this.rehabMapper.selectByRehabId(orderId);

			this.redisService.setObject(orderKey, rehab);
			this.redisService.setExpire(orderKey, OrderConst.EXPIRE_TIME);
		}

		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("order", rehab);

		List<Object> flowStatusJsonList = this.redisService.getList(super
				.getOrderFlowRedisKey(orderId));

		if (flowStatusJsonList == null || flowStatusJsonList.isEmpty()) {

			OrderRehabInfoCriteria flowStatusCriteria = new OrderRehabInfoCriteria();
			flowStatusCriteria.createCriteria().andOrderIdEqualTo(orderId);
			flowStatusCriteria.setOrderByClause("create_time desc");
			List<OrderRehabInfo> flowStatusList = this.flowStatusMapper
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
	 * @author gaochao
	 * @param orderId
	 * @param user
	 * @param orderStatus
	 * @param orderStatusStr
	 * @return
	 */
	private OrderRehabInfo getFlowStatus(String orderId, AppUserInfo user, Integer orderStatus,
			String orderStatusStr) {

		OrderRehabInfo flowStatus = new OrderRehabInfo();
		flowStatus.setOrderId(orderId);
		flowStatus.setCreateTime(new Date());
		flowStatus.setFlowStatus(orderStatus);
		flowStatus.setUserId(user.getUserId());
		flowStatus.setFlowStatusName(orderStatusStr);
		flowStatus.setUserType(OrderConst.USER);
		return flowStatus;
	}

}
