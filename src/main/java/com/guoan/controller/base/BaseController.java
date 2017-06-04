package com.guoan.controller.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.guoan.entity.base.common.SessionInfo;
import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.utils.AppConstants;

/**
 * @Title: BaseController.java
 * @Package com.guoan.controller.base
 * @Description: controller 基类
 * @author 赵彤
 * @date 2014年4月17日 下午2:51:04
 * @version V1.0
 */
public class BaseController<T> extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected static final String ERROR_MSG_KEY = "errorMsg";

	private Class<T> classT;
	protected static Logger logger;

	protected BaseController() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		classT = (Class<T>) type.getActualTypeArguments()[0];
		logger = LoggerFactory.getLogger(classT);
	}

	/**
	 * 取得客户端IP.
	 */
	public static String getIp(HttpServletRequest requst) {
		HttpServletRequest request = requst;
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获得sessionId
	 */
	public static String getSessionId(HttpServletRequest requst) {
		return requst.getSession().getId();
	}

	// ① 获取保存在Session中的用户对象
	protected AppUserInfo getSessionUser(HttpServletRequest request) {
		return (AppUserInfo) request.getSession().getAttribute(request.getSession().getId());
	}

	// ② 将用户对象保存到Session中
	protected void setSessionUser(HttpServletRequest request, AppUserInfo user) {
		request.getSession().setAttribute(request.getSession().getId(), user);
	}

	// ③ 获取基于应用程序的url绝对路径
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}

	public static void putUserToSession(AppUserInfo user, HttpServletRequest requst) {
		String sessionId = requst.getSession().getId();
		AppConstants.sessionUser.put(sessionId, userToSessionInfo(user));
	}

	public static SessionInfo userToSessionInfo(AppUserInfo user) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setUserId(user.getUserId());
		sessionInfo.setNickname(user.getNickname());
		return sessionInfo;
	}

	/**
	 * 取得HttpSession中Attribute的简化函数.
	 */
	public static Object getSessionAttribute(String name, HttpServletRequest requst) {
		HttpSession session = requst.getSession();
		return (session != null ? session.getAttribute(name) : null);
	}

	/**
	 * 取得HttpRequest中Parameter的简化方法.
	 */
	public static String getParameter(String name, HttpServletRequest requst) {
		return requst.getParameter(name);
	}

	/**
	 * 错误信息记录日志
	 */
	@ExceptionHandler
	public void exception(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException{

		ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
		logger.error("***************************************************************");
		logger.error("***************************************************************");
		logger.error("***************************************************************");
		logger.error("exceptionMessage", e.getMessage());
		logger.error(request.getRequestURL().toString());
		logger.error("***************************************************************");
		logger.error("***************************************************************");
		logger.error("***************************************************************");
		Result result = new Result();
		result.setCode(CodeEnum.error.getValue());
		result.setMessage(CodeEnum.error.getDescription());

		response.setContentType("application/json;charset=UTF-8");
		mapper.writeValue(response.getWriter(), result);
	}
}
