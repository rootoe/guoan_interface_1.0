package com.guoan.entity.shequ.param;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 参数 
 * @author 
 *
 */
public class OrderParam implements Serializable {

    private static final long serialVersionUID = -4679458875114666298L;

    private String orderAddressId;

    private String orderRemark;

    private Date appointmentBeginTime;

    private Date appointmentEndTime;

    private Integer orderType;

    private Integer orderQuantity;

    private Double orderAmount;

    private Integer paymentType;

    private String paymentTypeName;

    private String washOrder;

    // 服务商id
    private Integer providerId;
    // 服务商名称
    private String providerName;

    // 支付
    private Integer payType;
    // 支付名称
    private String payTypeName;

    private Integer areaId;

    /*收发类型  receive :接收 send:发送*/
    private String dispatcherType;

    /*订单ID*/
    private String orderId;

    /* 评价-服务态度*/
    private Integer evaluationAttitude;

    /*评价-品质*/
    private Integer evaluationQuality;

    /*评价-备注*/
    private String evaluationRemark;

    /*订单列表检索条件*/
    private Integer pageNum;
    private Integer lineNum;

    /*用户类型:1:用户;2:服务人员*/
    private Integer userType;

    private Integer flowStatus;

    private List<Integer> flowStatusList;

    private Long packageId;

    private String packageName;

    private Long packagePriceId;

    private String packagePriceName;

    private String orderSN; /**订单编号**/

    private String telephone;

    private String serviceType;

    private Double orderReduce;

    private String couponId;

    // 目的地
    private String targetAddress;

    // 寄件地址
    private String sourceAddress;

    // 快递单号
    private String expressOrder;

    // 物品类型
    private String goodsInfo;

    // 物品重量
    private Double goodsWeight;

    // 购买商品
    private List<Map<String, Integer>> goods;
    
    //add drugOrder on 2015-09-15 处方药订单号
    private String drugsOrder;   
    
    //add massageOrder ON 2015-09-24 康复按摩订单号
    private String massageOrder;
    
    
    
    //add getMassageOrder ON 2015-09-24
    public String getMassageOrder() {
		return massageOrder;
	}

    //add setMassageOrder ON 2015-09-24
	public void setMassageOrder(String massageOrder) {
		this.massageOrder = massageOrder;
	}

	//修改了 drug ---> drugs 2015-09-15
    public String getDrugsOrder() {
		return drugsOrder;
	}

    //修改了 drug ---> drugs 2015-09-15
	public void setDrugsOrder(String drugsOrder) {
		this.drugsOrder = drugsOrder;
	}

	public List<Map<String, Integer>> getGoods() {
        return goods;
    }

    public void setGoods(List<Map<String, Integer>> goods) {
        this.goods = goods;
    }

    public Double getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Double goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getExpressOrder() {
        return expressOrder;
    }

    public void setExpressOrder(String expressOrder) {
        this.expressOrder = expressOrder;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getOrderAddressId() {
        return orderAddressId;
    }

    public void setOrderAddressId(String orderAddressId) {
        this.orderAddressId = orderAddressId;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
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

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getWashOrder() {
        return washOrder;
    }

    public void setWashOrder(String washOrder) {
        this.washOrder = washOrder;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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
        this.orderId = orderId;
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
        this.evaluationRemark = evaluationRemark;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public List<Integer> getFlowStatusList() {
        return flowStatusList;
    }

    public void setFlowStatusList(List<Integer> flowStatusList) {
        this.flowStatusList = flowStatusList;
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
        this.packageName = packageName;
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
        this.packagePriceName = packagePriceName;
    }

    public String getOrderSN() {
        return orderSN;
    }

    public void setOrderSN(String orderSN) {
        this.orderSN = orderSN;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Double getOrderReduce() {
        return orderReduce;
    }

    public void setOrderReduce(Double orderReduce) {
        this.orderReduce = orderReduce;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }
}