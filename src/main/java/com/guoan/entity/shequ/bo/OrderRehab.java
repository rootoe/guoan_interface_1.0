package com.guoan.entity.shequ.bo;

import java.io.Serializable;

public class OrderRehab extends Order implements Serializable {
    private String rehabId;

    private Integer providerId;

    private String providerName;

    private Long packageId;

    private String packageName;

    private Long packagePriceId;

    private String packagePriceName;

    private String workerName;

    private String workerPhone;

    private Integer workerAmount;

    private String customerServicePhone;

    private static final long serialVersionUID = 1L;

    public String getRehabId() {
        return rehabId;
    }

    public void setRehabId(String rehabId) {
        this.rehabId = rehabId == null ? null : rehabId.trim();
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

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public Long getPackagePriceId() {
        return packagePriceId;
    }

    public void setPackagePriceId(Long packagePriceId) {
        this.packagePriceId = packagePriceId;
    }

    public String getPackagePriceName() {
        return packagePriceName;
    }

    public void setPackagePriceName(String packagePriceName) {
        this.packagePriceName = packagePriceName == null ? null : packagePriceName.trim();
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName == null ? null : workerName.trim();
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone == null ? null : workerPhone.trim();
    }

    public Integer getWorkerAmount() {
        return workerAmount;
    }

    public void setWorkerAmount(Integer workerAmount) {
        this.workerAmount = workerAmount;
    }

    public String getCustomerServicePhone() {
        return customerServicePhone;
    }

    public void setCustomerServicePhone(String customerServicePhone) {
        this.customerServicePhone = customerServicePhone == null ? null : customerServicePhone.trim();
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
        OrderRehab other = (OrderRehab) that;
        return (this.getRehabId() == null ? other.getRehabId() == null : this.getRehabId().equals(other.getRehabId()))
            && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()))
            && (this.getProviderName() == null ? other.getProviderName() == null : this.getProviderName().equals(other.getProviderName()))
            && (this.getPackageId() == null ? other.getPackageId() == null : this.getPackageId().equals(other.getPackageId()))
            && (this.getPackageName() == null ? other.getPackageName() == null : this.getPackageName().equals(other.getPackageName()))
            && (this.getPackagePriceId() == null ? other.getPackagePriceId() == null : this.getPackagePriceId().equals(other.getPackagePriceId()))
            && (this.getPackagePriceName() == null ? other.getPackagePriceName() == null : this.getPackagePriceName().equals(other.getPackagePriceName()))
            && (this.getWorkerName() == null ? other.getWorkerName() == null : this.getWorkerName().equals(other.getWorkerName()))
            && (this.getWorkerPhone() == null ? other.getWorkerPhone() == null : this.getWorkerPhone().equals(other.getWorkerPhone()))
            && (this.getWorkerAmount() == null ? other.getWorkerAmount() == null : this.getWorkerAmount().equals(other.getWorkerAmount()))
            && (this.getCustomerServicePhone() == null ? other.getCustomerServicePhone() == null : this.getCustomerServicePhone().equals(other.getCustomerServicePhone()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRehabId() == null) ? 0 : getRehabId().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
        result = prime * result + ((getProviderName() == null) ? 0 : getProviderName().hashCode());
        result = prime * result + ((getPackageId() == null) ? 0 : getPackageId().hashCode());
        result = prime * result + ((getPackageName() == null) ? 0 : getPackageName().hashCode());
        result = prime * result + ((getPackagePriceId() == null) ? 0 : getPackagePriceId().hashCode());
        result = prime * result + ((getPackagePriceName() == null) ? 0 : getPackagePriceName().hashCode());
        result = prime * result + ((getWorkerName() == null) ? 0 : getWorkerName().hashCode());
        result = prime * result + ((getWorkerPhone() == null) ? 0 : getWorkerPhone().hashCode());
        result = prime * result + ((getWorkerAmount() == null) ? 0 : getWorkerAmount().hashCode());
        result = prime * result + ((getCustomerServicePhone() == null) ? 0 : getCustomerServicePhone().hashCode());
        return result;
    }
}