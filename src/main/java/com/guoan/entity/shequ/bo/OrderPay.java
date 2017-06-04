package com.guoan.entity.shequ.bo;

import java.io.Serializable;

public class OrderPay extends Order implements Serializable {
    private String payId;

    private String payOrder;

    private String payType;

    private String payTypeName;

    private static final long serialVersionUID = 1L;

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    public String getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(String payOrder) {
        this.payOrder = payOrder == null ? null : payOrder.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
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
        OrderPay other = (OrderPay) that;
        return (this.getPayId() == null ? other.getPayId() == null : this.getPayId().equals(other.getPayId()))
            && (this.getPayOrder() == null ? other.getPayOrder() == null : this.getPayOrder().equals(other.getPayOrder()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getPayTypeName() == null ? other.getPayTypeName() == null : this.getPayTypeName().equals(other.getPayTypeName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPayId() == null) ? 0 : getPayId().hashCode());
        result = prime * result + ((getPayOrder() == null) ? 0 : getPayOrder().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getPayTypeName() == null) ? 0 : getPayTypeName().hashCode());
        return result;
    }
}