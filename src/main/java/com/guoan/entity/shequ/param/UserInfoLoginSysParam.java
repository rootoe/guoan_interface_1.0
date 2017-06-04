package com.guoan.entity.shequ.param;

/**
 * @Title: UserInfoLoginParam.java
 * @Package com.guoan.entity.shequ.param
 * @Description: 用户登陆时传递的软件、硬件参数
 * @author 赵彤
 * @date 2015年5月4日 下午5:19:00
 * @version V1.0
 */
public class UserInfoLoginSysParam {

	// 用户硬件信息
	private String clientIp;

	private String clientToken;

	private String phoneModel;

	private String phoneBrand;

	private String phoneScreenWidth;

	private String phoneScreenHeight;

	// 用户app软件信息
	private String clientVersion;

	private String clientPlatform;

	private String hannels;

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getClientToken() {
		return clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	public String getPhoneScreenWidth() {
		return phoneScreenWidth;
	}

	public void setPhoneScreenWidth(String phoneScreenWidth) {
		this.phoneScreenWidth = phoneScreenWidth;
	}

	public String getPhoneScreenHeight() {
		return phoneScreenHeight;
	}

	public void setPhoneScreenHeight(String phoneScreenHeight) {
		this.phoneScreenHeight = phoneScreenHeight;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getClientPlatform() {
		return clientPlatform;
	}

	public void setClientPlatform(String clientPlatform) {
		this.clientPlatform = clientPlatform;
	}

	public String getHannels() {
		return hannels;
	}

	public void setHannels(String hannels) {
		this.hannels = hannels;
	}

}
