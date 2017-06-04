package com.guoan.entity.base.common;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @Title: Result.java
 * @Package com.guoan.entity.base.common
 * @Description: 返回对象
 * @author 赵彤 
 * @date 2015年4月29日 下午3:35:04
 * @version V1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 成功消息
	 */
	public static final String SUCCESS_MSG = "操作成功！";
	/**
	 * 失败消息
	 */
	public static final String ERROR_MSG = "操作失败:发生未知异常！";

	/**
	 * 结果状态码(可自定义结果状态码) 1:操作成功 0:操作失败
	 */
	private int code;
	/**
	 * 响应结果描述
	 */
	private String message;
	
	/**
	 * 返回数据
	 */
	private Object data;

	public Result() {
		super();
	}

	/**
	 * 
	 * @param code
	 *            结果状态码(可自定义结果状态码或者使用内部静态变量 1:操作成功 0:操作失败 2:警告)
	 * @param msg
	 *            响应结果描述
	 */
	public Result(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * 
	 * @param code
	 *            结果状态码(可自定义结果状态码或者使用内部静态变量 1:操作成功 0:操作失败 2:警告)
	 * @param msg
	 *            响应结果描述
	 * @param obj
	 *            数据
	 */
	public Result(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * 默认操作成功结果.
	 */
	public static Result successResult() {
		return new Result(CodeEnum.success.getValue(), CodeEnum.success.getDescription());
	}

	/**
	 * 默认操作失败结果.
	 */
	public static Result errorResult() {
		return new Result(CodeEnum.error.getValue(), CodeEnum.error.getDescription());
	}

	/**
	 * 结果状态码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 结果状态码
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 响应结果描述
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置响应结果描述
	 * 
	 * @param msg
	 *            响应结果描述
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@SuppressWarnings("static-access")
	@Override
	public String toString() {
		return new JsonMapper().nonDefaultMapper().toJson(this);
	}
}
