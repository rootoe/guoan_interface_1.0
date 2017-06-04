package com.easemob.server.example.comm;

import com.guoan.utils.SysConstants;

/**
 * @Title: Constants.java
 * @Package com.easemob.server.example.comm
 * @Description: 环信用到的常量
 * @author 赵彤
 * @date 2015年4月29日 下午5:15:30
 * @version V1.0
 */
public interface Constants {

	// API_HTTP_SCHEMA
	public static String API_HTTP_SCHEMA = SysConstants.getHuanXinRestAPIConfig().getProperty("API_HTTP_SCHEMA");
	// API_SERVER_HOST
	public static String API_SERVER_HOST = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("API_SERVER_HOST");
	// API_SERVER_PORT
	public static String API_SERVER_PORT = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("API_SERVER_PORT");
	// APPKEY
	public static String APPKEY = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("APPKEY");
	// APP_CLIENT_ID
	public static String APP_CLIENT_ID = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("APP_CLIENT_ID");
	// APP_CLIENT_SECRET
	public static String APP_CLIENT_SECRET = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("APP_CLIENT_SECRET");
	// APP_ADMIN_USERNAME
	public static String APP_ADMIN_USERNAME = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("APP_ADMIN_USERNAME");
	// APP_ADMIN_PASSWORD
	public static String APP_ADMIN_PASSWORD = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("APP_ADMIN_PASSWORD");
	// DEFAULT_PASSWORD
	public static String DEFAULT_PASSWORD = "1234456";

	// DataMigration
	// SOURCE_SVR_HOST
	public static String SOURCE_SVR_HOST = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("SOURCE_SVR_HOST");
	// TARGET_SVR_HOST
	public static String TARGET_SVR_HOST = SysConstants.getHuanXinRestAPIConfig().getProperties().getProperty("TARGET_SVR_HOST");

}
