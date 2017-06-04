package com.guoan.service.token;

import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.entity.work.bo.WorkUserInfo;


/**
 * @Title: TokenGenerator.java
 * @Package com.guoan.service.token
 * @Description: token生成、验证
 * @author 赵彤
 * @date 2014年9月11日 下午12:04:46
 * @version V1.0
 */
public interface TokenGenerator {

	public String generatorToken(AppUserInfo user);
	
	public String generatorTokenWithLogin(AppUserInfo user, int tokenLivingTime);

	public String generatorTokenWithWorkLogin(WorkUserInfo user, int tokenLivingTime);

	public boolean validateToken(String token);
}
