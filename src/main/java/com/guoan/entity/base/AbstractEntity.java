package com.guoan.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
* @Title: AbstractEntity.java
* @Package com.guoan.entity.base
* @Description: 抽象实体基类.
* @author 赵彤  
* @date 2013-10-10 下午05:10:01
* @version V1.0
 */
public abstract class AbstractEntity<ID extends Serializable>{

    public abstract ID getId();

    /**
     * 设置主键ID.
     *
     * @param id 主键ID
     */
    public abstract void setId(final ID id);

    /**
     * 是否是新创建的对象.
     * @return
     */
    @JsonIgnore
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractEntity<?> that = (AbstractEntity<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}