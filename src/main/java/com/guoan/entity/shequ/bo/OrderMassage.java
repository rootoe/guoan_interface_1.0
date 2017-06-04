package com.guoan.entity.shequ.bo;

import java.io.Serializable;

/**
 * 康复按摩
 * @author
 * add OrderMassage on 2015-09-24 
 *
 */
public class OrderMassage extends Order implements Serializable {
   
	/**康复按摩id**/
	private String massageId;

	/**康复按摩订单号**/
    private String massageOrder;

    /**服务商id**/
    private Integer providerId;

    /**本质是服务商名称，但此字段在这里的含义是 按摩类型 **/
    private String providerName;

    private static final long serialVersionUID = 1L;

    public String getMassageId() {
        return massageId;
    }

    public void setMassageId(String massageId) {
        this.massageId = massageId == null ? null : massageId.trim();
    }

    public String getMassageOrder() {
        return massageOrder;
    }

    public void setMassageOrder(String massageOrder) {
        this.massageOrder = massageOrder == null ? null : massageOrder.trim();
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
        OrderMassage other = (OrderMassage) that;
        return (this.getMassageId() == null ? other.getMassageId() == null : this.getMassageId().equals(other.getMassageId()))
            && (this.getMassageOrder() == null ? other.getMassageOrder() == null : this.getMassageOrder().equals(other.getMassageOrder()))
            && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()))
            && (this.getProviderName() == null ? other.getProviderName() == null : this.getProviderName().equals(other.getProviderName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMassageId() == null) ? 0 : getMassageId().hashCode());
        result = prime * result + ((getMassageOrder() == null) ? 0 : getMassageOrder().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
        result = prime * result + ((getProviderName() == null) ? 0 : getProviderName().hashCode());
        return result;
    }
}