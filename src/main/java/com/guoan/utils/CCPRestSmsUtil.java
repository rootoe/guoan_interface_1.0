package com.guoan.utils;

import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuntongxun.ccpsdk.CCPRestSmsSDK;

public class CCPRestSmsUtil {
	
	static CCPRestSmsSDK restAPI;
	static HashMap<String, Object> result = null;
	private static final Logger logger = LoggerFactory.getLogger(CCPRestSmsUtil.class);
	
	private String phoneNum;
	private String model;
	private String identifyingCode;
	private String survivalTime;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

	public String getSurvivalTime() {
		return survivalTime;
	}

	public void setSurvivalTime(String survivalTime) {
		this.survivalTime = survivalTime;
	}

	/**
	 * 初始化CCPRestSmsSDK
	 */
	public static void init() {
		String serviceHostPort = "";
		String servierHostName = "";
		String accountSID = "";
		String accountToken = "";
		String appId = "";

		//初始化SDK
		restAPI = new CCPRestSmsSDK();
		//判断测试账户是否打开
//		String usingAccount = Const.YUNTONGXUN_SAND_COUNT_SWITCH ? "SAND" : "FINAL";
		String usingAccount = "FINAL";
		try {
			servierHostName = Const.class.getDeclaredField("YUNTONGXUN_REST_HOST_" + usingAccount).get(null).toString();
			serviceHostPort = Const.YUNTONGXUN_REST_PORT;
			accountSID = Const.class.getDeclaredField("YUNTONGXUN_ACCOUNT_SID_" + usingAccount).get(null).toString();
			accountToken = Const.class.getDeclaredField("YUNTONGXUN_ACCOUNT_TOKEN_" + usingAccount).get(null).toString();
			appId = Const.class.getDeclaredField("YUNTONGXUN_APP_ID_" + usingAccount).get(null).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//******************************注释*********************************************
		//*初始化服务器地址和端口                                                       *
		//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
		//*******************************************************************************
		restAPI.init(servierHostName, serviceHostPort);
		
		//******************************注释*********************************************
		//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
		//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
		//*******************************************************************************
		restAPI.setAccount(accountSID, accountToken);
		
		
		//******************************注释*********************************************
		//*初始化应用ID                                                                 *
		//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
		//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		//*******************************************************************************
		restAPI.setAppId(appId);
	}

	/**
	 * 发送信息 -- 验证码
	 * @param phoneNum
	 * @param model
	 * @param identifyingCode
	 * @param survivalTime
	 */
	public static synchronized void sendIdentifiyCode(String phoneNum, String model, String identifyingCode, String survivalTime) {

		//******************************注释****************************************************************
		//*调用发送模板短信的接口发送短信                                                                  *
		//*参数顺序说明：                                                                                  *
		//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
		//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
		//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		//*第三个参数是要替换的内容数组。																														       *
		//**************************************************************************************************
		
		//**************************************举例说明***********************************************************************
		//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
		//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
		//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
		//*********************************************************************************************************************
		CCPRestSmsUtil.init();
		Thread thread = new Thread(()-> {
			result = restAPI.sendTemplateSMS(phoneNum, model, new String[]{identifyingCode, survivalTime});

			logger.info("SDKTestGetSubAccounts result=" + result);
			if ("000000".equals(result.get("statusCode"))) {
				//正常返回输出data包体信息（map）
				HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
				Set<String> keySet = data.keySet();
				for (String key : keySet) {
					Object object = data.get(key);
					logger.info("SDKTestGetSubAccounts->" + key + " = " + object);
				}
			} else {
				//异常返回输出错误码和错误信息
				logger.info("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
			}
		});
		thread.start();
	}

	/**
	 * 发送信息 -- 衣服已洗好
	 * @param phoneNum
	 * @param model
	 */
	public static synchronized void sendWashMessage(String phoneNum, String model) {
		CCPRestSmsUtil.init();
		Thread thread = new Thread(()-> {
			result = restAPI.sendTemplateSMS(phoneNum, model, new String[]{"30分钟内"});

			logger.info("SDKTestGetSubAccounts result=" + result);
			if ("000000".equals(result.get("statusCode"))) {
				//正常返回输出data包体信息（map）
				HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
				Set<String> keySet = data.keySet();
				for (String key : keySet) {
					Object object = data.get(key);
					logger.info("SDKTestGetSubAccounts->" + key + " = " + object);
				}
			} else {
				//异常返回输出错误码和错误信息
				logger.info("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
			}
		});
		thread.start();
	}
	
	/**
	 * 发送信息 -- 药品已备好
	 * @param phoneNum
	 * @param model
	 */
	public static synchronized void sendDrugMessage(String phoneNum, String model) {
		CCPRestSmsUtil.init();
		Thread thread = new Thread(()-> {
			result = restAPI.sendTemplateSMS(phoneNum, model, new String[]{"30分钟内"});

			logger.info("SDKTestGetSubAccounts result=" + result);
			if ("000000".equals(result.get("statusCode"))) {
				//正常返回输出data包体信息（map）
				HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
				Set<String> keySet = data.keySet();
				for (String key : keySet) {
					Object object = data.get(key);
					logger.info("SDKTestGetSubAccounts->" + key + " = " + object);
				}
			} else {
				//异常返回输出错误码和错误信息
				logger.info("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
			}
		});
		thread.start();
	}
	
	/**
	 * @MethodName：sendMassageMessage
	 * @Description： 发送按摩信息
	 * @param phoneNum
	 * @param model
	 */
	public static synchronized void sendMassageMessage(String phoneNum, String model) {
		CCPRestSmsUtil.init();
		Thread thread = new Thread(()-> {
			result = restAPI.sendTemplateSMS(phoneNum, model, new String[]{"30分钟内"});

			logger.info("SDKTestGetSubAccounts result=" + result);
			if ("000000".equals(result.get("statusCode"))) {
				//正常返回输出data包体信息（map）
				HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
				Set<String> keySet = data.keySet();
				for (String key : keySet) {
					Object object = data.get(key);
					logger.info("SDKTestGetSubAccounts->" + key + " = " + object);
				}
			} else {
				//异常返回输出错误码和错误信息
				logger.info("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
			}
		});
		thread.start();
	}
}
