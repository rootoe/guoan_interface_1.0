package com.guoan.entity.shequ.param;

/**
 * @Title: UserInfoLoginParam.java
 * @Package com.guoan.entity.shequ.param
 * @Description: 用户登陆时传递的参数
 * @author 赵彤 
 * @date 2015年5月4日 下午5:19:00
 * @version V1.0
 */
public class UserInfoLoginParam {
	// 用户信息
	private String nickname;

	private String telephone;

	private String password;

	private String verificode;

	private String type;

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

}
