package com.guoan.service.token.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.guoan.entity.work.bo.WorkUserInfo;
import com.guoan.utils.redis.RedisContants;
import org.springframework.stereotype.Service;

import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.service.redis.RedisService;
import com.guoan.service.token.TokenGenerator;
import com.guoan.utils.encode.MD5Util;
import com.guoan.utils.token.TokenUtil;

@Service
public class TokenGeneratorImpl extends RedisService implements TokenGenerator {

	Map<String, AppUserInfo> tokenStore = new ConcurrentHashMap<String, AppUserInfo>();
	
	public String generatorToken(AppUserInfo user) {
//		String token = TokenUtil.getTokenStringByString(user.getNickname());
//		MemcachedClient client = MemcacheUtil.getMemCachedClient();
//		Integer time = Const.MEMCACHED_IDENTIFYING_CODE_LIVE_TIME;
//		MemcacheUtil.set(token, user, time);
//		System.out.println("生成的token为: " + token);
//		return token;
		return null;
	}
	@Override
	public String generatorTokenWithLogin(AppUserInfo user, int tokenLivingTime) {
//		String token = TokenUtil.getTokenStringByUserId(MD5Util.getStringMD5(user.getUserId()+System.currentTimeMillis()));
		String token = TokenUtil.getTokenStringByUserId(MD5Util.getStringMD5(user.getUserId()));
		super.setRedisUserObject(RedisContants.USER_APP, token, user);
		return token;
	}

	@Override
	public String generatorTokenWithWorkLogin(WorkUserInfo user, int tokenLivingTime) {
		String token = TokenUtil.getTokenStringByUserId(MD5Util.getStringMD5(user.getId().toString()+ user.getRemark() == null ? "noDeviceToken":user.getRemark()));
		super.setRedisUserObject(RedisContants.USER_WORK, token, user);
		return token;
	}

	public boolean validateToken(String token) {
//		MemcachedClient client = MemcacheUtil.getMemCachedClient();
//		Object obj = client.get(token);
//		if (null != obj) {
//			return true;
//		} else {
//			// 日志，没有权限
//			return false;
//		}
		return true;
	}
	//*/
}