package com.guoan.utils;

/**
* @Title: SysConstants.java
* @Package com.guoan.common.utils
* @Description: 项目中用到的静态变量.
* @author 赵彤  
* @date 2013-10-10 下午05:40:21
* @version V1.0
 */
public class SysConstants {
    /**
     * session 验证码key
     */
    public static final String SESSION_VALIDATE_CODE = "validateCode";
    
    private static PropertiesLoader appconfig = null;
    // 开发配置文件
    private static PropertiesLoader guoanconfig = null;
    
    //订单流程状态
    private static PropertiesLoader orderStatusConfig = null;

	// 服务人员常量
	private static PropertiesLoader workAppInfoConfig = null;

	// 后台管理的相关配置
	private static PropertiesLoader backgroundConfig = null;
    
    private static String guoanConfigName = "";
    
	static {
		// 加载配置文件， 看是开发模式还是测试模式
//		System.out.println("是否开发模式：-> "+ (Const.IS_ONLINE == true ? "否" : "是"));
		if (Const.IS_ONLINE) { // true 上线模式
			guoanConfigName = Const.ONLINE_PROPERTIES;
		} else {
			guoanConfigName = Const.DEV_PROPERTIES;
		}
		System.out.println("guoan项目加载的配置文件是：-> " + guoanConfigName);
	}
    
    /**
     * 配置文件guoanConfig)
     */
    public static PropertiesLoader getGuoanConfig() {
    	if(guoanconfig == null){
    		guoanconfig = new PropertiesLoader(guoanConfigName);
    	}
        return guoanconfig;
    }
    
    /**
     * 配置文件(appconfig.properties)
     */
    public static PropertiesLoader getAppConfig() {
    	if(appconfig == null){
    		appconfig = new PropertiesLoader("appconfig.properties");
    	}
        return appconfig;
    }

	/**
	 * 服务人员常量配置文件(workAppInfo.properties)
	 */
	public static PropertiesLoader getWorkAppInfoConfig() {
		if(workAppInfoConfig == null){
			workAppInfoConfig = new PropertiesLoader("conf\\workAppInfo.properties");
		}
		return workAppInfoConfig;
	}

	/**
	 * 服务人员常量配置文件(workAppInfo.properties)
	 */
	public static PropertiesLoader getBackgroundConfig() {
		if(backgroundConfig == null){
			backgroundConfig = new PropertiesLoader("conf\\background.properties");
		}
		return backgroundConfig;
	}
    
    /**
     * 环信(HuanXinRestAPIConfig.properties)
     */
    public static PropertiesLoader getHuanXinRestAPIConfig() {
    	if(appconfig == null){
    		appconfig = new PropertiesLoader("conf\\HuanXinRestAPIConfig.properties");
    	}
        return appconfig;
    }

    /**
     * jdbc url连接参数(默认:"").
     */
    public static String getJdbcUrl(){
    	return SysConstants.getAppConfig().getProperty("jdbc.url","");
    }
    /**
     * 获取是否是开发模式(默认:false).
     */
    public static boolean isdevMode(){
    	return getAppConfig().getBoolean("devMode",false);
    }
    
	/**
	 * 获得上传表单域的名称
	 * 
	 * @return
	 */
	public static final String getUploadFieldName() {
		return getAppConfig().getProperty("uploadFieldName", "filedata");
	}

	/**
	 * 获得上传文件的最大大小限制
	 * 
	 * @return
	 */
	public static final long getUploadFileMaxSize() {
		 String uploadFileMaxSize = getAppConfig().getProperty("uploadFileMaxSize", "20971520");
		 return Long.valueOf(uploadFileMaxSize);
	}

	/**
	 * 获得允许上传文件的扩展名
	 * 
	 * @return
	 */
	public static final String getUploadFileExts() {
		return getAppConfig().getProperty("uploadFileExts","txt,rar,zip,doc,docx,xls,xlsx,jpg,jpeg,gif,png,swf,wmv,avi,wma,mp3,mid");
	}

	/**
	 * 获得上传文件要放到那个目录
	 * 
	 * @return
	 */
	public static final String getUploadDirectory() {
		return getAppConfig().getProperty("uploadDirectory", "attached");
	}

	public static PropertiesLoader getOrderStatusConfig() {
		
    	if(orderStatusConfig == null){
    		orderStatusConfig = new PropertiesLoader("conf\\orderFlowStatus.properties");
    	}
		return orderStatusConfig;
	}
    
}
