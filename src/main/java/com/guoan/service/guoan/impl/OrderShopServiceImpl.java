package com.guoan.service.guoan.impl;

import com.guoan.dao.shequ.*;
import com.guoan.dao.work.WorkUserInfoMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.*;
import com.guoan.entity.shequ.param.OrderParam;
import com.guoan.entity.work.bo.WorkUserInfo;
import com.guoan.service.guoan.AppSystemService;
import com.guoan.service.guoan.OrderShopService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.DateUtil;
import com.guoan.utils.OrderConst;
import com.guoan.utils.StringUtils;
import com.guoan.utils.SysConstants;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.order.GenerateOrderSN;
import com.guoan.utils.push.PushUtils;
import com.guoan.utils.redis.RedisContants;
import com.pingplusplus.ChargeUtils;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
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
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Service
public class OrderShopServiceImpl extends OrderService implements OrderShopService {

    private static final Logger logger = LoggerFactory.getLogger(OrderShopServiceImpl.class);

    @Autowired
    private AppUserAddressMapper addrMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private OrderGoodsInfoMapper goodsInfoMapper;

    @Autowired
    private OrderExpressMapper expressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Autowired
    private OrderGoodsInfoMapper flowStatusMapper;

    @Autowired
    private OrderGoodsMapper goodsMapper;

    @Autowired
    private SysCouponMapper couponMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AppSystemService sysService;

    @Autowired
    private AppUserInfoMapper appUserInfoMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private WorkUserInfoMapper workUserInfoMapper;

    /**
     * 下单
     *
     * @param jsonString
     */
    @Override
    @Transactional
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

        List<Map<String, Integer>> goodsList = orderParam.getGoods();
        List<ShopGoods> shopGoodsList = new ArrayList<ShopGoods>();
        Charge charge = null;

        OrderGoods goodsOrder = new OrderGoods();

        String id = UUID.randomUUID().toString().replace("-", "");
        goodsOrder.setOrderId(id);

		/*获得地址信息*/
        String addrId = orderParam.getOrderAddressId();
        AppUserAddressFull address = this.addrMapper.selectById(addrId);

        goodsOrder.setOrderAddress(address.getAddress());
        goodsOrder.setOrderContact(address.getContact());
        goodsOrder.setOrderTelephone(address.getTelephone());
        goodsOrder.setCreateTime(new Date());
        goodsOrder.setOrderType(OrderConst.ORDER_TYPE_APP);
        goodsOrder.setOrderRemark(orderParam.getOrderRemark());
        goodsOrder.setUserId(user.getUserId());
        Date beginTime = orderParam.getAppointmentBeginTime();
        Date endTime = DateUtils.addMinutes(beginTime, 30);
        goodsOrder.setAppointmentBeginTime(beginTime);
        goodsOrder.setAppointmentEndTime(endTime);
        goodsOrder.setOrderStatus(OrderConst.ORDER_STATUS_NORMAL);
        goodsOrder.setServiceType(OrderConst.SERVICE_TYPE_SHOP);
        // 优惠卷
        goodsOrder.setCouponId(orderParam.getCouponId());

        goodsOrder.setIsEvaluation(OrderConst.IsNotEvaluation);
        goodsOrder.setIsCancel(OrderConst.IsNotCancel);

        goodsOrder.setOrderSn(GenerateOrderSN.nextCode());

		/*根据地址,选择服务人员*/
        List<SysUser> sysUserList = this.sysUserMapper.getSysUserByArea(address.getAreaId());
        if (sysUserList != null && sysUserList.size() > 0) {
            SysUser server = sysUserList.get(0);
            goodsOrder.setServiceContact(server.getName());
            goodsOrder.setServiceTelephone(server.getMobilephone());
            goodsOrder.setServiceUserId(String.valueOf(server.getId()));
            goodsOrder.setStoreId(server.getStoreId());
        } else {
            Map<String, Object> storeMap = this.sysService.getStoreByArea(orderParam.getAreaId());
            goodsOrder.setStoreId((Long) storeMap.get("id"));
        }

        Order order = new Order();
        OrderShop orderShop = new OrderShop();
        BeanUtils.copyProperties(goodsOrder, order);

        // 购买的商品
        Double orderTotalAmount = 0.0;
        int orderQuantity = 0;
        StringBuffer stringBufferGoodsName = new StringBuffer();
        if (!ListUtils.isEmpty(goodsList)) {
            orderQuantity = goodsList.size();
            for (int i = 0, l = goodsList.size(); i < l; i++) {

                JSONObject jasonObject = JSONObject.fromObject(goodsList.get(i));
                Map<String, Object> map2 = (Map) jasonObject;

                OrderGoods orderGoods = new OrderGoods();

                if(null == orderGoods){
                    result.setCode(CodeEnum.ShopGoodsIsOverdue.getValue());
                    result.setMessage(CodeEnum.ShopGoodsIsOverdue.getDescription());
                    return result;
                }

                orderGoods.setOrderId(id);
                String goodsId = (String) map2.get("id");
                Integer amount = (Integer) map2.get("amount");
                ShopGoods shopGoods = shopGoodsMapper.selectByPrimaryKey(goodsId);
                shopGoodsList.add(shopGoods);
                Double price = shopGoods.getPrice();

                // 商品id
                orderGoods.setGoodsId(goodsId);
                // 商品数量
                orderGoods.setAmount(amount);
                // 商品价格
                orderGoods.setUnitPrice(price);
                // 商品总钱数
                orderGoods.setTotalPrice(price * amount);
                orderTotalAmount += price * amount;
                stringBufferGoodsName.append(shopGoods.getName());
                stringBufferGoodsName.append(", ");
                this.goodsMapper.insertSelective(orderGoods);
            }
            // 应付
            order.setOrderAmount(orderTotalAmount);
        }
        // 购物数量
        order.setOrderQuantity(orderQuantity);
        order.setShopGoodsList(shopGoodsList);

        /*修改优惠券的状态为锁定*/
        // update by zhaotong 2015/05/29 09:50
        if (StringUtils.isNotBlank(orderParam.getCouponId())) {
            SysCoupon sysCoupon = this.couponMapper.selectByPrimaryKey(orderParam.getCouponId());
            if (null != sysCoupon) {
                if (OrderConst.COUPON_LOCK.equals(sysCoupon.getStatus())) { // 锁定
                    result.setCode(CodeEnum.CouponIsLock.getValue());
                    result.setMessage(CodeEnum.CouponIsLock.getDescription());
                    return result;
                } else if (DateUtil.compareDate(new Date(System.currentTimeMillis() / 86400000 * 86400000 - (23 - Calendar.ZONE_OFFSET) * 3600000), sysCoupon.getExpirationDate())) {// 过期
                    result.setCode(CodeEnum.CouponIsOverdue.getValue());
                    result.setMessage(CodeEnum.CouponIsOverdue.getDescription());
                    return result;
                } else {
                    sysCoupon.setStatus(OrderConst.COUPON_LOCK);
                    this.couponMapper.updateByPrimaryKeySelective(sysCoupon);
                    order.setOrderReduce(sysCoupon.getAmount());
                }
            } else {
                result.setCode(CodeEnum.CouponNone.getValue());
                result.setMessage(CodeEnum.CouponNone.getDescription());
                return result;
            }
        } else {
            order.setOrderReduce(0.0);
        }

        // 实付金额 如果有优惠券的话就减去优惠券的价格 生成最总付款金额
        order.setOrderPaid(orderTotalAmount);
        if(null != order.getOrderReduce() && order.getOrderReduce()-0 > 0){
            double orderPaid = 0.0;
            if(orderTotalAmount - order.getOrderReduce()<=0){
                orderPaid = 0.0;
            }else{
                orderPaid = orderTotalAmount - order.getOrderReduce();
            }
            order.setOrderPaid(orderPaid);
        }


        // 判断支付方式
        if(OrderConst.PAYMENT_TYPE_ZHIFUBAO == orderParam.getPaymentType() && (order.getOrderPaid()-0 > 0)){
            order.setPaymentType(19); // 帮客户端转换
            order.setPaymentTypeName(orderParam.getPaymentTypeName());
            /*初始化订单流程状态*/
            String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.SHOP_STATUS_KEY_PREFIX + OrderConst.STATUS_START_DAIFUKUAN);
            order.setFlowStatus(OrderConst.STATUS_START_DAIFUKUAN);
            order.setFlowStatusName(flowStatusStr);

            // 生成ping ++ 订单对象
            ChargeUtils ce = new ChargeUtils();
            System.out.println("---------创建charge");
            Pingpp.apiKey = OrderConst.PINGPP_APIKEY;

            Map<String, Object> chargeMap = new HashMap<String, Object>();
            java.text.DecimalFormat df  =new DecimalFormat("#");

            chargeMap.put("amount", df.format(order.getOrderPaid() * 100));
            chargeMap.put("currency", "cny");
            chargeMap.put("subject", "国安社区商品购买subject");
            chargeMap.put("body", stringBufferGoodsName);
            chargeMap.put("order_no", id);
            chargeMap.put("channel", "alipay");//
            chargeMap.put("client_ip", "127.0.0.1");
            Map<String, String> app = new HashMap<String, String>();
            app.put("id",OrderConst.PINGPP_APPID);
            chargeMap.put("app", app);
            try {
                //发起交易请求
                charge = Charge.create(chargeMap);
                order.setChargeId(charge.getId());
            } catch (PingppException e) {
                e.printStackTrace();
            }

        } else if (OrderConst.PAYMENT_TYPE_XIANJIN == orderParam.getPaymentType() || (order.getOrderPaid()-0 == 0)){
            order.setPaymentType(9);  // 帮客户端转换
            order.setPaymentTypeName(orderParam.getPaymentTypeName());
            /*初始化订单流程状态*/
            String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.SHOP_STATUS_KEY_PREFIX + OrderConst.STATUS_START_INT);
            order.setFlowStatus(OrderConst.STATUS_START_INT);
            order.setFlowStatusName(flowStatusStr);
            /*插入流程状态表*/
            OrderGoodsInfo flowStatus = this.getFlowStatus(id, user, OrderConst.STATUS_START_INT, flowStatusStr);
            this.goodsInfoMapper.insertSelective(flowStatus);


            // 推送消息
            String title_android = "购物下单成功";
            String content_android = "您有一条购物订单！";

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
        }

        // 插入 调整位置 update by zhaotong
        this.orderMapper.insertSelective(order);

        // 购物下单成功
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString().replace("-", ""));
        message.setOrderId(order.getOrderId());
        message.setServiceType("shop");
        message.setTitle("购物下单成功");
        message.setContent("国安侠已经收到小主购物清单~马上给您添置！");
        message.setUserId(user.getUserId());
        message.setCreateTime(new Date());
        messageMapper.insert(message);

        JsonConfig jsonConfig = super.getJsonConfig();
        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());
        // 购物模块 返回对象不同 返回
        BeanUtils.copyProperties(order, orderShop);
        if(null != charge){
            orderShop.setCharge(charge);
            orderShop.setChargeString(charge.toString());
        }

		/*数据格式化*/
        JSONObject orderJson = JSONObject.fromObject(orderShop, jsonConfig);
        logger.debug(orderJson.toString());
        result.setData(orderJson);

        return result;
    }

    /**
     * 取得订单列表
     *
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
        record.put("serviceType", orderParam.getServiceType());
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
        List<Order> orderList = this.orderGoodsMapper.getOrderList(record);

        if (ListUtils.isEmpty(orderList)) {
            result.setCode(CodeEnum.OrderGetNull.getValue());
            result.setMessage(CodeEnum.OrderGetNull.getDescription());
            return result;
        }
//        List<Map<String, Object>> shopGoodsList = new ArrayList<Map<String, Object>>();
        for (Order order : orderList){

            OrderGoodsCriteria orderGoodsCriteria = new OrderGoodsCriteria();
            orderGoodsCriteria.createCriteria().andOrderIdEqualTo(order.getOrderId());
            List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsCriteria);

            int orderQuantity = 0;
            if(!ListUtils.isEmpty(orderGoodsList)){
                for (OrderGoods orderGoods : orderGoodsList){
                    ShopGoods shopGoods = shopGoodsMapper.selectByPrimaryKey(orderGoods.getGoodsId());
                    if(null != shopGoods){
                        orderQuantity += 1;
                    }
                }
            }
            order.setOrderQuantity(orderQuantity);
        }

        int count = this.orderGoodsMapper.getOrderListCount(record);
        int pageCount = (int) Math.ceil((double) count / lineNum);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pageNum", pageNum);
        resultMap.put("pageCount", pageCount);
        resultMap.put("orderList", orderList);

		/*数据格式化*/
        JsonConfig jsonConfig = super.getJsonConfig();
        JSONObject resultMapJson = JSONObject.fromObject(resultMap, jsonConfig);
        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());
        result.setData(resultMapJson);

        return result;
    }

    /**
     * 更新订单状态
     *
     * @param jsonString
     * @author gaochao
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
                OrderConst.SHOP_STATUS_KEY_PREFIX + status);

		/*插入流程状态表*/
        OrderGoodsInfo flowStatus = this.getFlowStatus(id, user, status, orderStatusStr);
        this.goodsInfoMapper.insertSelective(flowStatus);

        Map<String, Object> statusMap = new HashMap<String, Object>();
        statusMap.put("id", id);
        statusMap.put("status", status);
        statusMap.put("orderStatusStr", orderStatusStr);

		/*更新订单表状态*/
        this.orderMapper.updateOrderStatus(statusMap);

		/*更新redis*/
//		String orderKey = super.getOrderRedisKey(id);
//		OrderExpress order = (OrderExpress) this.redisService.getObject(orderKey,
//				OrderExpress.class);
//		order.setFlowStatus(status);
//		order.setFlowStatusName(orderStatusStr);
//		this.redisService.setObject(orderKey, order);
//		this.redisService.setExpire(orderKey, OrderConst.EXPIRE_TIME);

//		JsonConfig jsonConfig = super.getJsonConfig();
//		String orderFlowKey = super.getOrderFlowRedisKey(id);
//		this.redisService.setListLeftPush(orderFlowKey,
//				JSONObject.fromObject(flowStatus, jsonConfig).toString());
//		this.redisService.setExpire(orderFlowKey, OrderConst.EXPIRE_TIME);

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

        OrderExpress order = (OrderExpress) JSONObject.toBean(paramsObject, OrderExpress.class);
        String id = paramsObject.getString("orderId");
        order.setExpressId(id);

        this.expressMapper.updateByPrimaryKeySelective(order);

		/*更新金额*/
        Double amount = order.getOrderAmount();
        if (amount != null) {
            this.orderMapper.updateAmountById(id, amount);
        }

        order = this.expressMapper.selectByExpressId(id);

		/*更新redis*/
//		String orderKey = super.getOrderRedisKey(id);
//		this.redisService.setObject(orderKey, order);
//		this.redisService.setExpire(orderKey, OrderConst.EXPIRE_TIME);

        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());

        return result;
    }

    @Override
    public Result getOrder(String jsonString) {
        Result result = new Result();

        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
        JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

        String orderId = paramsObject.getString("id");

        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(null == order){
            result.setCode(CodeEnum.OrderGetNull.getValue());
            result.setMessage(CodeEnum.OrderGetNull.getDescription());
            return result;
        }

        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("order", order);

        OrderGoodsCriteria orderGoodsCriteria = new OrderGoodsCriteria();
        orderGoodsCriteria.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsCriteria);

        List<Map<String, Object>> shopGoodsList = new ArrayList<Map<String, Object>>();

        int orderQuantity = 0;
        if(!ListUtils.isEmpty(orderGoodsList)){
            for (OrderGoods orderGoods : orderGoodsList){
                ShopGoods shopGoods = shopGoodsMapper.selectByPrimaryKey(orderGoods.getGoodsId());
                if(null != shopGoods){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("price", orderGoods.getUnitPrice());
                    map.put("amount", orderGoods.getAmount());
                    map.put("goods", shopGoods);
                    shopGoodsList.add(map);
                    orderQuantity += 1;
                }

            }
        }
        order.setOrderQuantity(orderQuantity);
        orderMap.put("shopGoodsList", shopGoodsList);

        List<Object> flowStatusJsonList = this.redisService.getList(super.getOrderFlowRedisKey(orderId));
        if (flowStatusJsonList == null || flowStatusJsonList.isEmpty()) {

            OrderGoodsInfoCriteria flowStatusCriteria = new OrderGoodsInfoCriteria();
            flowStatusCriteria.createCriteria().andOrderIdEqualTo(orderId);
            flowStatusCriteria.setOrderByClause("create_time desc");
            List<OrderGoodsInfo> flowStatusList = this.flowStatusMapper.selectByExample(flowStatusCriteria);
            orderMap.put("flowStatus", flowStatusList);
        } else {
            orderMap.put("flowStatus", flowStatusJsonList);
        }

        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());

		/*时间日期格式化*/
        JsonConfig jsonConfig = super.getJsonConfig();
        JSONObject washOrderJson = JSONObject.fromObject(orderMap, jsonConfig);

        result.setData(washOrderJson);

        return result;
    }

    @Override
    public Result pay(String jsonString) {
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

        String orderId = paramsObject.getString("orderId");

        if(StringUtils.isEmpty(orderId)){
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
            return result;
        }


        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderShop orderShop = new OrderShop();

        if(null == orderId){
            result.setCode(CodeEnum.OrderGetErr.getValue());
            result.setMessage(CodeEnum.OrderGetErr.getDescription());
            return result;
        }

        Charge charge = null;
        // 判断支付方式
        // 这地方写死了, 以后进行修改.
        if(19 == order.getPaymentType()){

            // 生成ping ++ 订单对象
            ChargeUtils ce = new ChargeUtils();
            System.out.println("---------创建charge");
            Pingpp.apiKey = OrderConst.PINGPP_APIKEY;
            Map<String, Object> chargeMap = new HashMap<String, Object>();
            java.text.DecimalFormat df  =new DecimalFormat("#");

            chargeMap.put("amount", df.format(order.getOrderAmount() * 100));
            chargeMap.put("currency", "cny");
            chargeMap.put("subject", "国安社区商品购买suject");
            chargeMap.put("body", "国安社区商品购买body");
            chargeMap.put("order_no", order.getOrderId());
            chargeMap.put("channel", "alipay");
            chargeMap.put("client_ip", "127.0.0.1");
            Map<String, String> app = new HashMap<String, String>();
            app.put("id",OrderConst.PINGPP_APPID);
            chargeMap.put("app", app);
            try {
                //发起交易请求
                charge = Charge.create(chargeMap);
            } catch (PingppException e) {
                e.printStackTrace();
            }

        } else {
            // 商品付款类型不是支付宝
            result.setCode(CodeEnum.PaymentTypeIsNotZhifubao.getValue());
            result.setMessage(CodeEnum.PaymentTypeIsNotZhifubao.getDescription());
            return result;
        }

        JsonConfig jsonConfig = super.getJsonConfig();
        result.setCode(CodeEnum.success.getValue());
        result.setMessage(CodeEnum.success.getDescription());

        ChargeObject chargeObject = new ChargeObject();
        chargeObject.setCharge(charge);
        chargeObject.setChargeString(charge.toString());

//        JSONObject jsonObject = JSONObject.fromObject(chargeObject);
        result.setData(chargeObject);

        return result;
    }

    class ChargeObject {
        private Charge charge;
        private String chargeString;

        public String getChargeString() {
            return chargeString;
        }
        public void setChargeString(String chargeString) {
            this.chargeString = chargeString;
        }
        public Charge getCharge() {
            return charge;
        }
        public void setCharge(Charge charge) {
            this.charge = charge;
        }

    }

    @Override
    @Transactional
    public String callback(String jsonString) {
        Result result = new Result();
        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
        if(StringUtils.isNotEmpty(jsonString) && null != requestJsonObject){
            Boolean paid = requestJsonObject.getBoolean("paid");
            if(paid){
                String orderNo = requestJsonObject.getString("order_no");
                Order order = orderMapper.selectByPrimaryKey(orderNo);
                if(null != order){

                    AppUserInfo appUserInfo = appUserInfoMapper.selectByPrimaryKey(order.getUserId());
                    if(null != order.getUserId()){
                        Order newOrder = new Order();
                        newOrder.setOrderId(order.getOrderId());
                        String flowStatusStr = SysConstants.getOrderStatusConfig().getProperty(OrderConst.SHOP_STATUS_KEY_PREFIX + OrderConst.STATUS_START_INT);
                        // 修改订单状态
                        newOrder.setFlowStatus(OrderConst.STATUS_START_INT);
                        newOrder.setFlowStatusName(flowStatusStr);
                        newOrder.setPayTime(new Date());
                        this.orderMapper.updateByPrimaryKeySelective(newOrder);

                        /*插入流程状态表*/
                        OrderGoodsInfo orderGoodsInfo = this.getFlowStatus(orderNo, appUserInfo, OrderConst.STATUS_START_INT, flowStatusStr);
                        this.goodsInfoMapper.insertSelective(orderGoodsInfo);
                        logger.info("支付宝回调成功 -> 订单id "+order.getOrderId()+" 。");

                        // 推送消息
                        String title_android = "购物下单成功";
                        String content_android = "您有一条购物订单！";

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

                    }
                    return "success";
                }
            }
        }
        return "fail";
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
    private OrderGoodsInfo getFlowStatus(String orderId, AppUserInfo user, Integer orderStatus, String orderStatusStr) {

        OrderGoodsInfo flowStatus = new OrderGoodsInfo();
        flowStatus.setOrderId(orderId);
        flowStatus.setCreateTime(new Date());
        flowStatus.setFlowStatus(orderStatus);
        flowStatus.setUserId(user.getUserId());
        flowStatus.setFlowStatusName(orderStatusStr);
        flowStatus.setUserType(OrderConst.USER);
        return flowStatus;
    }

}
