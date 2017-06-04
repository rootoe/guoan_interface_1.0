package com.guoan.entity.shequ.bo;

import java.io.Serializable;

public class OrderWash extends Order implements Serializable {
    private String washId;

    private Integer orderQuantity;

    private String washOrder;

    private Integer providerId;

    private String providerName;

    private static final long serialVersionUID = 1L;

    public String getWashId() {
        return washId;
    }

    public void setWashId(String washId) {
        this.washId = washId == null ? null : washId.trim();
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getWashOrder() {
        return washOrder;
    }

    public void setWashOrder(String washOrder) {
        this.washOrder = washOrder == null ? null : washOrder.trim();
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
        OrderWash other = (OrderWash) that;
        return (this.getWashId() == null ? other.getWashId() == null : this.getWashId().equals(other.getWashId()))
            && (this.getOrderQuantity() == null ? other.getOrderQuantity() == null : this.getOrderQuantity().equals(other.getOrderQuantity()))
            && (this.getWashOrder() == null ? other.getWashOrder() == null : this.getWashOrder().equals(other.getWashOrder()))
            && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()))
            && (this.getProviderName() == null ? other.getProviderName() == null : this.getProviderName().equals(other.getProviderName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWashId() == null) ? 0 : getWashId().hashCode());
        result = prime * result + ((getOrderQuantity() == null) ? 0 : getOrderQuantity().hashCode());
        result = prime * result + ((getWashOrder() == null) ? 0 : getWashOrder().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
        result = prime * result + ((getProviderName() == null) ? 0 : getProviderName().hashCode());
        return result;
    }
}