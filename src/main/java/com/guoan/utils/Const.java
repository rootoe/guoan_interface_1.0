package com.guoan.utils;

import java.util.concurrent.TimeUnit;

public class Const {

	/**
	 * 方法返回值
	 */
	public static final Integer RETURN_CORRECT = 1; // 使用
	public static final Integer RETURN_WRONG = 0; // 未使用

	/**
	 * 昵称最短长度
	 */
	public static final Integer NICKNAME_LENGTH = 3;

	/**
	 * 是否使用 1:使用 0:未使用
	 */
	public static final Integer ISUSED = 1; // 使用
	public static final Integer ISNOTUSED = 0; // 未使用


	// 电话号码--------------START-------------------
	public static final String REGEX_PHONE_NUM_MAINLAND = "^((\\+86)|(86))?(1\\d?)\\d{9}$";// 大陆
	public static final String REGEX_PHONE_NUM_HONGKONG = "^((\\+852)|(852))?([5,6,9])\\d{7}$";// 香港
	// 电话号码--------------END---------------------
	
	// 性别--------------START-------------------
	public static final Integer SEX_MAN = 1;// 男
	public static final Integer SEX_WOMAN = 2;// 女
	public static final Integer SEX_UNKNOWN = 3;// 未知
	// 性别--------------END---------------------
	

	// ----------------------AliyunOSS---Begin-----------------
	public static final String ACCESSKEYID = "auop2HiDHf9xPuiN";
	public static final String ACCESSKEYSECRET = "fXdJB97XvMJMVGO7H8vkbS4qmh9Z05";
	public static final String BUCKET = "static-xinzhixu";
	// ----------------------AliyunOSS---End-------------------

	// ----------------------代理---begin-------------------
	public static final String PROXY_HOST = SysConstants.getGuoanConfig().getProperty("proxy_host");// 云通讯代理地址
	public static final int PROXY_PORT = SysConstants.getGuoanConfig().getInteger("proxy_port");// 云通讯代理端口
	public static final String PROXY_USERNAME = "";// 云通讯代理用户名
	public static final String PROXY_PASSWD = "";// 云通讯代理密码
	// ----------------------代理---begin-------------------

	// ----------------------YunTongXun---Begin-----------------
	public static final String YUNTONGXUN_REST_PORT = "8883";// rest端口
	public static final String YUNTONGXUN_CODE_SURVIVAL_TIME_DEFAULT = "30";// 默认验证码生存时间(分钟)
	public static final String YUNTONGXUN_MODEL_YANZHENGMA = "22782";// 验证码
	public static final String YUNTONGXUN_MODEL_WASH_YIXIHAO = "22591";// 衣服已洗好
	//add drug(处方药) on 2015-09-15
	public static final String YUNTONGXUN_MODEL_DRUG_YIGOUYAO = "22591";// 药品已备好
	//add massage(康复按摩) on 2015-09-25 
	public static final String YUNTONGXUN_MODEL_MASSAGE_KAISHIFUWU = "22591"; //服务中

	/* 沙盒环境账户信息 */
	public static final String YUNTONGXUN_REST_HOST_SAND = "sandboxapp.cloopen.com";// rest地址
	public static final String YUNTONGXUN_ACCOUNT_SID_SAND = "aaf98f894cfa16bc014d0347200c06f7";// 主账户id
	public static final String YUNTONGXUN_ACCOUNT_TOKEN_SAND = "fda05a31bb4a56649a5a63fc6def748a";// 主账户token
	public static final String YUNTONGXUN_APP_ID_SAND = "aaf98f894cfa16bc014d0347200c06f7";// 应用id

	/* 生产环境账户信息 */
	public static final String YUNTONGXUN_REST_HOST_FINAL = "app.cloopen.com";// 生产地址
	public static final String YUNTONGXUN_ACCOUNT_SID_FINAL = "8a48b5514cfa209e014cff0fb170040e";// 生产主账户id
	public static final String YUNTONGXUN_ACCOUNT_TOKEN_FINAL = "dd2b3f99c2614bdaadd44daf5ff3b693";// 生产主账户token
	public static final String YUNTONGXUN_APP_ID_FINAL = "aaf98f894cfa16bc014d0347200c06f7";// 应用id

	public static final boolean YUNTONGXUN_SAND_COUNT_SWITCH = false; // 云通讯是否使用沙盒账号
	// true：使用
	// false：不使用
	public static final boolean YUNTONGXUN_SWITCH = true;// 云通讯是否开启短信通知
	public static final boolean YUNTONGXUN_PROXY_SWITCH = false;// 云通讯是否开启代理
	public static final boolean YUNTONGXUN_PROXY_ANONYMOUS_SWITCH = false;// 云通讯代理匿名开关
	// ----------------------开关设置----End-----------------
	// ----------------------YunTongXun---End-------------------

	// ----------------------AliyunOCS---Begin-----------------
	// 控制台上的“内网地址”
	public static String OCS_HOST = SysConstants.getGuoanConfig().getProperty("ocs_host");
	// 默认端口 11211，不用改
	public static String OCS_PORT = SysConstants.getGuoanConfig().getProperty("ocs_port", "11211");
	// 控制台上的“访问账号”
	public static String OCS_USERNAME = SysConstants.getGuoanConfig().getProperty("ocs_username");
	// 邮件或短信中提供的“密码”
	public static String OCS_PASSWORD = SysConstants.getGuoanConfig().getProperty("ocs_password");
	// ----------------------AliyunOCS---End-------------------

	// ----------------------缓存---Begin-----------------
	// 默认缓存时间
	public static final int MEMCACHED_DEFAULT_TIME = 3;
	// 注册验证码缓存生存时间
	public static int MEMCACHED_IDENTIFYING_CODE_LIVE_TIME = SysConstants.getGuoanConfig().getInteger("memcache.identifyingCodeLiveTime", Const.MEMCACHED_DEFAULT_TIME);
	// 注册验证码缓存生存时间
	public static int MEMCACHED_TOKEN_CODE_LIVE_TIME = SysConstants.getGuoanConfig().getInteger("memcache.tokenCodeLiveTime", Const.MEMCACHED_DEFAULT_TIME);
	// 等待5秒
	public static int DEFAULT_TIMEOUT = 5;
	public static TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;
	
	//树形结构KEY
	public static String SYS_ADDRESS_TREE = "sysAddressTree";
	// ----------------------缓存---End-------------------

	// 看是开发模式还是测试模式
	public static final boolean IS_ONLINE = true;
	public static boolean IS_PROXY = SysConstants.getGuoanConfig().getBoolean("is_proxy");
	public static final String DEV_PROPERTIES = "conf\\dev\\dev.properties";
	public static final String ONLINE_PROPERTIES = "conf\\online.properties";

	// ----------------------腾讯 信鸽通讯---Begin-----------------
	public final static long accessId = 2200052821l;
	public final static String accessKey = "I5561KD2ZWKW";
	public final static String secretKey = "c50e19d5dd17a54dd81881d3e532744d";
	// ----------------------腾讯 信鸽通讯---End-----------------

	//----------------------------版本更新级别-Start----------------------------
	public final static Integer UPDATE_NONE = 0;
	public final static Integer UPDATE_UN_COMPEL = 1;
	public final static Integer UPDATE_COMPEL = 2;
	//----------------------------版本更新级别-End----------------------------

	//----------------------------服务人员常量配置信息-Start----------------------------
	public final static String WORK_APP_INFO_NOTICE = "notice";
	public final static String WORK_APP_INFO_ABOUTUS = "aboutUs";
	public final static String WORK_APP_INFO_FAQ = "faq";
	//----------------------------服务人员常量配置信息-End----------------------------
	
	// 功能开关--------------START-------------------
	public static final String SWITCH_ON = "on";
	public static final String SWITCH_OFF = "off";
	// 功能开关--------------START-------------------

	// 通知后台消息
	public static String CHAT_MESSAGE_NOTIFY_BACKGROUND_URL_1 = SysConstants.getGuoanConfig().getProperty("chat_message_notify_background_url_1");
	public static String CHAT_MESSAGE_NOTIFY_BACKGROUND_URL_2 = SysConstants.getGuoanConfig().getProperty("chat_message_notify_background_url_2");
}
