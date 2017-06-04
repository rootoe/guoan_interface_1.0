package com.guoan.entity.base.state;

/**
 * @Title: StatusState.java
 * @Package com.guoan.entity.base.state
 * @Description: 状态标识 枚举类型.
 * @author 赵彤
 * @date 2013-10-10 下午06:11:23
 * @version V1.0
 */
public enum StatusState {
	/**
	 * 状态
	 */
	/** 已禁用(0) */
	delete(0, "已禁用"),
	/** 正常(1) */
	normal(1, "正常"),
	/** 未注册(2) */
	audit(2, "未注册"),
	/** 锁定(3) */
	lock(3, "已锁定"),

	/**
	 * 日志类型
	 */
	syslog(0, "系统日志"),
	/** 系统日志(1) */
	userlog(1, "操作日志");

	/**
	 * 值 Integer型
	 */
	private final Integer value;
	/**
	 * 描述 String型
	 */
	private final String description;

	StatusState(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * 获取值
	 * 
	 * @return value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * 获取描述信息
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	public static StatusState getStatusState(Integer value) {
		if (null == value)
			return null;
		for (StatusState _enum : StatusState.values()) {
			if (value.equals(_enum.getValue()))
				return _enum;
		}
		return null;
	}

	public static StatusState getStatusState(String description) {
		if (null == description)
			return null;
		for (StatusState _enum : StatusState.values()) {
			if (description.equals(_enum.getDescription()))
				return _enum;
		}
		return null;
	}

}