package com.guoan.service.guoan.impl;

import java.util.Date;
import java.util.UUID;

import com.guoan.utils.StringUtils;
import com.guoan.utils.redis.RedisContants;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoan.dao.shequ.AppFeedbackMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.AppFeedback;
import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.service.guoan.AppFeedbackService;
import com.guoan.service.redis.RedisService;

@Service
public class AppFeedbackServiceImpl implements AppFeedbackService {

	@Autowired
	AppFeedbackMapper feedbackMapper;

	@Autowired
	private RedisService redisService;

	@Override
	public Result addFeedback(String jsonString) {

		Result result = new Result();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONObject feedbackJson = jsonObject.getJSONObject("params");
		String token = jsonObject.getString("token");

		/*取得用户对象*/
		AppUserInfo user = new AppUserInfo();
		user = (AppUserInfo) this.redisService.getRedisUserObject(RedisContants.USER_APP, token, user.getClass());

		if (null == user || StringUtils.isEmpty(user.getUserId())) {
			result.setCode(CodeEnum.tokenErr.getValue());
			result.setMessage(CodeEnum.tokenErr.getDescription());
			return result;
		}

		String userId = user.getUserId();

		AppFeedback feedback = (AppFeedback) JSONObject.toBean(feedbackJson, AppFeedback.class);
		String id = UUID.randomUUID().toString().replace("-", "");
		feedback.setId(id);
		feedback.setUserId(userId);
		feedback.setUserType("user");
		feedback.setCreateTime(new Date());

		this.feedbackMapper.insert(feedback);

		result.setCode(CodeEnum.success.getValue());
		result.setMessage(CodeEnum.success.getDescription());

		return result;
	}

}
