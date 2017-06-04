package com.guoan.utils;

/**
 * @Title: OrderConst.java
 * @Package com.guoan.controller.work
 * @Description: 订单常量
 * @author 赵彤
 * @date 2015年06月01日 下午3:55:25
 * @version V1.0
 */
public class OrderConst {

	//-----------------------订单状态---------------------
	public final static int STATUS_START_DAIFUKUAN = -1;
	public final static int STATUS_START_INT = 0;
	public final static int STATUS_CANCEL_INT = 900;

	// 流转状态前缀
	public final static String WASH_STATUS_KEY_PREFIX = "wash.flow.status.";
	public final static String EXPRESS_STATUS_KEY_PREFIX = "express.flow.status.";
	public final static String SHOP_STATUS_KEY_PREFIX = "shop.flow.status.";
	public final static String REHAB_STATUS_KEY_PREFIX = "rehab.flow.status.";
	public final static String CLEAN_STATUS_KEY_PREFIX = "clean.flow.status.";
	public final static String MAINTAIN_STATUS_KEY_PREFIX = "maintain.flow.status.";
	public final static String PAY_STATUS_KEY_PREFIX = "pay.flow.status.";
	//add drugFlowStatus  ON 2015-09-15
	public final static String DRUG_STATUS_KEY_PREFIX = "drug.flow.status.";
	//add massageFlowStatus ON 2015-09-24
	public final static String MASSAGE_STATUS_KEY_PREFIX = "massage.flow.status.";

	// 服务类型
	public final static String SERVICE_TYPE_WASH = "wash"; // 洗衣
	public final static String SERVICE_TYPE_EXPRESS = "express"; // 快递
	public final static String SERVICE_TYPE_SHOP = "shop"; // 购物
	public final static String SERVICE_TYPE_REHAB = "rehab"; // 康复护理
	public final static String SERVICE_TYPE_CLEAN = "clean"; // 保洁
	public final static String SERVICE_TYPE_MAINTAIN = "maintain"; // 维修
	public final static String SERVICE_TYPE_PAY = "pay"; // 代缴
	//add service_type_drug  ON 2015-09-15
	public final static String SERVICE_TYPE_DRUG = "drug"; // 处方药
	//add service_type_massage ON 2015-09-24
	public final static String SERVICE_TYPE_MASSAGE = "massage";//康复按摩

	public final static int ORDER_STATUS_NORMAL = 1;

	// 过期时间
	public final static int EXPIRE_TIME = 7 * 24;
	public final static String[] DATE_FORMAT = new String[] { "yyyy-MM-dd HH:mm:ss" };

	// 用户类型
	public final static String WORKER = "worker";
	public final static String USER = "user";

	// 快递类型
	public final static String EXPRESS_SEND = "send";
	public final static String EXPRESS_RECEIVE = "receive";

	// 优惠券状态
	public final static String COUPON_BIND = "bind";
	public final static String COUPON_LOCK = "lock";
	public final static String COUPON_USED = "used";
	public final static String COUPON_OVERDUE = "overdue";

	// 下单类型
	public final static int ORDER_TYPE_APP = 1;
	public final static int ORDER_TYPE_VOICE = 2;
	public final static int ORDER_TYPE_400 = 3;
	public final static int ORDER_TYPE_STORE = 4;

	// 支付方式
	public final static int PAYMENT_TYPE_ZHIFUBAO = 1;
	public final static int PAYMENT_TYPE_XIANJIN = 0;

	// 取消状态
	public final static int IsCancel = 1;
	public final static int IsNotCancel = 0;

	// 评价状态
	public final static int IsEvaluation = 1;
	public final static int IsNotEvaluation = 0;

	// 状态流转 公共
	public final static int FLOW_STATUS_DIAODUZHONG=0; // 调度中
	public final static int FLOW_STATUS_JIEDANCHENGGONG=10; // 接单成功
	public final static int FLOW_STATUS_YIWANCHENG=40; // 已完成
	public final static int FLOW_STATUS_YIQUXIAO=900; // 已取消

	// 状态流转 洗衣
	public final static int FLOW_STATUS_WASH_YISONGXI=20;
	public final static int FLOW_STATUS_WASH_YIXIHAO=26;
	public final static int FLOW_STATUS_WASH_PEISONGZHONG=30;

	// 状态流转 快递
	public final static int FLOW_STATUS_EXPRESS_DAIYOUJI=20;
	public final static int FLOW_STATUS_EXPRESS_PEISONGZHONG=30;
	public final static int FLOW_STATUS_EXPRESS_JUSHOU=910;

	// 状态流转 购物
	public final static int FLOW_STATUS_SHOP_PEISONGZHONG=30;
	
	// add 状态流转_处方药  ON 2015-09-15  
	public final static int FLOW_STATUS_DRUG_DAISHANGMEN=20;//待上门
	public final static int FLOW_STATUS_DRUG_YIGOUYAO=26;//已购药
	public final static int FLOW_STATUS_DRUG_PEISONGZHONG=30;//配送中
	
	//add 状态流转_康复按摩  ON 2015-09-24
	public final static int FLOW_STATUS_MASSAGE_YUYUECHENGGONG=20;//预约成功
	public final static int FLOW_STATUS_MASSAGE_KAISHIFUWU=26;//开始服务
	public final static int FLOW_STATUS_MASSAGE_FUWUWANCHENG=30;//服务完成 ---对应 ---> 已完成

	// ping ++ 支付平台
	// public final static String PINGPP_APIKEY = "sk_test_1aHiD8erbX1Sy5ezT44eDWH8"; // Test Key
	public final static String PINGPP_APIKEY = "sk_live_wEQYEatVU9OjxkeRdgj7t9jV"; // Live Key
	public final static String PINGPP_APPID = "app_9mX5aT0m9mX1uDCu";



}
