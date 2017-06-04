package com.guoan.utils.verificode;

import org.springframework.data.redis.core.RedisTemplate;

import com.guoan.service.redis.RedisService;
import com.guoan.utils.StringUtils;
import com.guoan.utils.encode.MD5Util;
import com.guoan.utils.spring.SpringContextHolder;
/**
 * @Title: VerifiCodeUtils.java
 * @Package com.guoan.utils.verificode
 * @Description: 短信验证码验证工具类
 * @author 赵彤 
 * @date 2015年4月29日 下午4:40:16
 * @version V1.0
 */
public class VerifiCodeUtils extends RedisService{
	
	/**
	  * @Description : 从缓存中取得验证码
	  * @创建人：赵彤  
	  * @创建时间：2015年4月29日 下午4:44:27 
	  * @return boolean
	  * @throws
	 */
	public static boolean checkIdentifyingCode(String phoneNum, String identifyingCode) {
		// 如果缓存打开，从缓存读取
		RedisTemplate redisTemplate = (RedisTemplate)SpringContextHolder.getApplicationContext().getBean("redisTemplate");
		String cachePhoneNum = "";
		Object o = redisTemplate.opsForValue().get(MD5Util.getStringMD5(identifyingCode + phoneNum));
		if(null == o){
			return false;
		}
		cachePhoneNum = o.toString();
		
		if (StringUtils.isNotEmpty(cachePhoneNum) && cachePhoneNum.equals(phoneNum)) {
			return true;
		} else {
			return false;
		}
	}
}
