package com.guoan.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.guoan.entity.base.common.SessionInfo;

/**
 * @Title: AppConstants.java
 * @Package com.guoan.utils
 * @Description: 系统使用的静态变量.
 * @author 赵彤
 * @date 2013-10-11 上午09:42:22
 * @version V1.0
 */
public class AppConstants {
	/**
	 * 修改用户密码 个人(需要输入原始密码)
	 */
	public static final String USER_UPDATE_PASSWORD_YES = "1";
	/**
	 * 修改用户密码 个人(不需要输入原始密码)
	 */
	public static final String USER_UPDATE_PASSWORD_NO = "0";

	/**
	 * 超级管理员
	 */
	public static final String ROLE_SUPERADMIN = "超级管理员";

	/**
	 * 在线用户列表.
	 */
	public static final Map<String, SessionInfo> sessionUser = new ConcurrentHashMap<String, SessionInfo>();

}
