package com.guoan.entity.shequ.bo;

import java.io.Serializable;

public class OrderExpress extends Order implements Serializable {
    private String expressId;

    private String goodsInfo;

    private Double goodsWeight;

    private String expressOrder;

    private Integer providerId;

    private String providerName;

    private String dispatcherType;

    private String targetAddress;

    private String sourceAddress;

    private static final long serialVersionUID = 1L;

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId == null ? null : expressId.trim();
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo == null ? null : goodsInfo.trim();
    }

    public Double getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Double goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getExpressOrder() {
        return expressOrder;
    }

    public void setExpressOrder(String expressOrder) {
        this.expressOrder = expressOrder == null ? null : expressOrder.trim();
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

    public String getDispatcherType() {
        return dispatcherType;
    }

    public void setDispatcherType(String dispatcherType) {
        this.dispatcherType = dispatcherType == null ? null : dispatcherType.trim();
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress == null ? null : targetAddress.trim();
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress == null ? null : sourceAddress.trim();
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
        OrderExpress other = (OrderExpress) that;
        return (this.getExpressId() == null ? other.getExpressId() == null : this.getExpressId().equals(other.getExpressId()))
            && (this.getGoodsInfo() == null ? other.getGoodsInfo() == null : this.getGoodsInfo().equals(other.getGoodsInfo()))
            && (this.getGoodsWeight() == null ? other.getGoodsWeight() == null : this.getGoodsWeight().equals(other.getGoodsWeight()))
            && (this.getExpressOrder() == null ? other.getExpressOrder() == null : this.getExpressOrder().equals(other.getExpressOrder()))
            && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()))
            && (this.getProviderName() == null ? other.getProviderName() == null : this.getProviderName().equals(other.getProviderName()))
            && (this.getDispatcherType() == null ? other.getDispatcherType() == null : this.getDispatcherType().equals(other.getDispatcherType()))
            && (this.getTargetAddress() == null ? other.getTargetAddress() == null : this.getTargetAddress().equals(other.getTargetAddress()))
            && (this.getSourceAddress() == null ? other.getSourceAddress() == null : this.getSourceAddress().equals(other.getSourceAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExpressId() == null) ? 0 : getExpressId().hashCode());
        result = prime * result + ((getGoodsInfo() == null) ? 0 : getGoodsInfo().hashCode());
        result = prime * result + ((getGoodsWeight() == null) ? 0 : getGoodsWeight().hashCode());
        result = prime * result + ((getExpressOrder() == null) ? 0 : getExpressOrder().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
        result = prime * result + ((getProviderName() == null) ? 0 : getProviderName().hashCode());
        result = prime * result + ((getDispatcherType() == null) ? 0 : getDispatcherType().hashCode());
        result = prime * result + ((getTargetAddress() == null) ? 0 : getTargetAddress().hashCode());
        result = prime * result + ((getSourceAddress() == null) ? 0 : getSourceAddress().hashCode());
        return result;
    }
}