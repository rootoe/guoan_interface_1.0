package com.guoan.entity.shequ.bo;

import java.io.Serializable;

public class OrderMaintain extends Order implements Serializable {
    private String maintainId;

    private String maintainOrder;

    private Integer providerId;

    private String providerName;

    private static final long serialVersionUID = 1L;

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId == null ? null : maintainId.trim();
    }

    public String getMaintainOrder() {
        return maintainOrder;
    }

    public void setMaintainOrder(String maintainOrder) {
        this.maintainOrder = maintainOrder == null ? null : maintainOrder.trim();
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
        OrderMaintain other = (OrderMaintain) that;
        return (this.getMaintainId() == null ? other.getMaintainId() == null : this.getMaintainId().equals(other.getMaintainId()))
            && (this.getMaintainOrder() == null ? other.getMaintainOrder() == null : this.getMaintainOrder().equals(other.getMaintainOrder()))
            && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()))
            && (this.getProviderName() == null ? other.getProviderName() == null : this.getProviderName().equals(other.getProviderName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMaintainId() == null) ? 0 : getMaintainId().hashCode());
        result = prime * result + ((getMaintainOrder() == null) ? 0 : getMaintainOrder().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
        result = prime * result + ((getProviderName() == null) ? 0 : getProviderName().hashCode());
        return result;
    }
}