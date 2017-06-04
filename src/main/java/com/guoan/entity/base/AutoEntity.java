package com.guoan.entity.base;

/**
 * @Title: AutoEntity.java
 * @Package com.guoan.entity.base
 * @Description: 统一定义entity基类. <br>
 *               基类统一定义id的属性名称、数据类型、列名映射及生成策略. <br>
 *               子类可重载getId()函数重定义id的列名映射和生成策略. <br>
 *               新加并发控制(乐观锁,用于并发控制)、数据更新时间、操作用户ID.
 * @author 赵彤
 * @date 2013-10-10 下午05:11:11
 * @version V1.0
 */
public abstract class AutoEntity  extends AbstractEntity<Long>{

	/**
	 * 主键ID
	 */
	protected Long id;

	public AutoEntity() {
	}

	/**
	 * 主键ID 根据数据库主键自增长策略 依赖于数据库(SQL Serveer、MySQL数据库使用)
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 主键ID
	 * 
	 * @param id
	 *            主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
