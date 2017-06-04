package com.guoan.utils;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: AppUtils.java
 * @Package com.guoan.utils
 * @Description: 系统使用的特殊工具类 简化代码编写.
 * @author 赵彤
 * @date 2013-10-11 上午09:42:46
 * @version V1.0
 */
public class AppUtils {
	private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);

	/**
	 * User转SessionInfo.
	 * 
	 * @param user
	 * @return

	public static SessionInfo userToSessionInfo(User user) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setUserId(user.getId());
		sessionInfo.setLoginName(user.getLoginname());
		sessionInfo.setIp(getIp());
		return sessionInfo;
	}
     */
	/**
	 * 将用户放入session中.
	 * 
	 *

	public static void putUserToSession(User user) {
		String sessionId = httpServletRequest.getSession().getId();
		logger.debug("putUserToSession:{}", sessionId);
		AppConstants.sessionUser.put(sessionId, userToSessionInfo(user));
	}
     */
	public static void removeUserFromSession(String sessionId) {
		if (StringUtils.isNotBlank(sessionId)) {
			Set<String> keySet = AppConstants.sessionUser.keySet();
			for (String key : keySet) {
				if (key.equals(sessionId)) {
					logger.debug("removeUserFromSession:{}", sessionId);
					AppConstants.sessionUser.remove(key);
				}
			}
		}
	}

}
