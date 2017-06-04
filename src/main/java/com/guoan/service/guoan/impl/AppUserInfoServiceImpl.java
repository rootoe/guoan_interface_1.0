package com.guoan.service.guoan.impl;

import java.util.*;

import alex.zhrenjie04.wordfilter.WordFilterUtil;
import com.guoan.dao.shequ.*;
import com.guoan.entity.base.state.StatusState;
import com.guoan.entity.shequ.bo.*;
import com.guoan.utils.*;
import com.guoan.utils.redis.RedisContants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easemob.server.example.httpclient.apidemo.EasemobIMUsers;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.param.UserInfoLoginParam;
import com.guoan.entity.shequ.param.UserInfoLoginSysParam;
import com.guoan.entity.shequ.param.UserInfoRegisterParam;
import com.guoan.service.guoan.AppUserInfoService;
import com.guoan.service.redis.RedisService;
import com.guoan.service.token.TokenGenerator;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.encode.MD5Util;
import com.guoan.utils.json.JsonDateValueProcessor;
import com.guoan.utils.verificode.VerifiCodeUtils;

/**
 * @Title: AppUserInfoServiceImpl.java
 * @Package com.guoan.service.guoan.impl
 * @Description: AppUserInfoService实现类
 * @author 赵彤
 * @date 2015年4月29日 下午4:07:31
 * @version V1.0
 */
@Service
public class AppUserInfoServiceImpl extends RedisService implements AppUserInfoService {

	private static final Logger logger = LoggerFactory.getLogger(AppUserInfoServiceImpl.class);

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private AppUserInfoMapper appUserInfoMapper;
	
	@Autowired
	private AppDeviceInfoMapper appDeviceInfoMapper;
	
	@Autowired
	private AppSoftwareInfoMapper appSoftwareInfoMapper;

	@Autowired
	private AppUserAddressMapper addressMapper;

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private SysCouponMapper couponMapper;

	@SuppressWarnings("static-access")
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Result register(String jsonString) {

		Result result = new Result();
		AppUserInfo appUserInfo = new AppUserInfo();
		logger.info("AppUserInfoServiceImpl register 请求到的json：>>>>>>>>>>>>>>>>>>>>>>" + jsonString);

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

		UserInfoRegisterParam userInfoParam = (UserInfoRegisterParam) paramsObject.toBean(paramsObject, UserInfoRegisterParam.class);
		if (!StringUtils.isBlank(userInfoParam.getVerificode()) && !StringUtils.isBlank(userInfoParam.getTelephone()) && !StringUtils.isBlank(userInfoParam.getPassword())) {

			if (!(userInfoParam.getTelephone().matches(Const.REGEX_PHONE_NUM_MAINLAND) || userInfoParam.getTelephone().matches(Const.REGEX_PHONE_NUM_HONGKONG))) {
				result.setCode(CodeEnum.illegalPhone.getValue());
				result.setMessage(CodeEnum.illegalPhone.getDescription());
				return result;
			}
			
			// 检测 -- 昵称过短
			if (StringUtils.isNotEmpty(userInfoParam.getNickname()) && userInfoParam.getNickname().length() < Const.NICKNAME_LENGTH) {
				result.setCode(CodeEnum.nicknameToshort.getValue());
				result.setMessage(CodeEnum.nicknameToshort.getDescription());
				return result;
			}
			// 检测 -- 短信验证码
			if (!VerifiCodeUtils.checkIdentifyingCode(userInfoParam.getTelephone(), userInfoParam.getVerificode())) {
				result.setCode(CodeEnum.identifyingCodeError.getValue());
				result.setMessage(CodeEnum.identifyingCodeError.getDescription());
				return result;
			}
			try {
				if(StringUtils.isNotEmpty(userInfoParam.getNickname())){
					appUserInfo.setNickname(userInfoParam.getNickname().trim());
				}
				appUserInfo.setTelephone(userInfoParam.getTelephone().trim());
				// 验证用户注册的手机号或者昵称是否重复
				if (0 == appUserInfoMapper.isTelephoneInDB(paramsObject.getString("telephone"))) { // 不存在
					String huanxinPassword = userInfoParam.getPassword();
					// isExist为空，插入数据库
					// 生成密码key(UUID)
					UUID uuid = UUID.randomUUID();
					String passKey = uuid.toString().replace("-", "");
					appUserInfo.setPassword(MD5Util.getStringMD5(userInfoParam.getPassword() + passKey));
					appUserInfo.setEasemobPassword(huanxinPassword);
					appUserInfo.setCode(passKey);
					String userId = UUID.randomUUID().toString().replace("-", "");
					appUserInfo.setUserId(userId);
					appUserInfo.setStatus(StatusState.normal.getValue());
					appUserInfo.setSex(Const.SEX_UNKNOWN);
					appUserInfo.setCreateTime(new Date());
					// 入库、返回token
//					String token = TokenUtil.getTokenStringByUserId(userId);
					
					// 用户信息
					int insertAppUserInfoResult = appUserInfoMapper.insertSelective(appUserInfo);
					
					// 客户端硬件信息
					AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
					appDeviceInfo.setUserId(userId);
					appDeviceInfoMapper.insert(appDeviceInfo);
					
					// 客户端软件信息
					AppSoftwareInfo appSoftwareInfo = new AppSoftwareInfo();
					appSoftwareInfo.setUserId(userId);
					appSoftwareInfoMapper.insert(appSoftwareInfo);
					
					
					if (insertAppUserInfoResult > 0) { // 保存成功
						// 生成并返回token
//						AppUserInfo user = appUserInfoMapper.selectByPrimaryKey(userId);
//						super.setRedisUserObject(token, user);
//						JSONObject tokenObject = new JSONObject();
//						tokenObject.put("token", token);

						try {
							// 向环信注册用户
							JsonNodeFactory factory = new JsonNodeFactory(false);
							ObjectNode objectNode = factory.objectNode();
							objectNode.put("username", userId);
							objectNode.put("nickname", userId);
							objectNode.put("password", huanxinPassword);
							logger.debug("userId-> "+userId+" huanxinPassword-> "+huanxinPassword);
							EasemobIMUsers.createNewIMUserSingle(objectNode);
						} catch (RuntimeException e) {
							result.setCode(CodeEnum.unknow.getValue());
							result.setMessage(CodeEnum.unknow.getDescription());
							return result;
						}

						// 返回user对象
						// user.setPassword(null);
						// user.setCode(null);
						// user.setAvatar("");
						// result.setUser(user);

						result.setCode(CodeEnum.success.getValue());
						result.setMessage(CodeEnum.success.getDescription());
					}
					
					/*发放优惠券 add  by gaochao*/
					if( this.whetherCouponInRegister() ){
						this.couponMapper.updateBindUserId(userId);
					}
					
				} else {
					// 昵称或手机号重复
					result.setCode(CodeEnum.telephoneExists.getValue());
					result.setMessage(CodeEnum.telephoneExists.getDescription());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
		}
		return result;
	}

	@Override
	public Result identifyingCode(String jsonString) {
		logger.info("获取json串：>>>>>>>>>>>>>>" + jsonString);
		Result result = new Result();
		String identifyingCode;
		// 生成验证码
		String randomString = new Random().nextDouble() + "";
		randomString = randomString.substring(randomString.indexOf(".") + 1, randomString.indexOf(".") + 7);
		JSONObject jObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(jObject.get("params"));
		String phoneNum = paramsObject.get("telephone").toString();
		String type = paramsObject.get("type").toString();
		identifyingCode = randomString;
		// 验证电话号码
		if (phoneNum.matches(Const.REGEX_PHONE_NUM_MAINLAND) || phoneNum.matches(Const.REGEX_PHONE_NUM_HONGKONG)) {
			// 验证电话号码在库中是否存在
			if (isTelephoneInDB(phoneNum, type)) {
				if (Const.YUNTONGXUN_SWITCH) {
					// 调用CCP方法发送手机短信
					CCPRestSmsUtil ccpRestSmsUtil = new CCPRestSmsUtil();
					ccpRestSmsUtil.sendIdentifiyCode(phoneNum, Const.YUNTONGXUN_MODEL_YANZHENGMA, identifyingCode, Const.YUNTONGXUN_CODE_SURVIVAL_TIME_DEFAULT);
				}
				// 增加缓存
				String cacheKey = MD5Util.getStringMD5(identifyingCode + phoneNum);
				super.setRedisIdentifyingCode(cacheKey, phoneNum);
				
				// 返回验证码
				JSONObject verificodeObject = new JSONObject();
				verificodeObject.put("verificode", identifyingCode);
				result.setData(verificodeObject);
				result.setCode(CodeEnum.success.getValue());
				result.setMessage(CodeEnum.success.getDescription());
			} else {
				if (type.trim().equals("register")) {
					result.setCode(CodeEnum.telePhoneExist.getValue());
					result.setMessage(CodeEnum.telePhoneExist.getDescription());
				} else if (type.trim().equals("findPassword")) {
					result.setCode(CodeEnum.telePhoneNotExist.getValue());
					result.setMessage(CodeEnum.telePhoneNotExist.getDescription());
				}

			}
		} else {
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage("移动电话号码非法！");
		}
		return result;
	}

	/**
	 * @Description : 验证手机号
	 * @创建人：赵彤
	 * @创建时间：2015年4月30日 下午3:34:32
	 * @param phoneNum
	 *            需要验证的手机号码
	 * @param type
	 *            区分接口字段 register:注册接口 findPassword：找回密码接口
	 * @return boolean
	 * @throws
	 */
	boolean isTelephoneInDB(String phoneNum, String type) {
		boolean result = true;
		int dbCount = appUserInfoMapper.isTelephoneInDB(phoneNum);
		if (dbCount > 0) {
			if (type.trim().equals("register")) {
				logger.debug("请求验证码：【" + phoneNum + "】在库中存在，验证不通过！！！");
				result = false;
			} else if (type.trim().equals("findPassword")) {
				logger.debug("请求验证码：【" + phoneNum + "】在库中存在，验证通过！！！");
				result = true;
			}
		} else {
			if (type.trim().equals("register")) {
				logger.debug("请求验证码：【" + phoneNum + "】在库中不存在，验证通过！！！");
				result = true;
			} else if (type.trim().equals("findPassword")) {
				logger.debug("请求验证码：【" + phoneNum + "】在库中不存在，验证不通过！！！");
				result = false;
			}
		}
		return result;
	}

	/**
	 * 找回密码
	 * 
	 * @author gaochao
	 */
	@Override
	@Transactional
	public Result findPassword(String jsonString) {

		Result result = new Result();

		JSONObject passwordJson = JSONObject.fromObject(jsonString).getJSONObject("params");
		String telephone = passwordJson.getString("telephone");
		String verificode = passwordJson.getString("verificode");

		/* 验证码+手机号作为key,在redis中查找验证码 */
		String key =  MD5Util.getStringMD5(verificode + telephone);
		String value = super.getRedisIdentifyingCode(key);

		/* 验证码是否匹配 */
		if (StringUtils.isNotEmpty(value)) {
			String password = passwordJson.getString("password");

			Map<String, String> record = new HashMap<String, String>();
			record.put("telephone", telephone);

			UUID uuid = UUID.randomUUID();
			String passKey = uuid.toString().replace("-", "");
			record.put("code", passKey);
			record.put("password", MD5Util.getStringMD5(password + passKey));

			/* 更新密码和code */
			this.appUserInfoMapper.updatePasswordByTelephone(record);

			result.setCode(CodeEnum.success.getValue());
			result.setMessage(CodeEnum.success.getDescription());

		} else {
			result.setCode(CodeEnum.identifyingCodeError.getValue());
			result.setMessage(CodeEnum.identifyingCodeError.getDescription());
		}

		return result;
	}

	/**
	 * 退出登录
	 * 
	 * @author gaochao
	 */
	@Override
	public Result logout(String jsonString) {

		Result result = new Result();
		try {
			JSONObject passwordJson = JSONObject.fromObject(jsonString);
			String token = passwordJson.getString("token");
			super.deleteKey(token);
		} catch (Exception e) {
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			return result;
		}
		
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

	@Override
	@Transactional
	public Result login(String jsonString) {
		Result result = new Result();
		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		JSONObject sysparamsObject = JSONObject.fromObject(requestJsonObject.get("sysparams"));
		
		UserInfoLoginParam userInfoLoginParam = (UserInfoLoginParam)JSONObject.toBean(paramsObject, UserInfoLoginParam.class);
		UserInfoLoginSysParam userInfoLoginSysParam = (UserInfoLoginSysParam)JSONObject.toBean(sysparamsObject, UserInfoLoginSysParam.class);
		
		String telephone = userInfoLoginParam.getTelephone();
		String password = userInfoLoginParam.getPassword();

		if (StringUtils.isNotBlank(telephone) && StringUtils.isNotBlank(password) && (telephone.matches(Const.REGEX_PHONE_NUM_MAINLAND) || telephone.matches(Const.REGEX_PHONE_NUM_HONGKONG))) {
			// 验证用户名密码
			List<AppUserInfo> appUserInfoList = new ArrayList<AppUserInfo>();
			AppUserInfoCriteria appUserInfoCriteria = new AppUserInfoCriteria();
			appUserInfoCriteria.createCriteria().andTelephoneEqualTo(telephone).andStatusEqualTo(StatusState.normal.getValue());
			appUserInfoList = appUserInfoMapper.selectByExample(appUserInfoCriteria);
			
			if (!ListUtils.isEmpty(appUserInfoList)) {
				AppUserInfo appUserInfo = appUserInfoList.get(0);
				String pd = MD5Util.getStringMD5(password + appUserInfo.getCode());
				if (appUserInfo.getPassword().equals(pd)) {
					result.setCode(CodeEnum.success.getValue());
					result.setMessage(CodeEnum.success.getDescription());
					// 生成token
					String tokenString = tokenGenerator.generatorTokenWithLogin(appUserInfo, Const.MEMCACHED_TOKEN_CODE_LIVE_TIME);
					appUserInfo.setPassword(password);
					appUserInfo.setCode(null);
					appUserInfo.setToken(tokenString);
					
					// 返回json前对日期数据进行格式化
					JsonConfig jsonConfig = new JsonConfig();
					jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
					JSONObject appUserInfoJson = JSONObject.fromObject(appUserInfo, jsonConfig);
					
					//返回用户默认地址
					List<AppUserAddressFull> defaultAddr = this.addressMapper.getDefaultAddr(appUserInfo.getUserId());
					if( defaultAddr != null && defaultAddr.size() > 0 ){
						appUserInfoJson.accumulate("defaultAddr", defaultAddr.get(0));
					}

					result.setData(appUserInfoJson);
					
					// super.setRedisUserObject(RedisContants.USER_APP, tokenString, appUserInfo);


					if(null != userInfoLoginSysParam){
						// 客户端硬件信息
						AppDeviceInfo appDeviceInfo = new AppDeviceInfo();
						appDeviceInfo.setUserId(appUserInfo.getUserId());
						appDeviceInfo.setClientIp(userInfoLoginSysParam.getClientIp());
						appDeviceInfo.setClientToken(userInfoLoginSysParam.getClientToken());
						appDeviceInfo.setPhoneModel(userInfoLoginSysParam.getPhoneModel());
						appDeviceInfo.setPhoneBrand(userInfoLoginSysParam.getPhoneBrand());
						appDeviceInfo.setPhoneScreenWidth(userInfoLoginSysParam.getPhoneScreenWidth());
						appDeviceInfo.setPhoneScreenHeight(userInfoLoginSysParam.getPhoneScreenHeight());
						appDeviceInfo.setCreateTime(new Date());

						appDeviceInfoMapper.updateByPrimaryKeySelective(appDeviceInfo);

						// 客户端软件信息
						AppSoftwareInfo appSoftwareInfo = new AppSoftwareInfo();
						appSoftwareInfo.setUserId(appUserInfo.getUserId());
						appSoftwareInfo.setClientVersion(userInfoLoginSysParam.getClientVersion());
						appSoftwareInfo.setClientPlatform(userInfoLoginSysParam.getClientPlatform());
						appSoftwareInfo.setHannels(userInfoLoginSysParam.getHannels());
						appSoftwareInfo.setCreateTime(new Date());

						appSoftwareInfoMapper.updateByPrimaryKeySelective(appSoftwareInfo);
					}

					// 更新用户登陆时间
					AppUserInfo updateAppUserInfo = new AppUserInfo();
					updateAppUserInfo.setUserId(appUserInfo.getUserId());
					updateAppUserInfo.setLastLoginTime(new Date());
					appUserInfoMapper.updateByPrimaryKeySelective(updateAppUserInfo);
					
				} else {
					result.setCode(CodeEnum.passwordErr.getValue());
					result.setMessage(CodeEnum.passwordErr.getDescription());
				}
			} else{
				result.setCode(CodeEnum.telePhoneNotExist.getValue());
				result.setMessage(CodeEnum.telePhoneNotExist.getDescription());
			}
		} else {
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
		}
		return result;
	}
	
	/**
	 * 修改密码
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	@Override
	public Result changePassword(String jsonString) {
		
		Result result = new Result();
		
		JSONObject passwordJson = JSONObject.fromObject(jsonString).getJSONObject("params");
		String token = JSONObject.fromObject(jsonString).getString("token");
		AppUserInfo user = new AppUserInfo();
		user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());
		user = this.appUserInfoMapper.selectByPrimaryKey(user.getUserId());
		
		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}
		
		String code = user.getCode();
		String oldPassword = passwordJson.getString("oldPassword");
		String passwordMD5 = MD5Util.getStringMD5(oldPassword + code);
		if( user.getPassword().equals(passwordMD5) ){

			String newPassword = passwordJson.getString("newPassword");
			String telephone = user.getTelephone();
			
			Map<String, String> record = new HashMap<String, String>();
			record.put("telephone", telephone);
			
			UUID uuid = UUID.randomUUID();
			String passKey = uuid.toString().replace("-", "");
			record.put("code", passKey);
			record.put("password", MD5Util.getStringMD5(newPassword + passKey));
			
			/*更新密码和code*/
			this.appUserInfoMapper.updatePasswordByTelephone(record);
			
			result.setCode(CodeEnum.success.getValue());
			result.setMessage(CodeEnum.success.getDescription());
			
		} else {
			
			result.setCode(CodeEnum.passwordErr.getValue());
			result.setMessage(CodeEnum.passwordErr.getDescription());
		}

		return result;
		
	}

	/**
	 * 修改性别
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	@Override
	public Result updateSex(String jsonString) {
		
		Result result = new Result();
		
		JSONObject json = JSONObject.fromObject(jsonString).getJSONObject("params");
		String token = JSONObject.fromObject(jsonString).getString("token");
		
		AppUserInfo user = new AppUserInfo();
//		user = (AppUserInfo) super.getObject(token,user.getClass());
		user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());
		
		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}
		
		
		Map<String, String> record = new HashMap<String, String>();
		record.put("sex", json.getString("sex"));
		record.put("id", user.getUserId());
		
		this.appUserInfoMapper.updateSex(record);
		
		user.setSex(Integer.valueOf(json.getString("sex")));
		
		super.setRedisUserObject(RedisContants.USER_APP, token, user);
		
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());
		
		return result;
	}

	/**
	 * 修改昵称
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	@Override
	public Result updateNickname(String jsonString) {
		
		Result result = new Result();
		
		JSONObject json = JSONObject.fromObject(jsonString).getJSONObject("params");
		String token = JSONObject.fromObject(jsonString).getString("token");
		
		AppUserInfo user = new AppUserInfo();
		user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());
		
		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}
		
		Map<String, String> record = new HashMap<String, String>();
		record.put("nickname", json.getString("nickname"));
		record.put("id", user.getUserId());
		
		int nameCount = this.appUserInfoMapper.isNicknameInDB(record);
		
		if( nameCount > 0 ){
			
			result.setCode(CodeEnum.nicknameExists.getValue());
			result.setMessage(CodeEnum.nicknameExists.getDescription());
			
			return result;
		}
		// update by zhaotong 
		// 1.判断保存结果.
		// 2.返回用户nickname
		int opt_result =  this.appUserInfoMapper.updateNickname(record);
		if(opt_result>0){
			user.setNickname(json.getString("nickname"));
			super.setRedisUserObject(RedisContants.USER_APP, token, user);
			
			result.setCode(CodeEnum.success.getValue());
			result.setMessage(CodeEnum.success.getDescription());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("nickname", user.getNickname());
			result.setData(jsonObject);
			
		} else {
			result.setCode(CodeEnum.error.getValue());
			result.setMessage(CodeEnum.error.getDescription());
		}
		
		return result;
	}

	/**
	 * 修改头像
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	@Override
	public Result updateAvatar(String jsonString) {
		
		Result result = new Result();
		
		JSONObject json = JSONObject.fromObject(jsonString).getJSONObject("params");
		String token = JSONObject.fromObject(jsonString).getString("token");
		
		AppUserInfo user = new AppUserInfo();
		user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());
		
		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}
		
		Map<String, String> record = new HashMap<String, String>();
		record.put("avatar", json.getString("avatar"));
		record.put("id", user.getUserId());
		
		this.appUserInfoMapper.updateAvatar(record);
		
		user.setAvatar(json.getString("avatar"));
		super.setRedisUserObject(RedisContants.USER_APP, token, user);
		
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());
		
		return result;
	}

	@Override
	public Result updateProfile(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		String token = requestJsonObject.getString("token");
		
		AppUserInfo user = new AppUserInfo();
		user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());
		
		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}
		
		String userId = user.getUserId();

		AppUserInfo userInfo = new AppUserInfo();
		userInfo.setUserId(userId);
		
		/*判断昵称重复*/
		String nickname = (String)paramsObject.get("nickname");
		if( StringUtils.isNotBlank(nickname) ){
			
			Map<String, String> record = new HashMap<String, String>();
			record.put("nickname", nickname);
			record.put("id", userId);
			
			int nameCount = this.appUserInfoMapper.isNicknameInDB(record);
			
			if( nameCount > 0 ){
				
				result.setCode(CodeEnum.nicknameExists.getValue());
				result.setMessage(CodeEnum.nicknameExists.getDescription());
				
				return result;
			}
			//过滤敏感词
			nickname = WordFilterUtil.filterText(nickname, '*').getFilteredContent();
			paramsObject.put("nickname", nickname);
			userInfo.setNickname(nickname);
		}
		
		/*性别*/
		Object sex = paramsObject.get("sex");
		if( null != sex ){
			int sexInt = Integer.valueOf(sex.toString());
			if(sexInt>0){
				userInfo.setSex(sexInt);
			}
		}
		
		/*头像*/
		String avatar = (String)paramsObject.get("avatar");
		if( StringUtils.isNotBlank(avatar) ){
			userInfo.setAvatar(avatar);
		}
		if(StringUtils.isNotEmpty(nickname) || StringUtils.isNotEmpty(avatar) || null != sex){
			this.appUserInfoMapper.updateByPrimaryKeySelective(userInfo);
			user = this.appUserInfoMapper.selectByPrimaryKey(user.getUserId());
			super.setRedisUserObject(RedisContants.USER_APP, token, user);
			
			result.setCode(CodeEnum.success.getValue());
			result.setMessage(CodeEnum.success.getDescription());
			result.setData(paramsObject);
		} else {
			result.setCode(CodeEnum.error.getValue());
			result.setMessage(CodeEnum.error.getDescription());
		}
		
		return result;
	}
	
	/**
	 * 取得用户优惠券
	 * @param jsonString
	 * @return
	 */
	@Override
	public Result getCouponListByUser(String jsonString) {

		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		String token = requestJsonObject.getString("token");
		
		AppUserInfo user = new AppUserInfo();
		user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());
		
		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}
		
		/*根据用户ID和优惠券状态查询*/
		SysCouponCriteria couponCriteria = new SysCouponCriteria();
		String status = paramsObject.getString("status");
		if( StringUtils.isBlank(status) ){
			couponCriteria.createCriteria().andUserIdEqualTo(user.getUserId()).andStatusNotEqualTo(OrderConst.COUPON_LOCK);
		} else {
			couponCriteria.createCriteria().andUserIdEqualTo(user.getUserId()).andStatusEqualTo(status).andExpirationDateGreaterThanOrEqualTo(new Date());
		}
		couponCriteria.setOrderByClause("amount desc");

		List<SysCoupon> couponList = this.couponMapper.selectByExample(couponCriteria);

		if(ListUtils.isEmpty(couponList)){
			result.setCode(CodeEnum.CouponGetNull.getValue());
			result.setMessage(CodeEnum.CouponGetNull.getDescription());
			return result;
		}

		for(SysCoupon sysCoupon : couponList){
			if(DateUtil.compareDate(new Date(System.currentTimeMillis() / 86400000 * 86400000 - (23 - Calendar.ZONE_OFFSET) * 3600000), sysCoupon.getExpirationDate())){
				sysCoupon.setExpiration(true);
			}else {
				sysCoupon.setExpiration(false);
			}
		}
		
		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("couponList", couponList);
		
		/*数据格式化*/

		JsonConfig jsonConfig = JsonUtil.getJsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(DateUtil.DATE_FORMAT));
		JSONObject resultMapJson = JSONObject.fromObject(resultMap, jsonConfig);
		result.setData(resultMapJson);
		
		return result;
		
	}

	@Override
	public Result messageList(String jsonString) {
		Result result = new Result();

		JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
		JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
		String token = requestJsonObject.getString("token");
		int page = paramsObject.getInt("page");

		AppUserInfo user = new AppUserInfo();
		user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		JSONArray WorkOrderVOJSONArray = new JSONArray();

		List<Message> messageList = new ArrayList<Message>();

		MessageCriteria messageCriteria = new MessageCriteria();
		messageCriteria.createCriteria().andUserIdEqualTo(user.getUserId());

		int count = messageMapper.countByExample(messageCriteria);

		if (page < 1) {
			page = 1;
		}
		messageCriteria.setOrderByClause("create_time desc");
		messageCriteria.setLimitStart((page - 1) * 10);
		messageCriteria.setLimitEnd(10);
		messageList = messageMapper.selectByExample(messageCriteria);

		if(ListUtils.isEmpty(messageList)){
			result.setCode(CodeEnum.MessageGetNull.getValue());
			result.setMessage(CodeEnum.MessageGetNull.getDescription());
			return result;
		}

		int pageCount = (int) Math.ceil((double) count / 10);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pageNum", page);
		resultMap.put("pageCount", pageCount);

		// 返回json前对日期数据进行格式化
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		resultMap.put("messageList", JSONArray.fromObject(messageList, jsonConfig));

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());
		result.setData(resultMap);

		return result;
	}

	/**
	 * 判断注册时是否送优惠券
	 * @return
	 */
	private boolean whetherCouponInRegister(){
		boolean swich = false;
		String swichStr = SysConstants.getBackgroundConfig().getProperty("coupon.register.switch");
		if( swichStr != null && swichStr.equals(Const.SWITCH_ON) ){
			swich = true;
		}
		return swich;
	}
}
