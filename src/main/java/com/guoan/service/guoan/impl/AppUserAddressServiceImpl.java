/**
 * 
 */
package com.guoan.service.guoan.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.redis.RedisContants;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoan.dao.shequ.AppUserAddressMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.AppUserAddress;
import com.guoan.entity.shequ.bo.AppUserAddressFull;
import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.service.guoan.AppUserAddressService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.StringUtils;

/**
 * 用户管理地址
 * 
 * @author gaochao
 *
 */
@Service
public class AppUserAddressServiceImpl extends RedisService implements AppUserAddressService {

	// private static final Logger logger =
	// LoggerFactory.getLogger(AppUserAddressServiceImpl.class);

	@Autowired
	private AppUserAddressMapper appUserAddressMapper;

	/**
	 * 设置默认地址
	 * 
	 * @author gaochao
	 */
	@Override
	@Transactional
	public Result setDefault(String jsonString) {

		Result result = new Result();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONObject addressJson = jsonObject.getJSONObject("params");
		String token = jsonObject.getString("token");

		/*取得用户对象*/
		AppUserInfo user = new AppUserInfo();
		user = (AppUserInfo) super.getRedisUserObject(RedisContants.USER_APP, token, user.getClass());

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		/*清空默认地址*/
		appUserAddressMapper.cancelDefault(user.getUserId());

		/*设置默认地址*/
		appUserAddressMapper.setDefault(addressJson.getString("addressId"));

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 保存地址
	 * 
	 * @author gaochao
	 */
	@Override
	public Result save(String jsonString) {

		Result result = new Result();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONObject addressJson = jsonObject.getJSONObject("params");
		String token = jsonObject.getString("token");

		/*取得用户对象*/
		AppUserInfo user = new AppUserInfo();
		user = (AppUserInfo) super.getRedisUserObject(RedisContants.USER_APP, token, user.getClass());

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		String userId = user.getUserId();

		AppUserAddress appUserAddress = (AppUserAddress) JSONObject.toBean(addressJson, AppUserAddress.class);
		String id = UUID.randomUUID().toString().replace("-", "");
		appUserAddress.setId(id);
		appUserAddress.setUserId(userId);
		appUserAddress.setCreateTime(new Date());

		/*如果没有设置过默认地址,则将其设置为默认地址*/
		List<AppUserAddressFull> addrList = this.appUserAddressMapper.getDefaultAddr(userId);
		if (addrList.size() > 0) {
			appUserAddress.setIsDefault(0);
		} else {
			appUserAddress.setIsDefault(1);
		}

		this.appUserAddressMapper.insert(appUserAddress);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 更新地址
	 * 
	 * @author gaochao
	 */
	@Override
	public Result update(String jsonString) {

		Result result = new Result();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONObject addressJson = jsonObject.getJSONObject("params");
		AppUserAddress appUserAddress = (AppUserAddress) JSONObject.toBean(addressJson, AppUserAddress.class);
		this.appUserAddressMapper.updateByPrimaryKeySelective(appUserAddress);

		AppUserAddressFull addressFull = this.appUserAddressMapper.selectById(appUserAddress.getId());

		result.setData(addressFull);
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 删除地址
	 * 
	 * @author gaochao
	 */
	@Override
	@Transactional
	public Result delete(String jsonString) {

		Result result = new Result();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONObject addressJson = jsonObject.getJSONObject("params");
		String addrId = addressJson.getString("addressId");
		String token = jsonObject.getString("token");

		/*取得用户对象*/
		AppUserInfo user = new AppUserInfo();
		user = (AppUserInfo) super.getRedisUserObject(RedisContants.USER_APP, token, user.getClass());

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		String userId = user.getUserId();

		this.appUserAddressMapper.deleteByPrimaryKey(addrId);

		/*如果删除的是默认地址,则设置其他地址为默认*/
		List<AppUserAddressFull> defaAddrList = this.appUserAddressMapper.getDefaultAddr(userId);
		if (defaAddrList.size() == 0) {
			List<AppUserAddressFull> addrList = this.appUserAddressMapper.selectByUser(userId);
			if (addrList != null && addrList.size() > 0) {
				AppUserAddressFull addressFull = addrList.get(0);
				AppUserAddress address = this.appUserAddressMapper.selectByPrimaryKey(addressFull
						.getId());
				address.setIsDefault(1);
				this.appUserAddressMapper.updateByPrimaryKeySelective(address);
			}
		}

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 取得某个地址
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getById(String jsonString) {

		Result result = new Result();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONObject addressJson = jsonObject.getJSONObject("params");
		String addrId = addressJson.getString("addressId");
		AppUserAddressFull appUserAddress = this.appUserAddressMapper.selectById(addrId);

		result.setData(appUserAddress);
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 取得用户的地址
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getListByUser(String jsonString) {

		Result result = new Result();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		String token = jsonObject.getString("token");

		/*取得用户对象*/
		AppUserInfo user = new AppUserInfo();
		user = (AppUserInfo) super.getRedisUserObject(RedisContants.USER_APP, token, user.getClass());

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		String userId = user.getUserId();

		List<AppUserAddressFull> addrList = this.appUserAddressMapper.selectByUser(userId);

		if(ListUtils.isEmpty(addrList)){
			result.setCode(CodeEnum.AddressGetNull.getValue());
			result.setMessage(CodeEnum.AddressGetNull.getDescription());
			return result;
		}
		result.setData(addrList);
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	/**
	 * 取得用户的地址,设置某个区域外的地址为无效
	 * 
	 * @author gaochao
	 */
	@Override
	public Result getListByUserFilterArea(String jsonString) {

		Result result = new Result();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		String token = jsonObject.getString("token");

		JSONObject paramsJson = jsonObject.getJSONObject("params");
		String areaId = paramsJson.getString("areaId");

		/*取得用户对象*/
		AppUserInfo user = new AppUserInfo();
		user = (AppUserInfo) super.getRedisUserObject(RedisContants.USER_APP, token, user.getClass());

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		String userId = user.getUserId();

		List<AppUserAddressFull> addrList = this.appUserAddressMapper.selectByUser(userId);

		if(ListUtils.isEmpty(addrList)){
			result.setCode(CodeEnum.AddressGetNull.getValue());
			result.setMessage(CodeEnum.AddressGetNull.getDescription());
			return result;
		}

		if (StringUtils.isNotBlank(areaId)) {

			addrList.parallelStream().forEach(
					addr -> {
						List<String> areaList = Arrays.asList(addr.getProvinceId(),
								addr.getCityId(), addr.getDistrictId(), addr.getTownsId(),
								addr.getCommunityId(), addr.getBuildingId());
						if (!areaList.contains(areaId)) {
							addr.setEffectFlag(false);
						}
					});
		}

		result.setData(addrList);
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}
}
