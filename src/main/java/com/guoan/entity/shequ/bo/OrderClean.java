package com.guoan.entity.shequ.bo;

import java.io.Serializable;

public class OrderClean extends Order implements Serializable {
    private String cleanId;

    private String cleanOrder;

    private Integer providerId;

    private String providerName;

    private static final long serialVersionUID = 1L;

    public String getCleanId() {
        return cleanId;
    }

    public void setCleanId(String cleanId) {
        this.cleanId = cleanId == null ? null : cleanId.trim();
    }

    public String getCleanOrder() {
        return cleanOrder;
    }

    public void setCleanOrder(String cleanOrder) {
        this.cleanOrder = cleanOrder == null ? null : cleanOrder.trim();
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
        OrderClean other = (OrderClean) that;
        return (this.getCleanId() == null ? other.getCleanId() == null : this.getCleanId().equals(other.getCleanId()))
            && (this.getCleanOrder() == null ? other.getCleanOrder() == null : this.getCleanOrder().equals(other.getCleanOrder()))
            && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()))
            && (this.getProviderName() == null ? other.getProviderName() == null : this.getProviderName().equals(other.getProviderName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCleanId() == null) ? 0 : getCleanId().hashCode());
        result = prime * result + ((getCleanOrder() == null) ? 0 : getCleanOrder().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
        result = prime * result + ((getProviderName() == null) ? 0 : getProviderName().hashCode());
        return result;
    }
}