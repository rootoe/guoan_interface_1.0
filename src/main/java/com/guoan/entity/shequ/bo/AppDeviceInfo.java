package com.guoan.entity.shequ.bo;

import java.io.Serializable;
import java.util.Date;

public class AppDeviceInfo implements Serializable {
	private String userId;

	private String clientIp;

	private String clientToken;

	private String phoneModel;

	private String phoneBrand;

	private String phoneScreenWidth;

	private String phoneScreenHeight;

	private Date createTime;

	private static final long serialVersionUID = 1L;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp == null ? null : clientIp.trim();
	}

	public String getClientToken() {
		return clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken == null ? null : clientToken.trim();
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel == null ? null : phoneModel.trim();
	}

	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand == null ? null : phoneBrand.trim();
	}

	public String getPhoneScreenWidth() {
		return phoneScreenWidth;
	}

	public void setPhoneScreenWidth(String phoneScreenWidth) {
		this.phoneScreenWidth = phoneScreenWidth == null ? null : phoneScreenWidth.trim();
	}

	public String getPhoneScreenHeight() {
		return phoneScreenHeight;
	}

	public void setPhoneScreenHeight(String phoneScreenHeight) {
		this.phoneScreenHeight = phoneScreenHeight == null ? null : phoneScreenHeight.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		AppDeviceInfo other = (AppDeviceInfo) that;
		return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getClientIp() == null ? other.getClientIp() == null : this.getClientIp().equals(other.getClientIp()))
				&& (this.getClientToken() == null ? other.getClientToken() == null : this.getClientToken().equals(other.getClientToken()))
				&& (this.getPhoneModel() == null ? other.getPhoneModel() == null : this.getPhoneModel().equals(other.getPhoneModel()))
				&& (this.getPhoneBrand() == null ? other.getPhoneBrand() == null : this.getPhoneBrand().equals(other.getPhoneBrand()))
				&& (this.getPhoneScreenWidth() == null ? other.getPhoneScreenWidth() == null : this.getPhoneScreenWidth().equals(other.getPhoneScreenWidth()))
				&& (this.getPhoneScreenHeight() == null ? other.getPhoneScreenHeight() == null : this.getPhoneScreenHeight().equals(other.getPhoneScreenHeight()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		result = prime * result + ((getClientIp() == null) ? 0 : getClientIp().hashCode());
		result = prime * result + ((getClientToken() == null) ? 0 : getClientToken().hashCode());
		result = prime * result + ((getPhoneModel() == null) ? 0 : getPhoneModel().hashCode());
		result = prime * result + ((getPhoneBrand() == null) ? 0 : getPhoneBrand().hashCode());
		result = prime * result + ((getPhoneScreenWidth() == null) ? 0 : getPhoneScreenWidth().hashCode());
		result = prime * result + ((getPhoneScreenHeight() == null) ? 0 : getPhoneScreenHeight().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		return result;
	}
}