package com.guoan.service.guoan.impl;

import com.guoan.dao.shequ.*;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.*;
import com.guoan.entity.shequ.param.OrderParam;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.OrderConst;
import com.guoan.utils.StringUtils;
import com.guoan.utils.SysConstants;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.json.JsonDateValueProcessor;
import com.guoan.utils.redis.RedisContants;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private RedisService redisService;

	@Autowired
	private SysCouponMapper couponMapper;

	@Autowired
	private OrderWashInfoMapper orderWashInfoMapper;

	@Autowired
	private OrderExpressInfoMapper orderExpressInfoMapper;

	@Autowired
	private OrderPayInfoMapper orderPayInfoMapper;

	@Autowired
	private OrderMaintainInfoMapper orderMaintainInfoMapper;

	@Autowired
	private OrderCleanInfoMapper orderCleanInfoMapper;

	@Autowired
	private OrderGoodsInfoMapper orderGoodsInfoMapper;
	
	//add drugMapper at 2015-09-15
	@Autowired
	private OrderDrugInfoMapper orderDrugInfoMapper;
	
	//add massageMapper ON 2015-09-24
	@Autowired
	private OrderMassageInfoMapper orderMassageInfoMapper;
	

	/**
	 * 取消订单
	 * @param jsonString
	 */
	@Transactional
	public Result cancelOrder(String jsonString) {

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

		String remark = paramsObject.getString("remark");
		String id = paramsObject.getString("id");

		/*更新化订单流程状态*/
		String orderStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.WASH_STATUS_KEY_PREFIX + OrderConst.STATUS_CANCEL_INT);

		Order order = orderMapper.selectByPrimaryKey(id);
		if(null == order){
			result.setCode(CodeEnum.OrderGetNull.getValue());
			result.setMessage(CodeEnum.OrderGetNull.getDescription());
			return result;
		}
		Order orderCancel = new Order();
		orderCancel.setOrderId(order.getOrderId());
		orderCancel.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
		orderCancel.setFlowStatusName(orderStatusStr);

		// 添加订单流转状态
		String type = order.getServiceType();

		if (StringUtils.isNotBlank(type)) {
			switch (type) {
			case OrderConst.SERVICE_TYPE_DRUG:  //add Service_type_drug at 2015-09-15
				OrderDrugInfo orderDrugInfo = new OrderDrugInfo();
				orderDrugInfo.setOrderId(order.getOrderId());
				orderDrugInfo.setCreateTime(new Date());
				orderDrugInfo.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
				orderDrugInfo.setUserId(user.getUserId());
				orderDrugInfo.setFlowStatusName(orderStatusStr);
				orderDrugInfo.setUserType(OrderConst.USER);
				orderDrugInfoMapper.insertSelective(orderDrugInfo);
				break;
				case OrderConst.SERVICE_TYPE_WASH:
					OrderWashInfo orderWashInfo = new OrderWashInfo();
					orderWashInfo.setOrderId(order.getOrderId());
					orderWashInfo.setCreateTime(new Date());
					orderWashInfo.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
					orderWashInfo.setUserId(user.getUserId());
					orderWashInfo.setFlowStatusName(orderStatusStr);
					orderWashInfo.setUserType(OrderConst.USER);
					orderWashInfoMapper.insertSelective(orderWashInfo);
					break;
				case OrderConst.SERVICE_TYPE_EXPRESS:
					OrderExpressInfo orderExpressInfo = new OrderExpressInfo();
					orderExpressInfo.setOrderId(order.getOrderId());
					orderExpressInfo.setCreateTime(new Date());
					orderExpressInfo.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
					orderExpressInfo.setUserId(user.getUserId());
					orderExpressInfo.setFlowStatusName(orderStatusStr);
					orderExpressInfo.setUserType(OrderConst.USER);
					orderExpressInfoMapper.insertSelective(orderExpressInfo);
					break;
				case OrderConst.SERVICE_TYPE_PAY:
					OrderPayInfo orderPayInfo = new OrderPayInfo();
					orderPayInfo.setOrderId(order.getOrderId());
					orderPayInfo.setCreateTime(new Date());
					orderPayInfo.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
					orderPayInfo.setUserId(user.getUserId());
					orderPayInfo.setFlowStatusName(orderStatusStr);
					orderPayInfo.setUserType(OrderConst.USER);
					orderPayInfoMapper.insertSelective(orderPayInfo);
					break;
				case OrderConst.SERVICE_TYPE_MAINTAIN:
					OrderMaintainInfo orderMaintainInfo = new OrderMaintainInfo();
					orderMaintainInfo.setOrderId(order.getOrderId());
					orderMaintainInfo.setCreateTime(new Date());
					orderMaintainInfo.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
					orderMaintainInfo.setUserId(user.getUserId());
					orderMaintainInfo.setFlowStatusName(orderStatusStr);
					orderMaintainInfo.setUserType(OrderConst.USER);
					orderMaintainInfoMapper.insertSelective(orderMaintainInfo);
					break;
				case OrderConst.SERVICE_TYPE_CLEAN:
					OrderCleanInfo orderCleanInfo = new OrderCleanInfo();
					orderCleanInfo.setOrderId(order.getOrderId());
					orderCleanInfo.setCreateTime(new Date());
					orderCleanInfo.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
					orderCleanInfo.setUserId(user.getUserId());
					orderCleanInfo.setFlowStatusName(orderStatusStr);
					orderCleanInfo.setUserType(OrderConst.USER);
					orderCleanInfoMapper.insertSelective(orderCleanInfo);
					break;
				case OrderConst.SERVICE_TYPE_MASSAGE:  //add Service_type_drug at 2015-09-15
					OrderMassageInfo orderMassageInfo = new OrderMassageInfo();
					orderMassageInfo.setOrderId(order.getOrderId());
					orderMassageInfo.setCreateTime(new Date());
					orderMassageInfo.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
					orderMassageInfo.setUserId(user.getUserId());
					orderMassageInfo.setFlowStatusName(orderStatusStr);
					orderMassageInfo.setUserType(OrderConst.USER);
					orderMassageInfoMapper.insertSelective(orderMassageInfo);
					break;
				case OrderConst.SERVICE_TYPE_SHOP:
					// todo 购物时并且暂时不支持取消订单 涉及到退钱
					if(19 == order.getPaymentType() && OrderConst.STATUS_START_DAIFUKUAN != order.getFlowStatus()){
						result.setCode(CodeEnum.error.getValue());
						result.setMessage("请与国安侠联系。");
						return result;
					}

					OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
					orderGoodsInfo.setOrderId(order.getOrderId());
					orderGoodsInfo.setCreateTime(new Date());
					orderGoodsInfo.setFlowStatus(OrderConst.STATUS_CANCEL_INT);
					orderGoodsInfo.setUserId(user.getUserId());
					orderGoodsInfo.setFlowStatusName(orderStatusStr);
					orderGoodsInfo.setUserType(OrderConst.USER);
					orderGoodsInfoMapper.insertSelective(orderGoodsInfo);

					break;
				default:
					result.setCode(CodeEnum.paramErr.getValue());
					result.setMessage(CodeEnum.paramErr.getDescription());
					return result;
			}
		} else {
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			return result;
		}

		// 修改订单流转状态
		orderMapper.updateByPrimaryKeySelective(orderCancel);

		// 修改优惠券的状态为绑定
		String couponId = order.getCouponId();

		if (StringUtils.isNotBlank(couponId)) {

			SysCoupon coupon = new SysCoupon();
			SysCouponCriteria couponCriteria = new SysCouponCriteria();
			couponCriteria.createCriteria().andIdEqualTo(couponId);
			coupon.setStatus(OrderConst.COUPON_BIND);
			this.couponMapper.updateByExampleSelective(coupon, couponCriteria);
		}

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 评论订单
	 * @param jsonString
	 */
	@Transactional
	public Result commentOrder(String jsonString) {

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

		this.orderMapper.commentOrder(orderParam);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 删除订单
	 * @param jsonString
	 */
	@Transactional
	public Result deleteOrder(String jsonString) {

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
		if(StringUtils.isNotEmpty(id)){
			this.orderMapper.deleteOrder(id);
		}

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 取得订单列表
	 * @param jsonString
	 */
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
		List<Order> orderList = this.orderMapper.getOrderList(record);
		if(ListUtils.isEmpty(orderList)){
			result.setCode(CodeEnum.OrderGetNull.getValue());
			result.setMessage(CodeEnum.OrderGetNull.getDescription());
			return result;
		}
		int count = this.orderMapper.getOrderListCount(record);
		int pageCount = (int) Math.ceil((double) count / lineNum);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pageNum", pageNum);
		resultMap.put("pageCount", pageCount);
		resultMap.put("orderList", orderList);
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());
		/*数据格式化*/
		JsonConfig jsonConfig = this.getJsonConfig();
		JSONObject resultMapJson = JSONObject.fromObject(resultMap, jsonConfig);
		result.setData(resultMapJson);
		return result;
	}

	protected String getOrderRedisKey(String orderId) {

		return "order_" + orderId;
	}

	protected String getOrderFlowRedisKey(String orderId) {

		return this.getOrderRedisKey(orderId) + "_flow";
	}

	protected JsonConfig getJsonConfig() {

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

		jsonConfig.registerDefaultValueProcessor(Integer.class, type -> "");

		jsonConfig.registerDefaultValueProcessor(Double.class, type -> "");

		return jsonConfig;
	}

}
