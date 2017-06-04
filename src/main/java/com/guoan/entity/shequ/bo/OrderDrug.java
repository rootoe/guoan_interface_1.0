package com.guoan.entity.shequ.bo;

import java.io.Serializable;

/**
 * 处方药实体类
 * @author litianyu
 *
 */
public class OrderDrug extends Order implements Serializable {
    
	private String drugsId;

    private Integer orderQuantity;

    private String drugsOrder;

    private Integer providerId;

    private String providerName;

    private static final long serialVersionUID = 1L;

    public String getDrugsId() {
        return drugsId;
    }

    public void setDrugsId(String drugsId) {
        this.drugsId = drugsId == null ? null : drugsId.trim();
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getDrugsOrder() {
        return drugsOrder;
    }

    public void setDrugsOrder(String drugsOrder) {
        this.drugsOrder = drugsOrder;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName == null ? null : providerName.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderDrug other = (OrderDrug) that;
        return (this.getDrugsId() == null ? other.getDrugsId() == null : this.getDrugsId().equals(other.getDrugsId()))
            && (this.getOrderQuantity() == null ? other.getOrderQuantity() == null : this.getOrderQuantity().equals(other.getOrderQuantity()))
            && (this.getDrugsOrder() == null ? other.getDrugsOrder() == null : this.getDrugsOrder().equals(other.getDrugsOrder()))
            && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()))
            && (this.getProviderName() == null ? other.getProviderName() == null : this.getProviderName().equals(other.getProviderName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDrugsId() == null) ? 0 : getDrugsId().hashCode());
        result = prime * result + ((getOrderQuantity() == null) ? 0 : getOrderQuantity().hashCode());
        result = prime * result + ((getDrugsOrder() == null) ? 0 : getDrugsOrder().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
        result = prime * result + ((getProviderName() == null) ? 0 : getProviderName().hashCode());
        return result;
    }
}