package com.guoan.entity.base;

//import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.guoan.entity.base.state.StatusState;
import com.guoan.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title: BaseEntity.java
 * @Package com.guoan.common.orm.entity
 * @Description: 统一定义entity基类. <br>
 *               基类统一定义id的属性名称、数据类型、列名映射及生成策略. <br>
 *               子类可重载getId()函数重定义id的列名映射和生成策略. <br>
 *               新加并发控制(乐观锁,用于并发控制)、数据更新时间、操作用户ID.
 * @author 赵彤
 * @date 2013-10-10 下午05:12:23
 * @version V1.0
 */
public class BaseEntity extends AutoEntity implements Serializable, Cloneable {

	private static final long serialVersionUID = 2142201445199112425L;

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String TIME_FORMAT = "HH:mm:ss";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	public static final String TIMEZONE = "GMT+08:00";

	public static String date2String(Date date, String dateFormat) {
		return DateUtil.format(date, dateFormat);
	}

	public static <T extends Date> T string2Date(String dateString, String dateFormat, Class<T> targetResultType) {
		return (T) DateUtil.parse(dateString, dateFormat, targetResultType);
	}

	/**
	 * 记录状态标志位 正常(0) 已删除(1) 待审核(2) 锁定(3)
	 */
	protected Integer status = StatusState.normal.getValue();
	/**
	 * 操作版本(乐观锁,用于并发控制)
	 */
	protected Integer version;

	/**
	 * 记录创建者用户登录名
	 */
	protected String createUser;
	/**
	 * 记录创建时间
	 */
	protected Date createTime;

	/**
	 * 记录更新用户 用户登录名
	 */
	protected String updateUser;
	/**
	 * 记录更新时间
	 */
	protected Date updateTime;

	public BaseEntity() {
		super();
	}

	/**
	 * 状态标志位
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态描述
	 */
//	@Transient
	public String getStatusDesc() {
		StatusState s = StatusState.getStatusState(status);
		String str = "";
		if (s != null) {
			str = s.getDescription();
		}
		return str;
	}

	/**
	 * 设置 状态标志位
	 * 
	 * @param status
	 *            状态标志位
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 版本号(乐观锁)
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * 设置 版本号(乐观锁)
	 * 
	 * @param version
	 *            版本号(乐观锁)
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * 记录创建者 用户登录名
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 设置 记记录创建者 用户登录名
	 * 
	 * @param createUser
	 *            用户登录名
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 记录创建时间.
	 */
	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 记录创建时间
	 * 
	 * @param createTime
	 *            记录创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 记录更新用户 用户登录名
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 设置 记录更新用户 用户登录名
	 * 
	 * @param updateUser
	 *            用户登录名
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 记录更新时间
	 */
	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = DATE_TIME_FORMAT, timezone = TIMEZONE)
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 记录更新时间
	 * 
	 * @param updateTime
	 *            记录更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BaseEntity clone() {
		BaseEntity o = null;
		try {
			o = (BaseEntity) super.clone();// Object中的clone()识别出你要复制的是哪一个对象。
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
