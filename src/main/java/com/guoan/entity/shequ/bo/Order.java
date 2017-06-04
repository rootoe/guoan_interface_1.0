package com.guoan.entity.shequ.bo;

import com.pingplusplus.model.Charge;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private String orderId;

    private String orderSn;

    private String orderTelephone;

    private Date createTime;

    private String orderContact;

    private String orderAddress;

    private String orderRemark;

    private Date appointmentBeginTime;

    private Date appointmentEndTime;

    private Integer orderType;

    private String serviceUserId;

    private String serviceTelephone;

    private String serviceContact;

    private String serviceType;

    private String serviceRemark;

    private String couponId;

    private Double orderAmount;

    private Double orderReduce;

    private Double orderPaid;

    private Integer orderStatus;

    private Integer flowStatus;

    private String flowStatusName;

    private Integer isEvaluation;

    private Integer evaluationAttitude;

    private Integer evaluationQuality;

    private String evaluationRemark;

    private Integer paymentType;

    private String paymentTypeName;

    private Integer isCancel;

    private String cancelRemark;

    private String userId;

    // 是发还是收
    private String dispatcherType;

    private Long storeId;

    // 购买的商品
    private List<ShopGoods> shopGoodsList;

    // 购物数量
    private Integer orderQuantity;

    // ping++支付id
    private String chargeId;

    // 支付时间(不管是线上支付还是线下支付都需更新此字段)
    private Date payTime;

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    private static final long serialVersionUID = 1L;

    public List<ShopGoods> getShopGoodsList() {
        return shopGoodsList;
    }

    public void setShopGoodsList(List<ShopGoods> shopGoodsList) {
        this.shopGoodsList = shopGoodsList;
    }

    public String getDispatcherType() {
        return dispatcherType;
    }

    public void setDispatcherType(String dispatcherType) {
        this.dispatcherType = dispatcherType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getOrderTelephone() {
        return orderTelephone;
    }

    public void setOrderTelephone(String orderTelephone) {
        this.orderTelephone = orderTelephone == null ? null : orderTelephone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(String orderContact) {
        this.orderContact = orderContact == null ? null : orderContact.trim();
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress == null ? null : orderAddress.trim();
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    public Date getAppointmentBeginTime() {
        return appointmentBeginTime;
    }

    public void setAppointmentBeginTime(Date appointmentBeginTime) {
        this.appointmentBeginTime = appointmentBeginTime;
    }

    public Date getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setAppointmentEndTime(Date appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(String serviceUserId) {
        this.serviceUserId = serviceUserId == null ? null : serviceUserId.trim();
    }

    public String getServiceTelephone() {
        return serviceTelephone;
    }

    public void setServiceTelephone(String serviceTelephone) {
        this.serviceTelephone = serviceTelephone == null ? null : serviceTelephone.trim();
    }

    public String getServiceContact() {
        return serviceContact;
    }

    public void setServiceContact(String serviceContact) {
        this.serviceContact = serviceContact == null ? null : serviceContact.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getServiceRemark() {
        return serviceRemark;
    }

    public void setServiceRemark(String serviceRemark) {
        this.serviceRemark = serviceRemark == null ? null : serviceRemark.trim();
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getOrderReduce() {
        return orderReduce;
    }

    public void setOrderReduce(Double orderReduce) {
        this.orderReduce = orderReduce;
    }

    public Double getOrderPaid() {
        return orderPaid;
    }

    public void setOrderPaid(Double orderPaid) {
        this.orderPaid = orderPaid;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getFlowStatusName() {
        return flowStatusName;
    }

    public void setFlowStatusName(String flowStatusName) {
        this.flowStatusName = flowStatusName == null ? null : flowStatusName.trim();
    }

    public Integer getIsEvaluation() {
        return isEvaluation;
    }

    public void setIsEvaluation(Integer isEvaluation) {
        this.isEvaluation = isEvaluation;
    }

    public Integer getEvaluationAttitude() {
        return evaluationAttitude;
    }

    public void setEvaluationAttitude(Integer evaluationAttitude) {
        this.evaluationAttitude = evaluationAttitude;
    }

    public Integer getEvaluationQuality() {
        return evaluationQuality;
    }

    public void setEvaluationQuality(Integer evaluationQuality) {
        this.evaluationQuality = evaluationQuality;
    }

    public String getEvaluationRemark() {
        return evaluationRemark;
    }

    public void setEvaluationRemark(String evaluationRemark) {
        this.evaluationRemark = evaluationRemark == null ? null : evaluationRemark.trim();
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName == null ? null : paymentTypeName.trim();
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark == null ? null : cancelRemark.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId == null ? null : chargeId.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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
        Order other = (Order) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
            && (this.getOrderTelephone() == null ? other.getOrderTelephone() == null : this.getOrderTelephone().equals(other.getOrderTelephone()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getOrderContact() == null ? other.getOrderContact() == null : this.getOrderContact().equals(other.getOrderContact()))
            && (this.getOrderAddress() == null ? other.getOrderAddress() == null : this.getOrderAddress().equals(other.getOrderAddress()))
            && (this.getOrderRemark() == null ? other.getOrderRemark() == null : this.getOrderRemark().equals(other.getOrderRemark()))
            && (this.getAppointmentBeginTime() == null ? other.getAppointmentBeginTime() == null : this.getAppointmentBeginTime().equals(other.getAppointmentBeginTime()))
            && (this.getAppointmentEndTime() == null ? other.getAppointmentEndTime() == null : this.getAppointmentEndTime().equals(other.getAppointmentEndTime()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getServiceUserId() == null ? other.getServiceUserId() == null : this.getServiceUserId().equals(other.getServiceUserId()))
            && (this.getServiceTelephone() == null ? other.getServiceTelephone() == null : this.getServiceTelephone().equals(other.getServiceTelephone()))
            && (this.getServiceContact() == null ? other.getServiceContact() == null : this.getServiceContact().equals(other.getServiceContact()))
            && (this.getServiceType() == null ? other.getServiceType() == null : this.getServiceType().equals(other.getServiceType()))
            && (this.getServiceRemark() == null ? other.getServiceRemark() == null : this.getServiceRemark().equals(other.getServiceRemark()))
            && (this.getCouponId() == null ? other.getCouponId() == null : this.getCouponId().equals(other.getCouponId()))
            && (this.getOrderAmount() == null ? other.getOrderAmount() == null : this.getOrderAmount().equals(other.getOrderAmount()))
            && (this.getOrderReduce() == null ? other.getOrderReduce() == null : this.getOrderReduce().equals(other.getOrderReduce()))
            && (this.getOrderPaid() == null ? other.getOrderPaid() == null : this.getOrderPaid().equals(other.getOrderPaid()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getFlowStatus() == null ? other.getFlowStatus() == null : this.getFlowStatus().equals(other.getFlowStatus()))
            && (this.getFlowStatusName() == null ? other.getFlowStatusName() == null : this.getFlowStatusName().equals(other.getFlowStatusName()))
            && (this.getIsEvaluation() == null ? other.getIsEvaluation() == null : this.getIsEvaluation().equals(other.getIsEvaluation()))
            && (this.getEvaluationAttitude() == null ? other.getEvaluationAttitude() == null : this.getEvaluationAttitude().equals(other.getEvaluationAttitude()))
            && (this.getEvaluationQuality() == null ? other.getEvaluationQuality() == null : this.getEvaluationQuality().equals(other.getEvaluationQuality()))
            && (this.getEvaluationRemark() == null ? other.getEvaluationRemark() == null : this.getEvaluationRemark().equals(other.getEvaluationRemark()))
            && (this.getPaymentType() == null ? other.getPaymentType() == null : this.getPaymentType().equals(other.getPaymentType()))
            && (this.getPaymentTypeName() == null ? other.getPaymentTypeName() == null : this.getPaymentTypeName().equals(other.getPaymentTypeName()))
            && (this.getIsCancel() == null ? other.getIsCancel() == null : this.getIsCancel().equals(other.getIsCancel()))
            && (this.getCancelRemark() == null ? other.getCancelRemark() == null : this.getCancelRemark().equals(other.getCancelRemark()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getChargeId() == null ? other.getChargeId() == null : this.getChargeId().equals(other.getChargeId()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getOrderTelephone() == null) ? 0 : getOrderTelephone().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getOrderContact() == null) ? 0 : getOrderContact().hashCode());
        result = prime * result + ((getOrderAddress() == null) ? 0 : getOrderAddress().hashCode());
        result = prime * result + ((getOrderRemark() == null) ? 0 : getOrderRemark().hashCode());
        result = prime * result + ((getAppointmentBeginTime() == null) ? 0 : getAppointmentBeginTime().hashCode());
        result = prime * result + ((getAppointmentEndTime() == null) ? 0 : getAppointmentEndTime().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getServiceUserId() == null) ? 0 : getServiceUserId().hashCode());
        result = prime * result + ((getServiceTelephone() == null) ? 0 : getServiceTelephone().hashCode());
        result = prime * result + ((getServiceContact() == null) ? 0 : getServiceContact().hashCode());
        result = prime * result + ((getServiceType() == null) ? 0 : getServiceType().hashCode());
        result = prime * result + ((getServiceRemark() == null) ? 0 : getServiceRemark().hashCode());
        result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
        result = prime * result + ((getOrderAmount() == null) ? 0 : getOrderAmount().hashCode());
        result = prime * result + ((getOrderReduce() == null) ? 0 : getOrderReduce().hashCode());
        result = prime * result + ((getOrderPaid() == null) ? 0 : getOrderPaid().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getFlowStatus() == null) ? 0 : getFlowStatus().hashCode());
        result = prime * result + ((getFlowStatusName() == null) ? 0 : getFlowStatusName().hashCode());
        result = prime * result + ((getIsEvaluation() == null) ? 0 : getIsEvaluation().hashCode());
        result = prime * result + ((getEvaluationAttitude() == null) ? 0 : getEvaluationAttitude().hashCode());
        result = prime * result + ((getEvaluationQuality() == null) ? 0 : getEvaluationQuality().hashCode());
        result = prime * result + ((getEvaluationRemark() == null) ? 0 : getEvaluationRemark().hashCode());
        result = prime * result + ((getPaymentType() == null) ? 0 : getPaymentType().hashCode());
        result = prime * result + ((getPaymentTypeName() == null) ? 0 : getPaymentTypeName().hashCode());
        result = prime * result + ((getIsCancel() == null) ? 0 : getIsCancel().hashCode());
        result = prime * result + ((getCancelRemark() == null) ? 0 : getCancelRemark().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getChargeId() == null) ? 0 : getChargeId().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        return result;
    }
}