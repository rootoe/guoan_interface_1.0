package com.guoan.service.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.guoan.entity.work.bo.WorkUserInfo;
import com.guoan.utils.collections.MapUtils;
import com.guoan.utils.redis.RedisContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.utils.BeanMapper;
/**
 * @Title: RedisService.java
 * @Package com.guoan.service.redis
 * @Description: redis 服务
 * @author 赵彤 
 * @date 2015年5月7日 下午2:51:55
 * @version V1.0
 */
@Service
public class RedisService {


	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	  * @Description : 获得验证码
	  * @创建人：赵彤  
	  * @创建时间：2015年5月7日 下午3:10:35  
	  * @return String key 规则 验证码 + 手机  10分钟有效
	  * @throws
	 */
	public void setRedisIdentifyingCode(String key, String value){
		redisTemplate.opsForValue().set(key, value, 30, TimeUnit.MINUTES);
	}
	
	/**
	  * @Description : 获得验证码
	  * @创建人：赵彤  
	  * @创建时间：2015年5月7日 下午3:10:35  
	  * @return String key 规则 验证码 + 手机
	  * @throws
	 */
	public String getRedisIdentifyingCode(String key){
		Object o = redisTemplate.opsForValue().get(key);
		if(null != o){
			return o.toString();
		}else {
			return "";
		}
	}
	
	/**
	  * @Description : 存 user 对象
	  * @创建人：赵彤  
	  * @创建时间：2015年5月7日 下午4:12:03  
	  * @return void
	  * @throws
	 */
	public void setRedisUserObject(String firstKeyName, String token, Object obj) {
		String keyName = firstKeyName + "_" + token;
		redisTemplate.opsForHash().putAll(keyName, BeanMapper.beanToMap(obj));
		redisTemplate.expire(token, 24 * 365, TimeUnit.HOURS);
	}
	
	/**
	  * @Description : 获得  user 对象
	  * @创建人：赵彤  
	  * @创建时间：2015年5月7日 下午4:12:15  
	  * @return AppUserInfo
	  * @throws
	 */
	public <T> T getRedisUserObject(String firstKeyName, String token, Class clazz) {

		AppUserInfo appUserInfo = null;
		WorkUserInfo workUserInfo = null;
		String keyName = firstKeyName + "_" + token;

		Map<Object, Object> map = redisTemplate.opsForHash().entries(keyName);
		if (MapUtils.isEmpty(map)) {
			return null;
		} else {

			Object o = BeanMapper.mapToBean(map, clazz);
			if (null != o) {

				if(RedisContants.USER_APP.equals(firstKeyName)){//用户
					appUserInfo = new AppUserInfo();
					appUserInfo = (AppUserInfo) o;
					return (T) appUserInfo;

				}else if(RedisContants.USER_WORK.equals(firstKeyName)){
					workUserInfo = new WorkUserInfo();
					workUserInfo = (WorkUserInfo) o;
					return (T) workUserInfo;
				}else {
					return null;
				}

			} else {
				return null;
			}
		}
	}

	public void setString(String firstKeyName, String secondKeyName, String value) {
		String keyName = firstKeyName + ":" + secondKeyName;
		redisTemplate.opsForValue().set(keyName, value);
	}

	public Object getString(String firstKeyName, String secondKeyName) {
		String keyName = firstKeyName + ":" + secondKeyName;
		return redisTemplate.opsForValue().get(keyName);
	}

	public Object getString(String firstKeyName) {
		// String keyName = firstKeyName + ":" + secondKeyName;
		return redisTemplate.opsForValue().get(firstKeyName);
	}

	/**
	 * 对象存redis
	 * 
	 * @param keyName
	 *            大key
	 * @param obj
	 *            存储对象
	 */
	public void setObject(String keyName, Object obj) {
		redisTemplate.opsForHash().putAll(keyName, BeanMapper.beanToMap(obj));
	}

	/**
	 * Map类型存储Redis
	 * @param firstKeyName
	 * @param secondKeyName
	 * @param map
	 */
	public void setMap(String firstKeyName, String secondKeyName, Map<String, Object> map) {
		String keyName = firstKeyName + ":" + secondKeyName;
		redisTemplate.opsForHash().putAll(keyName, map);
	}

	/**
	 * 对象存redis（去除对象属性）
	 * 
	 * @param keyName
	 *            大key
	 * @param obj
	 *            存储对象
	 * @param excludeArray
	 *            去除对象属性String数组
	 */
	public void setObject(String keyName, Object obj, String[] excludeArray) {
		redisTemplate.opsForHash().putAll(keyName, BeanMapper.beanToMap(obj, excludeArray));
	}

	/**
	 * list存储redis属性
	 * 
	 * @param keyName
	 *            大key
	 * @param list
	 *            集合
	 */
	public void setList(String keyName, List<?> list) {
		this.setList(keyName, list, null);
	}

	/**
	 * list存储redis属性（去除对象属性）
	 * 
	 * @param list
	 *            集合
	 * @param excludeArray
	 *            去除对象属性String数组
	 */
	public void setList(String keyName, List<?> list, String[] excludeArray) {
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (excludeArray != null) {
				setObject(keyName, obj, excludeArray);
			} else {
				setObject(keyName, obj);
			}
		}
	}

	/**
	 * 根据key的名称和类的类型获取整个类的对象
	 * 
	 * @param keyName
	 * @param clazz
	 * @return
	 */
	public Object getObject(String keyName, Class clazz) {
		Map<Object, Object> map = redisTemplate.opsForHash().entries(keyName);
		return BeanMapper.mapToBean(map, clazz);
	}

	public Set<Object> mulitGetObject(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	public boolean hasKey(String key, String hashKey) {
		return redisTemplate.opsForHash().hasKey(key, hashKey);
	}

	/**
	 * 增加set元素
	 * 
	 * @param keyName
	 * @param value
	 */
	public void addSet(String keyName, String value) {
		redisTemplate.opsForSet().add(keyName, value);
	}

	/**
	 * 删除set元素
	 * 
	 * @param keyName
	 * @param value
	 */
	public void delSet(String keyName, String value) {
		redisTemplate.opsForSet().remove(keyName, value);
	}

	/**
	 * 判断 value是否在set中存在
	 * 
	 * @param key
	 * @param value
	 * @return ture exist flase not exist
	 */
	public boolean isMember(String key, String value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/**
	 * 取set集合
	 * 
	 * @param firstKeyName
	 * @param secondKeyValue
	 * @return
	 */
	public Set getSet(String firstKeyName, String secondKeyValue) {
		// final Long pwdLogSize1 =
		// redisTemplate.opsForSet().size("USERDEV:1010001002");
		String keyName = firstKeyName + ":" + secondKeyValue;
		Set s = redisTemplate.opsForSet().members(keyName);
		return s;
	}

	/**
	 * 取list集合
	 * 
	 * @param keyName
	 * @return
	 */
	public List<Object> getList(String keyName) {
		long opsForListSize = redisTemplate.opsForList().size(keyName);
		return redisTemplate.opsForList().range(keyName, 0, opsForListSize);
	}

	public String setList(String key, Object value) {
		redisTemplate.opsForList().rightPush(key, value);
		return null;
	}

	public String setListLeftPush(String key, Object value) {
		redisTemplate.opsForList().leftPush(key, value);
		return null;
	}

	public String delList(String key, Object value) {
		redisTemplate.opsForList().remove(key, 1, value);
		return null;
	}

	/**
	 * 根据获取对象中某个特定属性的值
	 * 
	 * @param keyName
	 * @param valueName
	 * @return
	 */
	public Object getValueFromObject(String keyName, String valueName) {
		return redisTemplate.opsForHash().get(keyName, valueName);
	}

	/**
	 * 根据id删除指定key的值
	 * 
	 * @param keyName
	 */
	public void deleteObject(String keyName) {
		redisTemplate.opsForHash().getOperations().delete(keyName);
	}

	/**
	 * 是否有指定key的值
	 * 
	 * @param keyName
	 */
	public boolean hasKey(String keyName) {
		return redisTemplate.opsForHash().getOperations().hasKey(keyName);
	}

	public Set<String> getKey(String pattern) {
		return redisTemplate.keys(pattern);
	}

	public void deleteKey(String keyName) {
		redisTemplate.delete(keyName);
	}

	public void setExpire(String key, int timeout) {
		redisTemplate.expire(key, timeout, TimeUnit.HOURS);
	}


}
