package com.guoan.entity.base.common;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.guoan.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title: SessionInfo.java
 * @Package com.guoan.common.model
 * @Description: session登录用户对象.
 * @author 赵彤
 * @date 2013-10-10 下午05:07:57
 * @version V1.0
 */
@SuppressWarnings("serial")
public class SessionInfo implements java.io.Serializable {

	/**
	 * sessionID
	 */
	private String id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 登录名
	 */
	private String nickname;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 客户端IP
	 */
	private String ip;
	/**
	 * 登录时间
	 */
	private Date loginTime = new Date();;

	public SessionInfo() {
	}

	/**
	 * 
	 * @param id
	 *            sessionID
	 * @param userId
	 *            用户ID
	 * @param loginName
	 *            登录名
	 * @param userType
	 *            用户类型
	 * @param ip
	 *            客户端IP
	 * @param loginTime
	 *            登录时间
	 */
	public SessionInfo(String id, String userId, String nickname, String userType, String ip, Date loginTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.nickname = nickname;
		this.userType = userType;
		this.ip = ip;
		this.loginTime = loginTime;
	}

	/**
	 * sessionID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 sessionID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 用户ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 登录名
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置 登录名
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 用户类型
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * 设置 用户类型
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * 客户端IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置 客户端IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 登录时间
	 */
	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = BaseEntity.DATE_TIME_FORMAT, timezone = BaseEntity.TIMEZONE)
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * 设置登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
