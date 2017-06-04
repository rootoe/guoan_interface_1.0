package com.guoan.service.work.impl;

import com.guoan.dao.shequ.*;
import com.guoan.dao.work.WorkUserInfoMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.*;
import com.guoan.entity.shequ.param.OrderParam;
import com.guoan.entity.work.bo.WorkUserInfo;
import com.guoan.entity.work.vo.CompleteWorkOrderVO;
import com.guoan.entity.work.vo.WorkLoginVO;
import com.guoan.entity.work.vo.WorkOrderFlowVO;
import com.guoan.entity.work.vo.WorkOrderVO;
import com.guoan.service.redis.RedisService;
import com.guoan.service.token.TokenGenerator;
import com.guoan.service.work.WorkUserService;
import com.guoan.utils.*;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.encode.MD5Util;
import com.guoan.utils.json.JsonDateValueProcessor;
import com.guoan.utils.push.PushUtils;
import com.guoan.utils.redis.RedisContants;
import com.guoan.utils.token.TokenUtil;
import com.tencent.xinge.XingeApp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述信息:
 * Created by 赵彤 on 2015/5/16.
 */
@Service
public class WorkUserServiceImpl extends RedisService implements WorkUserService {

    private static final Logger logger = LoggerFactory.getLogger(WorkUserServiceImpl.class);

    @Autowired
    private WorkUserInfoMapper workUserInfoMapper;

    @Autowired
    private OrderGoodsInfoMapper orderGoodsInfoMapper;

    @Autowired
    private OrderWashInfoMapper orderWashInfoMapper;

    @Autowired
    private OrderWashMapper orderWashMapper;

    @Autowired
    private OrderExpressMapper orderExpressMapper;

    @Autowired
    private OrderCleanMapper orderCleanMapper;

    @Autowired
    private OrderPayMapper orderPayMapper;

    @Autowired
    private OrderMaintainMapper orderMaintainMapper;

    @Autowired
    private OrderCleanInfoMapper orderCleanInfoMapper;

    @Autowired
    private OrderMaintainInfoMapper orderMaintainInfoMapper;

    @Autowired
    private OrderPayInfoMapper orderPayInfoMapper;


    @Autowired
    private OrderExpressInfoMapper orderExpressInfoMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SysDictionaryMapper dictionaryMapper;

    @Autowired
    private SysServiceProviderMapper providerMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SysStoreMapper sysStoreMapper;

    @Autowired
    private SysAddressMapper sysAreaMapper;
    
    //add  orderDrugMapper on 2015-09-16
    @Autowired
    private OrderDrugMapper orderDrugMapper;
  
    //add   OrderDrugInfoMapper on 2015-09-16
    @Autowired
    private OrderDrugInfoMapper orderDrugInfoMapper;

    //add OrderMassageMapper ON 2015-09-25
    @Autowired
    private OrderMassageMapper orderMassageMapper;
    
    //add OrderMassageInfoMapper ON 2015-09-25
    @Autowired
    private OrderMassageInfoMapper orderMassageInfoMapper;
    
    
    @Override
    @Transactional
    public Result login(String jsonString) {
        Result result = new Result();
        //*/
        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);

        String telephone = requestJsonObject.getString("telephone");
        String password = requestJsonObject.getString("password");
        // 推送使用的token
        String deviceToken = requestJsonObject.getString("deviceToken");

        if (StringUtils.isNotBlank(telephone) && StringUtils.isNotBlank(password) && (telephone.matches(Const.REGEX_PHONE_NUM_MAINLAND) || telephone.matches(Const.REGEX_PHONE_NUM_HONGKONG))) {
            // 验证用户名密码

            WorkUserInfo workUserInfo = workUserInfoMapper.getSysUserByTelephone(telephone);

            if (null != workUserInfo) {
                if (workUserInfo.getPassword().equals(password)) {
                    result.setCode(CodeEnum.success.getValue());
                    result.setMessage(CodeEnum.success.getDescription());

                    // 删除客户原有的redis中缓存的用户 主要是防止2台设备同时登陆的问题.
                    String token = TokenUtil.getTokenStringByUserId(MD5Util.getStringMD5(workUserInfo.getId().toString() + workUserInfo.getRemark() == null ? "noDeviceToken" : workUserInfo.getRemark()));
                    System.out.println("上一次的token: "+token);
                    this.deleteKey(RedisContants.USER_WORK + "_" + token);

                    // 更新国安侠的设备token
                    if(StringUtils.isNotEmpty(deviceToken)){
                        workUserInfo.setRemark(deviceToken);
                        // 根据deviceToken把和这个deviceToken一致的用户的deviceToken都删除
                        workUserInfoMapper.updateByWorkUserDeviceToken(deviceToken);
                        // 更新该员工的device token
                        workUserInfoMapper.updateByPrimaryKeySelective(workUserInfo);

                    }

                    // 生成token
                    String tokenString = tokenGenerator.generatorTokenWithWorkLogin(workUserInfo, Const.MEMCACHED_TOKEN_CODE_LIVE_TIME);
                    System.out.println("新生成的token: "+token);
                    WorkLoginVO workLoginVO = new WorkLoginVO();
                    workLoginVO.setToken(tokenString);
                    workLoginVO.setStoreName(workUserInfo.getStoreName());
                    workLoginVO.setProviderName(workUserInfo.getProviderName());
                    workLoginVO.setAvator(workUserInfo.getPhoto());
                    workLoginVO.setNickname(workUserInfo.getName());
                    //todo 这个分值需要计算
                    workLoginVO.setStarLevel("4.8");

                    workLoginVO.setNotice(SysConstants.getWorkAppInfoConfig().getProperty(Const.WORK_APP_INFO_NOTICE));
                    workLoginVO.setAboutUs(SysConstants.getWorkAppInfoConfig().getProperty(Const.WORK_APP_INFO_ABOUTUS));
                    workLoginVO.setFaq(SysConstants.getWorkAppInfoConfig().getProperty(Const.WORK_APP_INFO_FAQ));

                    List<String> serviceTypeList = new ArrayList<String>();
                    serviceTypeList.add(OrderConst.SERVICE_TYPE_SHOP); // 购物
                    serviceTypeList.add(OrderConst.SERVICE_TYPE_WASH); // 洗衣
                    serviceTypeList.add(OrderConst.SERVICE_TYPE_EXPRESS); // 快递
                    serviceTypeList.add(OrderConst.SERVICE_TYPE_PAY); // 代缴
                    serviceTypeList.add(OrderConst.SERVICE_TYPE_MAINTAIN); // 维修
                    serviceTypeList.add(OrderConst.SERVICE_TYPE_CLEAN); // 保洁
                    serviceTypeList.add(OrderConst.SERVICE_TYPE_DRUG);//处方药  add on 2015-09-15
                    serviceTypeList.add(OrderConst.SERVICE_TYPE_MASSAGE);//康复按摩  add on 2015-09-25

                    workLoginVO.setCompletedOrderCount(seachWorkOrderInfoCount(serviceTypeList, workUserInfo.getMobilephone(), OrderConst.FLOW_STATUS_YIWANCHENG));
                    workLoginVO.setCompletedOrderList(seachWorkOrderVOInfo(serviceTypeList, workUserInfo.getMobilephone(), OrderConst.FLOW_STATUS_YIWANCHENG));

                    // 返回一些下拉框数据字典
                    Map<String, List<Map<String, Object>>> dictionaryMap = new HashMap<String, List<Map<String, Object>>>();

                    // 支付
                    List<Map<String, Object>> dicList = this.dictionaryMapper.getDictionaryByType("paymentType");
                    dictionaryMap.put("paymentType", dicList);

                    // 获得该门店的区域
                    Integer storeAreaId = sysStoreMapper.getStoreAreaByStoreId(workUserInfo.getStoreId());

                    // 洗衣公司
                    Map<String, Object> providerMap = new HashMap<String, Object>();
                    providerMap.put("type", "wash");
                   

                    // 获取用户地址
                    List<Integer> parentAreaIdList = this.getAllParentAreaId(storeAreaId);
                    providerMap.put("areaList", parentAreaIdList);

                    List<Map<String, Object>> washProviderList = this.providerMapper.getProviderListByTypeAndArea(providerMap);
                    dictionaryMap.put("washProvider", washProviderList);
                    
                    
                    //****************************************
                    //医疗服务商   add  on 2015-09-15	
                    providerMap.put("type", "drug");
                    //add on 2015-09-15 
                    List<Map<String, Object>> drugProviderList = this.providerMapper.getProviderListByTypeAndArea(providerMap);
                    dictionaryMap.put("drugProvider", drugProviderList);
                    //***********************************************************
                    
                    //康复按摩  add ON 2015-09-25
                    providerMap.put("type","massage");
                    List<Map<String,Object>> massageProviderList = this.providerMapper.getProviderListByTypeAndArea(providerMap);
                    dictionaryMap.put("massageProvider",massageProviderList);
                    
                    
                    
                    // 快递公司
                    providerMap.put("type", "express");
                    List<Map<String, Object>> expressProviderList = this.providerMapper.getProviderListByTypeAndArea(providerMap);
                    dictionaryMap.put("expressProvider", expressProviderList);

                    // 保洁公司
                    providerMap.put("type", "clean");
                    List<Map<String, Object>> cleanProviderList = this.providerMapper.getProviderListByTypeAndArea(providerMap);
                    dictionaryMap.put("cleanProvider", cleanProviderList);

                    // 维修公司
                    providerMap.put("type", "maintain");
                    List<Map<String, Object>> maintainProviderList = this.providerMapper.getProviderListByTypeAndArea(providerMap);
                    dictionaryMap.put("maintainProvider", maintainProviderList);

                    // 代缴
                    List<Map<String, Object>> payList = this.dictionaryMapper.getDictionaryByType("payType");
                    dictionaryMap.put("payType", payList);


                    workLoginVO.setDictionary(dictionaryMap);

                    // 返回json前对日期数据进行格式化
//                    JsonConfig jsonConfig = new JsonConfig();
//                    jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
//                    JSONObject workUserVoJson = JSONObject.fromObject(workLoginVO, jsonConfig);

                    result.setData(workLoginVO);
                } else {
                    result.setCode(CodeEnum.passwordErr.getValue());
                    result.setMessage(CodeEnum.passwordErr.getDescription());
                }
            } else {
                result.setCode(CodeEnum.telePhoneNotExist.getValue());
                result.setMessage(CodeEnum.telePhoneNotExist.getDescription());
            }
        } else {
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
        }
        //
        return result;
    }

    private List<Integer> getAllParentAreaId(int areaId) {

        List<Integer> pathNodeIdList = new ArrayList<Integer>();

        Map<String, Long> pathNodeId = sysAreaMapper.getPathNodeId(areaId);
        pathNodeId.forEach((id, val) -> {
            if (val != null)
                pathNodeIdList.add(val.intValue());
        });

        return pathNodeIdList;
    }

    @Override
    public List<Order> seachWorkOrderInfo(List<String> serviceTypeList, String serviceTelephone, int flowStatus, int page) {
        List<Order> orderList = new ArrayList<Order>();
        OrderCriteria orderCriteria = new OrderCriteria();
        if (ListUtils.isEmpty(serviceTypeList)) {
            orderCriteria.createCriteria().andServiceTelephoneEqualTo(serviceTelephone).andFlowStatusEqualTo(flowStatus).andOrderStatusNotEqualTo(0);
        } else {
            orderCriteria.createCriteria().andServiceTelephoneEqualTo(serviceTelephone).andFlowStatusEqualTo(flowStatus).andServiceTypeIn(serviceTypeList).andOrderStatusNotEqualTo(0);
        }
        if (page < 1) {
            page = 1;
        }

        orderCriteria.setOrderByClause("create_time desc");
        orderCriteria.setLimitStart((page - 1) * 10);
        orderCriteria.setLimitEnd(10);
        orderList = orderMapper.selectByExample(orderCriteria);
        if(!ListUtils.isEmpty(orderList)){
            for(Order order : orderList){
                if(OrderConst.SERVICE_TYPE_EXPRESS.equals(order.getServiceType())){
                    OrderExpress orderExpress = orderExpressMapper.selectByExpressId(order.getOrderId());
                    if(OrderConst.EXPRESS_RECEIVE.equals(orderExpress.getDispatcherType())){
                        order.setDispatcherType(OrderConst.EXPRESS_RECEIVE);
                    } else if(OrderConst.EXPRESS_SEND.equals(orderExpress.getDispatcherType())){
                        order.setDispatcherType(OrderConst.EXPRESS_SEND);
                    }
                }
            }
        }
        return orderList;
    }

    @Override
    public int seachWorkOrderInfoCount(List<String> serviceTypeList, String serviceTelephone, int flowStatus) {

        OrderCriteria orderCriteria = new OrderCriteria();
        orderCriteria.createCriteria().andServiceTelephoneEqualTo(serviceTelephone).andFlowStatusEqualTo(flowStatus).andServiceTypeIn(serviceTypeList);

        return orderMapper.countByExample(orderCriteria);
    }

    @Override
    public List<WorkOrderVO> seachWorkOrderVOInfo(List<String> serviceTypeList, String serviceTelephone, int flowStatus) {

        List<WorkOrderVO> workOrderVOList = new ArrayList<WorkOrderVO>();
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("serviceTypeList", serviceTypeList);
        record.put("serviceTelephone", serviceTelephone);
        if (flowStatus >= 0) {
            record.put("flowStatus", flowStatus);
        }

        workOrderVOList = orderMapper.seachWorkOrderVOInfo(record);

        return workOrderVOList;
    }

    @Override
    public Result orderCompleteList(String jsonString) {
        Result result = new Result();
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        String token = jsonObject.getString("token");
        String serviceType = jsonObject.getString("serviceType");
        int page = jsonObject.getInt("page");

        WorkUserInfo user = new WorkUserInfo();
        user = super.getRedisUserObject(RedisContants.USER_WORK, token, user.getClass());
        if (null ==user || 0 == user.getId()) {
            result.setCode(CodeEnum.tokenErr.getValue());
            result.setMessage(CodeEnum.tokenErr.getDescription());
            return result;
        }
        List<String> serviceTypeList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(serviceType)) {
            serviceTypeList.add(serviceType);
        }else {
            serviceTypeList.add(OrderConst.SERVICE_TYPE_SHOP); // 购物
            serviceTypeList.add(OrderConst.SERVICE_TYPE_WASH); // 洗衣
            serviceTypeList.add(OrderConst.SERVICE_TYPE_EXPRESS); // 快递
            serviceTypeList.add(OrderConst.SERVICE_TYPE_PAY); // 代缴
            serviceTypeList.add(OrderConst.SERVICE_TYPE_MAINTAIN); // 维修
            serviceTypeList.add(OrderConst.SERVICE_TYPE_CLEAN); // 保洁
            serviceTypeList.add(OrderConst.SERVICE_TYPE_DRUG); // 处方药  add  ON  2015-09-16
            serviceTypeList.add(OrderConst.SERVICE_TYPE_MASSAGE);//康复按摩 add ON 2015-09-25
            
        }
        List<Order> orderList = seachWorkOrderInfo(serviceTypeList, user.getMobilephone(), OrderConst.FLOW_STATUS_YIWANCHENG, page);

        CompleteWorkOrderVO completeWorkOrderVO = new CompleteWorkOrderVO();
        completeWorkOrderVO.setOrderList(orderList);

        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());
        // 返回json前对日期数据进行格式化
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject orderListJsonObject = JSONObject.fromObject(completeWorkOrderVO, jsonConfig);

        result.setData(orderListJsonObject);

        return result;
    }

    @Override
    public Result orderMy(String jsonString) {
        Result result = new Result();
        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
      //报错
        String token = requestJsonObject.getString("token");

        JSONArray WorkOrderVOJSONArray = new JSONArray();

        WorkUserInfo workUserInfo = new WorkUserInfo();
        workUserInfo = super.getRedisUserObject(RedisContants.USER_WORK, token, workUserInfo.getClass());
        if (null == workUserInfo) {
            result.setCode(CodeEnum.tokenErr.getValue());
            result.setMessage(CodeEnum.tokenErr.getDescription());
            return result;
        }

        List<String> serviceTypeList = new ArrayList<String>();
        serviceTypeList.add(OrderConst.SERVICE_TYPE_SHOP); // 购物
        serviceTypeList.add(OrderConst.SERVICE_TYPE_WASH); // 洗衣
        serviceTypeList.add(OrderConst.SERVICE_TYPE_EXPRESS); // 快递
        serviceTypeList.add(OrderConst.SERVICE_TYPE_PAY); // 代缴
        serviceTypeList.add(OrderConst.SERVICE_TYPE_MAINTAIN); // 维修
        serviceTypeList.add(OrderConst.SERVICE_TYPE_CLEAN); // 保洁
        serviceTypeList.add(OrderConst.SERVICE_TYPE_DRUG); // 处方药  add on 2015-09-15
        serviceTypeList.add(OrderConst.SERVICE_TYPE_MASSAGE);// 康复按摩  add on 2015-09-25

        List<WorkOrderVO> workOrderVOList = seachWorkOrderVOInfo(serviceTypeList, workUserInfo.getMobilephone(), -1);
        if (ListUtils.isEmpty(workOrderVOList)) {
            result.setCode(CodeEnum.OrderWorkUserNull.getValue());
            result.setMessage(CodeEnum.OrderWorkUserNull.getDescription());
            return result;
        } else {
        	//跳转
            for (WorkOrderVO workOrderVO : workOrderVOList) {
                JSONObject WorkOrderVOJSONObject = new JSONObject();
                List<WorkOrderFlowVO> workOrderFlowVOList = seachWorkOrderFlowVOInfo(workOrderVO.getServiceType(), workUserInfo.getMobilephone());
                WorkOrderVOJSONObject.put("serviceType", workOrderVO.getServiceType());
                WorkOrderVOJSONObject.put("serviceTypeCount", workOrderVO.getServiceTypeCount());
                WorkOrderVOJSONObject.put("workOrderFLowList", workOrderFlowVOList);

                WorkOrderVOJSONArray.add(WorkOrderVOJSONObject);

                result.setCode(CodeEnum.success.getValue());
                result.setMessage(CodeEnum.success.getDescription());
                // 返回json前对日期数据进行格式化
//                JsonConfig jsonConfig = new JsonConfig();
//                jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
//                JSONObject workOrderMapJsonObject = JSONObject.fromObject(workOrderMap, jsonConfig);
                result.setData(WorkOrderVOJSONArray);
            }
        }

        return result;
    }

    @Override
    public Result orderList(String jsonString) {
        Result result = new Result();
        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);

        String token = requestJsonObject.getString("token");
        int page = requestJsonObject.getInt("page");
        String serviceType = requestJsonObject.getString("serviceType");
        int flowStatus = requestJsonObject.getInt("flowStatus");

        JSONArray WorkOrderVOJSONArray = new JSONArray();

        WorkUserInfo workUserInfo = new WorkUserInfo();
        workUserInfo = super.getRedisUserObject(RedisContants.USER_WORK, token, workUserInfo.getClass());
        if (null == workUserInfo) {
            result.setCode(CodeEnum.tokenErr.getValue());
            result.setMessage(CodeEnum.tokenErr.getDescription());
            return result;
        }

        List<String> serviceTypeList = new ArrayList<String>();
        serviceTypeList.add(serviceType);

        List<Order> orderList = seachWorkOrderInfo(serviceTypeList, workUserInfo.getMobilephone(), flowStatus, page);

        // 返回json前对日期数据进行格式化
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray workOrderJSONArray = JSONArray.fromObject(orderList, jsonConfig);

        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());
        result.setData(workOrderJSONArray);

        return result;
    }

    @Override
    @Transactional
    public Result orderUpdate(String jsonString) {
        Result result = new Result();
        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
        JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
        OrderParam orderParam = (OrderParam) JSONObject.toBean(paramsObject, OrderParam.class);

        String token = requestJsonObject.getString("token");

        JSONArray WorkOrderVOJSONArray = new JSONArray();

        // 验证token
        WorkUserInfo workUserInfo = new WorkUserInfo();
        workUserInfo = super.getRedisUserObject(RedisContants.USER_WORK, token, workUserInfo.getClass());
        if (null == workUserInfo) {
            result.setCode(CodeEnum.tokenErr.getValue());
            result.setMessage(CodeEnum.tokenErr.getDescription());
            return result;
        }

        // 查找该订单.
        Order order = orderMapper.selectByPrimaryKey(orderParam.getOrderId());
        if (null != order) {

            result = orderUpdate(workUserInfo, order, orderParam);


        } else {
            result.setCode(CodeEnum.OrderGetErr.getValue());
            result.setMessage(CodeEnum.OrderGetErr.getDescription());
            return result;
        }


        return result;
    }

    @Override
    public Result orderComplete(String jsonString) {
        Result result = new Result();
        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);

        String token = requestJsonObject.getString("token");

        JSONArray WorkOrderVOJSONArray = new JSONArray();

        WorkUserInfo workUserInfo = new WorkUserInfo();
        workUserInfo = super.getRedisUserObject(RedisContants.USER_WORK, token, workUserInfo.getClass());
        if (null == workUserInfo) {
            result.setCode(CodeEnum.tokenErr.getValue());
            result.setMessage(CodeEnum.tokenErr.getDescription());
            return result;
        }

        List<String> serviceTypeList = new ArrayList<String>();
        serviceTypeList.add(OrderConst.SERVICE_TYPE_SHOP); // 购物
        serviceTypeList.add(OrderConst.SERVICE_TYPE_WASH); // 洗衣
        serviceTypeList.add(OrderConst.SERVICE_TYPE_EXPRESS); // 快递
        serviceTypeList.add(OrderConst.SERVICE_TYPE_PAY); // 代缴
        serviceTypeList.add(OrderConst.SERVICE_TYPE_MAINTAIN); // 维修
        serviceTypeList.add(OrderConst.SERVICE_TYPE_CLEAN); // 保洁
        serviceTypeList.add(OrderConst.SERVICE_TYPE_DRUG); // 处方药  add on 2015-09-15
        serviceTypeList.add(OrderConst.SERVICE_TYPE_MASSAGE); //康复按摩  add on 2015-09-25
        

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("completedOrderCount", seachWorkOrderInfoCount(serviceTypeList, workUserInfo.getMobilephone(), OrderConst.FLOW_STATUS_YIWANCHENG));
        jsonObject.put("completedOrderList", seachWorkOrderVOInfo(serviceTypeList, workUserInfo.getMobilephone(), OrderConst.FLOW_STATUS_YIWANCHENG));

        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());
        result.setData(jsonObject);
        return result;
    }

    //************************************************************************************************************************
    //    @Transactional
    private Result orderUpdate(WorkUserInfo workUserInfo, Order order, OrderParam orderParam) {
        Result result = Result.successResult();
        try {
            Integer flowStatus = orderParam.getFlowStatus();
            String serviceType = order.getServiceType();
            if(OrderConst.SERVICE_TYPE_EXPRESS.equals(serviceType)){ // 快递
                switch (flowStatus) {
                    case OrderConst.FLOW_STATUS_JIEDANCHENGGONG:
                        // 1. 修改订单状态
                        updateExpressOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        break;
                    case OrderConst.FLOW_STATUS_EXPRESS_DAIYOUJI:
                        // 更新快递订单详情
                        // 更新订单表 修改金额.
                    	//************************************************************************************************************
                        Order washOrder = new Order();
                        washOrder.setOrderId(order.getOrderId());
                        // 应付
                        washOrder.setOrderAmount(orderParam.getOrderAmount());
                        // 实付 应付 - 优惠金额
                        double orderPaid = 0.0;
                        if(null != order.getOrderReduce() && 0 != order.getOrderReduce()){
                            if(orderParam.getOrderAmount() - order.getOrderReduce()<=0){
                                orderPaid = 0.0;
                            }else{
                                orderPaid = orderParam.getOrderAmount() - order.getOrderReduce();
                            }
                        }else {
                            orderPaid = orderParam.getOrderAmount();
                        }
                        washOrder.setOrderPaid(orderPaid);
                        washOrder.setPayTime(new Date());

                        // 付款方式
                        washOrder.setPaymentType(orderParam.getPaymentType());
                        washOrder.setPaymentTypeName(orderParam.getPaymentTypeName());
                        orderMapper.updateByPrimaryKeySelective(washOrder);

                        //更新快递订单详情
                        OrderExpress orderExpress = new OrderExpress();

                        orderExpress.setExpressId(order.getOrderId());
                        orderExpress.setGoodsInfo(orderParam.getGoodsInfo());
                        orderExpress.setGoodsWeight(orderParam.getGoodsWeight());
                        orderExpress.setExpressOrder(orderParam.getExpressOrder());

                        orderExpress.setDispatcherType(OrderConst.EXPRESS_SEND);
                        orderExpress.setTargetAddress(orderParam.getTargetAddress());
                        orderExpress.setSourceAddress(orderParam.getSourceAddress());
                        // 提供商
                        orderExpress.setProviderId(orderParam.getProviderId());
                        orderExpress.setProviderName(orderParam.getProviderName());
                        orderExpressMapper.updateByPrimaryKeySelective(orderExpress);

                        updateExpressOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);
                        break;
                    case OrderConst.FLOW_STATUS_EXPRESS_PEISONGZHONG:
                        updateExpressOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);
                        break;

                    default:
                        break;

                }
            }else if(OrderConst.SERVICE_TYPE_WASH.equals(serviceType)){ // 洗衣
                switch (flowStatus) {
                    case OrderConst.FLOW_STATUS_JIEDANCHENGGONG:
                        // 1. 修改状态
                        updateWashOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        break;
                    case -1: // -1说明是不修改状态, 只是把订单信息完善.

                        // 更新订单表 修改金额.
                        Order expressOrder = new Order();
                        expressOrder.setOrderId(order.getOrderId());
                        // 应付
                        expressOrder.setOrderAmount(orderParam.getOrderAmount());
                        // 实付 = 应付 - 优惠金额
                        double orderPaid = 0.0;
                        if(null != order.getOrderReduce() && 0 != order.getOrderReduce()){
                            if(orderParam.getOrderAmount() - order.getOrderReduce()<=0){
                                orderPaid = 0.0;
                            }else{
                                orderPaid = orderParam.getOrderAmount() - order.getOrderReduce();
                            }
                        }else {
                            orderPaid = orderParam.getOrderAmount();
                        }
                        expressOrder.setOrderPaid(orderPaid);
                        expressOrder.setPayTime(new Date());
                        // 付款方式
                        expressOrder.setPaymentType(orderParam.getPaymentType());
                        expressOrder.setPaymentTypeName(orderParam.getPaymentTypeName());
                        orderMapper.updateByPrimaryKeySelective(expressOrder);

                        //更新洗衣订单详情
                        OrderWash orderWash = new OrderWash();
                        orderWash.setWashId(order.getOrderId());
                        orderWash.setOrderQuantity(orderParam.getOrderQuantity());
                        orderWash.setWashOrder(orderParam.getWashOrder());
                        // 提供商
                        orderWash.setProviderId(orderParam.getProviderId());
                        orderWash.setProviderName(orderParam.getProviderName());
                        orderWashMapper.updateByPrimaryKeySelective(orderWash);

                        break;
                    case OrderConst.FLOW_STATUS_WASH_YIXIHAO:
                        updateWashOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        // 1. 发送短信
                        if(StringUtils.isNotEmpty(order.getServiceTelephone())){
                            CCPRestSmsUtil ccpRestSmsUtil = new CCPRestSmsUtil();
                            ccpRestSmsUtil.sendWashMessage(order.getOrderTelephone(), Const.YUNTONGXUN_MODEL_WASH_YIXIHAO);
                        }

                        // 2. 发送消息
                        Message message = new Message();
                        message.setMessageId(UUID.randomUUID().toString().replace("-", ""));
                        message.setOrderId(order.getOrderId());
                        message.setServiceType("wash");
                        message.setTitle("衣服已洗好!");
                        message.setContent("小主~您的衣裳已清洗完毕，正快马加鞭给您送去~到时候不见不散哦~");
                        message.setUserId(order.getUserId());
                        message.setCreateTime(new Date());
                        messageMapper.insert(message);

                        break;
                    case OrderConst.FLOW_STATUS_WASH_PEISONGZHONG:
                        // 1. 修改订单状态
                        updateWashOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        break;
                    default:
                        break;

                }
            }else if(OrderConst.SERVICE_TYPE_SHOP.equals(serviceType)){ // 购物
                switch (flowStatus) {
                    case OrderConst.FLOW_STATUS_JIEDANCHENGGONG:
                        // 1. 修改状态
                        updateShopOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        break;
                    case OrderConst.FLOW_STATUS_SHOP_PEISONGZHONG:
                        // 1. 发送短信
                        updateShopOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        break;
                    default:
                        break;

                }
            }else if(OrderConst.SERVICE_TYPE_CLEAN.equals(serviceType)){ // 保洁
                /**
                 * 1. 接单成功
                 * 2. 国安侠添加订单其他信息
                 */
                switch (flowStatus) {
                    case OrderConst.FLOW_STATUS_JIEDANCHENGGONG:
                        // 1. 修改状态
                        updateShopOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        break;
                    case -1: // -1说明是不修改状态, 只是把订单信息完善.
                        // 国安侠添加订单其他信息

                        // 更新订单表 修改金额.
                        Order cleanOrder = new Order();
                        cleanOrder.setOrderId(order.getOrderId());
                        // 应付
                        cleanOrder.setOrderAmount(orderParam.getOrderAmount());
                        // 实付 = 应付 - 优惠金额
                        double orderPaid = 0.0;
                        if(null != order.getOrderReduce() && 0 != order.getOrderReduce()){
                            if(orderParam.getOrderAmount() - order.getOrderReduce()<=0){
                                orderPaid = 0.0;
                            }else{
                                orderPaid = orderParam.getOrderAmount() - order.getOrderReduce();
                            }
                        }else {
                            orderPaid = orderParam.getOrderAmount();
                        }
                        cleanOrder.setOrderPaid(orderPaid);
                        cleanOrder.setPayTime(new Date());
                        // 付款方式
                        cleanOrder.setPaymentType(orderParam.getPaymentType());
                        cleanOrder.setPaymentTypeName(orderParam.getPaymentTypeName());
                        orderMapper.updateByPrimaryKeySelective(cleanOrder);

                        //更新保洁订单详情
                        OrderClean orderClean = new OrderClean();
                        orderClean.setCleanId(order.getOrderId());
                        orderClean.setOrderQuantity(orderParam.getOrderQuantity());
                        orderClean.setCleanOrder(orderParam.getWashOrder());
                        // 提供商
                        orderClean.setProviderId(orderParam.getProviderId());
                        orderClean.setProviderName(orderParam.getProviderName());
                        orderCleanMapper.updateByPrimaryKeySelective(orderClean);


                        break;
                    default:
                        break;

                }
            }else if(OrderConst.SERVICE_TYPE_MAINTAIN.equals(serviceType)){ // 维修
                /**
                 * 1. 接单成功
                 * 2. 国安侠添加订单其他信息
                 */
                switch (flowStatus) {
                    case OrderConst.FLOW_STATUS_JIEDANCHENGGONG:
                        // 1. 修改状态
                        updateMaintainOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        break;
                    case -1: // -1说明是不修改状态, 只是把订单信息完善.
                        // 国安侠添加订单其他信息

                        // 更新订单表 修改金额.
                        Order maintainOrder = new Order();
                        maintainOrder.setOrderId(order.getOrderId());
                        // 应付
                        maintainOrder.setOrderAmount(orderParam.getOrderAmount());
                        // 实付 = 应付 - 优惠金额
                        double orderPaid = 0.0;
                        if(null != order.getOrderReduce() && 0 != order.getOrderReduce()){
                            if(orderParam.getOrderAmount() - order.getOrderReduce()<=0){
                                orderPaid = 0.0;
                            }else{
                                orderPaid = orderParam.getOrderAmount() - order.getOrderReduce();
                            }
                        }else {
                            orderPaid = orderParam.getOrderAmount();
                        }
                        maintainOrder.setOrderPaid(orderPaid);
                        maintainOrder.setPayTime(new Date());
                        // 付款方式
                        maintainOrder.setPaymentType(orderParam.getPaymentType());
                        maintainOrder.setPaymentTypeName(orderParam.getPaymentTypeName());
                        orderMapper.updateByPrimaryKeySelective(maintainOrder);

                        //更新维修订单详情
                        OrderMaintain orderMaintain = new OrderMaintain();
                        orderMaintain.setMaintainId(order.getOrderId());
                        orderMaintain.setOrderQuantity(orderParam.getOrderQuantity());
                        orderMaintain.setMaintainOrder(orderParam.getWashOrder());
                        // 提供商
                        orderMaintain.setProviderId(orderParam.getProviderId());
                        orderMaintain.setProviderName(orderParam.getProviderName());
                        orderMaintainMapper.updateByPrimaryKeySelective(orderMaintain);

                        break;
                    default:
                        break;

                }
            }else if(OrderConst.SERVICE_TYPE_PAY.equals(serviceType)){ // 代缴
                /**
                 * 1. 接单成功
                 * 2. 国安侠添加订单其他信息
                 */
                switch (flowStatus) {
                    case OrderConst.FLOW_STATUS_JIEDANCHENGGONG:
                        // 1. 修改状态
                        updatePayOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                        break;
                    case -1: // -1说明是不修改状态, 只是把订单信息完善.
                        // 国安侠添加订单其他信息

                        // 更新订单表 修改金额.
                        Order payOrder = new Order();
                        payOrder.setOrderId(order.getOrderId());
                        // 应付
                        payOrder.setOrderAmount(orderParam.getOrderAmount());
                        // 实付 = 应付 - 优惠金额
                        double orderPaid = 0.0;
                        if(null != order.getOrderReduce() && 0 != order.getOrderReduce()){
                            if(orderParam.getOrderAmount() - order.getOrderReduce()<=0){
                                orderPaid = 0.0;
                            }else{
                                orderPaid = orderParam.getOrderAmount() - order.getOrderReduce();
                            }
                        }else {
                            orderPaid = orderParam.getOrderAmount();
                        }
                        payOrder.setOrderPaid(orderPaid);
                        payOrder.setPayTime(new Date());
                        // 付款方式
                        payOrder.setPaymentType(orderParam.getPaymentType());
                        payOrder.setPaymentTypeName(orderParam.getPaymentTypeName());
                        orderMapper.updateByPrimaryKeySelective(payOrder);

                        //更新维修订单详情
                        OrderPay orderPay = new OrderPay();
                        orderPay.setPayId(order.getOrderId());
                        orderPay.setOrderQuantity(orderParam.getOrderQuantity());
                        orderPay.setPayOrder(orderParam.getWashOrder());
                        // 提供商
                        orderPay.setPayType(orderParam.getPayType()+"");
                        orderPay.setPayTypeName(orderParam.getPayTypeName());
                        orderPayMapper.updateByPrimaryKeySelective(orderPay);

                        break;
                    default:
                        break;

                }
            }else if(OrderConst.SERVICE_TYPE_DRUG.equals(serviceType)){ // 处方药
                switch (flowStatus) {
                case OrderConst.FLOW_STATUS_JIEDANCHENGGONG:  //接单成功
                    // 1. 修改状态
                    updateDrugOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                    break;
                case -1: // -1说明是不修改状态, 只是把订单信息完善.

                    // 更新订单表 修改金额.
                    Order expressOrder = new Order();
                    expressOrder.setOrderId(order.getOrderId());
                    // 应付
                    expressOrder.setOrderAmount(orderParam.getOrderAmount());
                    // 实付 = 应付 - 优惠金额
                    double orderPaid = 0.0;
                    if(null != order.getOrderReduce() && 0 != order.getOrderReduce()){
                        if(orderParam.getOrderAmount() - order.getOrderReduce()<=0){
                            orderPaid = 0.0;
                        }else{
                            orderPaid = orderParam.getOrderAmount() - order.getOrderReduce();
                        }
                    }else {
                        orderPaid = orderParam.getOrderAmount();
                    }
                    expressOrder.setOrderPaid(orderPaid);
                    expressOrder.setPayTime(new Date());
                    // 付款方式
                    expressOrder.setPaymentType(orderParam.getPaymentType());
                    expressOrder.setPaymentTypeName(orderParam.getPaymentTypeName());
                    orderMapper.updateByPrimaryKeySelective(expressOrder);

                    //更新处方药订单详情
                    OrderDrug orderDrug = new OrderDrug();
                    orderDrug.setDrugsId(order.getOrderId());
                    orderDrug.setOrderQuantity(orderParam.getOrderQuantity());
                    orderDrug.setDrugsOrder(orderParam.getDrugsOrder());
                    // 提供商 
                    orderDrug.setProviderId(orderParam.getProviderId());
                    orderDrug.setProviderName(orderParam.getProviderName());
                    orderDrugMapper.updateByPrimaryKeySelective(orderDrug);

                    break;
                case OrderConst.FLOW_STATUS_DRUG_YIGOUYAO:
                    updateDrugOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                    // 1. 发送短信
                    if(StringUtils.isNotEmpty(order.getServiceTelephone())){
                        CCPRestSmsUtil ccpRestSmsUtil = new CCPRestSmsUtil();
                        ccpRestSmsUtil.sendDrugMessage(order.getOrderTelephone(), Const.YUNTONGXUN_MODEL_DRUG_YIGOUYAO);
                    }

                    // 2. 发送消息
                    Message message = new Message();
                    message.setMessageId(UUID.randomUUID().toString().replace("-", ""));
                    message.setOrderId(order.getOrderId());
                    message.setServiceType("drug");
                    message.setTitle("处方药备药完成!");
                    message.setContent("小主~您的药品已经备药完毕，正快马加鞭给您送去~到时候不见不散哦~");
                    message.setUserId(order.getUserId());
                    message.setCreateTime(new Date());
                    messageMapper.insert(message);

                    break;
                case OrderConst.FLOW_STATUS_DRUG_PEISONGZHONG:
                    // 1. 修改订单状态
                    updateDrugOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                    break;
                default:
                    break;

                   }
               }//####################################### 康复按摩 
            else if(OrderConst.SERVICE_TYPE_MASSAGE.equals(serviceType)){ // add massage 康复按摩 on 2015-09-28
                switch (flowStatus) {
                case OrderConst.FLOW_STATUS_JIEDANCHENGGONG:  //接单成功
                    // 1. 修改状态
                    updateMassageOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                    break;
                case -1: // -1说明是不修改状态, 只是把订单信息完善.

                    // 更新订单表 修改金额.
                    Order expressOrder = new Order();
                    expressOrder.setOrderId(order.getOrderId());
                    // 应付
                    expressOrder.setOrderAmount(orderParam.getOrderAmount());
                    // 实付 = 应付 - 优惠金额
                    double orderPaid = 0.0;
                    if(null != order.getOrderReduce() && 0 != order.getOrderReduce()){
                        if(orderParam.getOrderAmount() - order.getOrderReduce()<=0){
                            orderPaid = 0.0;
                        }else{
                            orderPaid = orderParam.getOrderAmount() - order.getOrderReduce();
                        }
                    }else {
                        orderPaid = orderParam.getOrderAmount();
                    }
                    expressOrder.setOrderPaid(orderPaid);
                    expressOrder.setPayTime(new Date());
                    // 付款方式
                    expressOrder.setPaymentType(orderParam.getPaymentType());
                    expressOrder.setPaymentTypeName(orderParam.getPaymentTypeName());
                    orderMapper.updateByPrimaryKeySelective(expressOrder);

                    //更新处方药订单详情
                    OrderMassage orderMassage = new OrderMassage();
                    orderMassage.setMassageId(order.getOrderId());
                    orderMassage.setOrderQuantity(orderParam.getOrderQuantity());
                    orderMassage.setMassageOrder(orderParam.getMassageOrder());
                    // 提供商 
                    orderMassage.setProviderId(orderParam.getProviderId());
                    orderMassage.setProviderName(orderParam.getProviderName());
                    orderMassageMapper.updateByPrimaryKeySelective(orderMassage);

                    break;
                case OrderConst.FLOW_STATUS_MASSAGE_KAISHIFUWU:// 对应 
                    updateMassageOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                    // 1. 发送短信
                    if(StringUtils.isNotEmpty(order.getServiceTelephone())){
                        CCPRestSmsUtil ccpRestSmsUtil = new CCPRestSmsUtil();
                        ccpRestSmsUtil.sendMassageMessage(order.getOrderTelephone(), Const.YUNTONGXUN_MODEL_MASSAGE_KAISHIFUWU);
                    }

                    // 2. 发送消息
                    Message message = new Message();
                    message.setMessageId(UUID.randomUUID().toString().replace("-", ""));
                    message.setOrderId(order.getOrderId());
                    message.setServiceType("massage");
                    message.setTitle("按摩服务完成!");
                    message.setContent("小主~您的按摩服务已完成");
                    message.setUserId(order.getUserId());
                    message.setCreateTime(new Date());
                    messageMapper.insert(message);

                    break;
                case OrderConst.FLOW_STATUS_YIWANCHENG:
                    // 1. 修改订单状态
                    updateMassageOrder(workUserInfo.getId() + "", order.getOrderId(), flowStatus);

                    break;
                default:
                    break;

                   }
               }
            }catch (Exception e){
            logger.debug("服务人员修改订单状态的时候出错 -> "+e);
            result = Result.errorResult();
        }
        return result;
    }

    /**
     * 更新洗衣订单状态
     * @param userId
     * @param orderId
     * @param flowStatus
     */
    private void updateWashOrder(String userId, String orderId, Integer flowStatus) {

        String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.WASH_STATUS_KEY_PREFIX + flowStatus);
        // 修改洗衣订单流转状态
        Order washOrder = new Order();
        washOrder.setOrderId(orderId);
        washOrder.setFlowStatus(flowStatus);
        washOrder.setFlowStatusName(flowStatusStr);
        orderMapper.updateByPrimaryKeySelective(washOrder);

        // 插入一条洗衣订单流转数据
        OrderWashInfo orderWashInfo = new OrderWashInfo();
        orderWashInfo.setOrderId(orderId);
        orderWashInfo.setUserType("worker");
        orderWashInfo.setUserId(userId);
        orderWashInfo.setFlowStatus(flowStatus);
        orderWashInfo.setFlowStatusName(flowStatusStr);
        orderWashInfo.setCreateTime(new Date());
        orderWashInfoMapper.insert(orderWashInfo);


    }

    /**
     * 更新购物订单状态
     * @param userId
     * @param orderId
     * @param flowStatus
     */
    private void updateShopOrder(String userId, String orderId, Integer flowStatus) {

        String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.SHOP_STATUS_KEY_PREFIX + flowStatus);
        // 修改购物订单流转状态
        Order shopOrder = new Order();
        shopOrder.setOrderId(orderId);
        shopOrder.setFlowStatus(flowStatus);
        shopOrder.setFlowStatusName(flowStatusStr);
        orderMapper.updateByPrimaryKeySelective(shopOrder);

        // 插入一条购物订单流转数据
        OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
        orderGoodsInfo.setOrderId(orderId);
        orderGoodsInfo.setUserType("worker");
        orderGoodsInfo.setUserId(userId);
        orderGoodsInfo.setFlowStatus(flowStatus);
        orderGoodsInfo.setFlowStatusName(flowStatusStr);
        orderGoodsInfo.setCreateTime(new Date());
        orderGoodsInfoMapper.insert(orderGoodsInfo);


    }

    /**
     * 更新快递订单状态
     * @param userId
     * @param orderId
     * @param flowStatus
     */
    private void updateExpressOrder(String userId, String orderId, Integer flowStatus) {

        String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.EXPRESS_STATUS_KEY_PREFIX + flowStatus);
        // 修改洗衣订单流转状态
        Order expressOrder = new Order();
        expressOrder.setOrderId(orderId);
        expressOrder.setFlowStatus(flowStatus);
        expressOrder.setFlowStatusName(flowStatusStr);
        orderMapper.updateByPrimaryKeySelective(expressOrder);

        // 插入一条洗衣订单流转数据
        OrderExpressInfo orderExpressInfo = new OrderExpressInfo();
        orderExpressInfo.setOrderId(orderId);
        orderExpressInfo.setUserType("worker");
        orderExpressInfo.setUserId(userId);
        orderExpressInfo.setFlowStatus(flowStatus);
        orderExpressInfo.setFlowStatusName(flowStatusStr);
        orderExpressInfo.setCreateTime(new Date());
        orderExpressInfoMapper.insert(orderExpressInfo);

    }

    /**
     * 更新保洁订单状态
     * @param userId
     * @param orderId
     * @param flowStatus
     */
    private void updateCleanOrder(String userId, String orderId, Integer flowStatus) {

        String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.CLEAN_STATUS_KEY_PREFIX + flowStatus);
        // 修改购物订单流转状态
        Order shopOrder = new Order();
        shopOrder.setOrderId(orderId);
        shopOrder.setFlowStatus(flowStatus);
        shopOrder.setFlowStatusName(flowStatusStr);
        orderMapper.updateByPrimaryKeySelective(shopOrder);

        // 插入一条购物订单流转数据
        OrderCleanInfo orderCleanInfo = new OrderCleanInfo();
        orderCleanInfo.setOrderId(orderId);
        orderCleanInfo.setUserType("worker");
        orderCleanInfo.setUserId(userId);
        orderCleanInfo.setFlowStatus(flowStatus);
        orderCleanInfo.setFlowStatusName(flowStatusStr);
        orderCleanInfo.setCreateTime(new Date());
        orderCleanInfoMapper.insert(orderCleanInfo);


    }

    /**
     * 更新代缴订单状态
     * @param userId
     * @param orderId
     * @param flowStatus
     */
    private void updatePayOrder(String userId, String orderId, Integer flowStatus) {

        String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.PAY_STATUS_KEY_PREFIX + flowStatus);
        // 修改购物订单流转状态
        Order shopOrder = new Order();
        shopOrder.setOrderId(orderId);
        shopOrder.setFlowStatus(flowStatus);
        shopOrder.setFlowStatusName(flowStatusStr);
        orderMapper.updateByPrimaryKeySelective(shopOrder);

        // 插入一条购物订单流转数据
        OrderPayInfo orderPayInfo = new OrderPayInfo();
        orderPayInfo.setOrderId(orderId);
        orderPayInfo.setUserType("worker");
        orderPayInfo.setUserId(userId);
        orderPayInfo.setFlowStatus(flowStatus);
        orderPayInfo.setFlowStatusName(flowStatusStr);
        orderPayInfo.setCreateTime(new Date());
        orderPayInfoMapper.insert(orderPayInfo);


    }

    /**
     * 更新维修订单状态
     * @param userId
     * @param orderId
     * @param flowStatus
     */
    private void updateMaintainOrder(String userId, String orderId, Integer flowStatus) {

        String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.MAINTAIN_STATUS_KEY_PREFIX + flowStatus);
        // 修改购物订单流转状态
        Order shopOrder = new Order();
        shopOrder.setOrderId(orderId);
        shopOrder.setFlowStatus(flowStatus);
        shopOrder.setFlowStatusName(flowStatusStr);
        orderMapper.updateByPrimaryKeySelective(shopOrder);

        // 插入一条购物订单流转数据
        OrderMaintainInfo orderMaintainInfo = new OrderMaintainInfo();
        orderMaintainInfo.setOrderId(orderId);
        orderMaintainInfo.setUserType("worker");
        orderMaintainInfo.setUserId(userId);
        orderMaintainInfo.setFlowStatus(flowStatus);
        orderMaintainInfo.setFlowStatusName(flowStatusStr);
        orderMaintainInfo.setCreateTime(new Date());
        orderMaintainInfoMapper.insert(orderMaintainInfo);


    }
    
    /**
     * 更新处方药订单状态
     * @param userId
     * @param orderId
     * @param flowStatus
     */
    private void updateDrugOrder(String userId, String orderId, Integer flowStatus) {

        String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.DRUG_STATUS_KEY_PREFIX + flowStatus);
        // 修改处方药订单流转状态
        Order drugOrder = new Order();   //  2015-09-18
        drugOrder.setOrderId(orderId);
        drugOrder.setFlowStatus(flowStatus);
        drugOrder.setFlowStatusName(flowStatusStr);
        orderMapper.updateByPrimaryKeySelective(drugOrder);

        // 插入一条处方药订单流转数据
        OrderDrugInfo orderDrugInfo = new OrderDrugInfo();
        orderDrugInfo.setOrderId(orderId);
        orderDrugInfo.setUserType("worker");
        orderDrugInfo.setUserId(userId);
        orderDrugInfo.setFlowStatus(flowStatus);
        orderDrugInfo.setFlowStatusName(flowStatusStr);
        orderDrugInfo.setCreateTime(new Date());
        orderDrugInfoMapper.insert(orderDrugInfo);


    }

    /**
     * 更新康复按摩订单状态
     * @param userId
     * @param orderId
     * @param flowStatus
     */
    private void updateMassageOrder(String userId,String orderId,Integer flowStatus){
    	
    	 String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.DRUG_STATUS_KEY_PREFIX + flowStatus);
         // 修改处方药订单流转状态
         Order massageOrder = new Order();   //  2015-09-18
         massageOrder.setOrderId(orderId);
         massageOrder.setFlowStatus(flowStatus);
         massageOrder.setFlowStatusName(flowStatusStr);
         orderMapper.updateByPrimaryKeySelective(massageOrder);

         // 插入一条处方药订单流转数据
         OrderMassageInfo orderMassageInfo = new OrderMassageInfo();
         orderMassageInfo.setOrderId(orderId);
         orderMassageInfo.setUserType("worker");
         orderMassageInfo.setUserId(userId);
         orderMassageInfo.setFlowStatus(flowStatus);
         orderMassageInfo.setFlowStatusName(flowStatusStr);
         orderMassageInfo.setCreateTime(new Date());
         orderMassageInfoMapper.insert(orderMassageInfo);
    	
    }


    private List<WorkOrderFlowVO> seachWorkOrderFlowVOInfo(String serviceType, String serviceTelephone) {

        List<WorkOrderFlowVO> workOrderFlowVOList = new ArrayList<WorkOrderFlowVO>();
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("serviceType", serviceType);
        record.put("serviceTelephone", serviceTelephone);

        workOrderFlowVOList = orderMapper.seachWorkOrderTypeVOInfo(record);

        return workOrderFlowVOList;
    }


}
