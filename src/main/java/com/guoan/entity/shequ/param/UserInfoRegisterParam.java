package com.guoan.entity.shequ.param;
/**
 * @Title: UserInfoParam.java
 * @Package com.guoan.entity.shequ.param
 * @Description: 用户注册时传递的参数
 * @author 赵彤 
 * @date 2015年4月29日 下午4:27:54
 * @version V1.0
 */
public class UserInfoRegisterParam {
	// 用户信息
	private String nickname;
	
    private String telephone;
    
    private String password;
    
    private String verificode;
    
    private String type;
    
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerificode() {
		return verificode;
	}

	public void setVerificode(String verificode) {
		this.verificode = verificode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
