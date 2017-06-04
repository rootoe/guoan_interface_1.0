package com.guoan.controller.shequ;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.service.guoan.AppUserInfoService;
import com.guoan.service.token.TokenGenerator;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;
/**
 * @Title: UserController.java
 * @Package com.guoan.controller.shequ
 * @Description: 用户登陆注册等等
 * @author 赵彤 
 * @date 2015年4月29日 下午3:55:25
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<UserController>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	AppUserInfoService appUserInfoService;
	
	@Autowired
	private TokenGenerator tokenGenerator;
	/**
	  * @Description : 默认
	  * @创建人：赵彤  
	  * @创建时间：2015年5月5日 上午11:18:26  
	  * @return void
	  * @throws
	 */
	@RequestMapping(value = {""})
	@ResponseBody
	public void nothing (HttpServletRequest request,HttpServletResponse response) {
		Result result = Result.successResult();
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	
	/**
	  * @Description : 用户注册
	  * @创建人：赵彤  
	  * @创建时间：2015年4月29日 下午4:00:32  
	  * @return void
	  * @throws
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public void register(HttpServletRequest request,HttpServletResponse response) {
		Result result = new Result();
		try {
			String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
			// 测试
			logger.debug("用户注册传递数据 -> "+jsonString);
			if(StringUtils.isBlank(jsonString)){
				result.setCode(CodeEnum.paramErr.getValue());
				result.setMessage(CodeEnum.paramErr.getDescription());
				JsonUtil jsonUtils = new JsonUtil();
				jsonUtils.renderJson(result, response);
				return;
			}
			
			result = appUserInfoService.register(jsonString);
		} catch (Exception e) {
			// 未知错误
			result.setCode(CodeEnum.unknow.getValue());
			result.setMessage(CodeEnum.unknow.getDescription());
			e.printStackTrace();
		}
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	/**
	  * @Description : 手机验证码
	  * @创建人：赵彤  
	  * @创建时间：2015年4月30日 下午3:28:16  
	  * @return void
	  * @throws
	 */
	@RequestMapping(value = "/identifyingCode", method = RequestMethod.POST)
	@ResponseBody
	public void identifyingCode(HttpServletRequest request,HttpServletResponse response) {
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		logger.debug("jsonString->" + jsonString);
		if(null == jsonString){
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			jsonResult.put("user_identifyingCode_response", result);
			return;
		}
		result = appUserInfoService.identifyingCode(jsonString);
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	/**
	 * 退出登录
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public void logout(HttpServletRequest request,HttpServletResponse response){
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result = this.appUserInfoService.logout(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
		
	}

	/**
	 * 找回密码
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	@ResponseBody
	public void findPassword(HttpServletRequest request,HttpServletResponse response){
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(null == jsonString){
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
		} else {
			result = this.appUserInfoService.findPassword(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public void changePassword(HttpServletRequest request,HttpServletResponse response){
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(StringUtils.isEmpty(jsonString)){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.appUserInfoService.changePassword(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
		
	}
	
	/**
	  * @Description : 登陆
	  * @创建人：赵彤  
	  * @创建时间：2015年5月4日 下午6:53:05  
	  * @return void
	  * @throws
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public void login(HttpServletRequest request,HttpServletResponse response) {
		JsonUtil jsonUtils = new JsonUtil();

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(StringUtils.isEmpty(jsonString)){
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			jsonUtils.renderJson(result, response);
			return;
		} else {
			result = this.appUserInfoService.login(jsonString);
			jsonUtils.renderJson(result, response);
		}
		

	}
	
	/**
	 * 变更性别
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateSex", method = RequestMethod.POST)
	@ResponseBody
	public void updateSex(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.appUserInfoService.updateSex(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
		
	}
	
	/**
	 * 变更
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	@ResponseBody
	public void updateProfile(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.appUserInfoService.updateProfile(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
		
	}
	
	/**
	 * 变更昵称
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateNickname", method = RequestMethod.POST)
	@ResponseBody
	public void updateNickname(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.appUserInfoService.updateNickname(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
		
	}
	
	/**
	 * 变更头像
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateAvatar", method = RequestMethod.POST)
	@ResponseBody
	public void updateAvatar(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.appUserInfoService.updateAvatar(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
		
	}
	
	/**
	 * 取得用户优惠券
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getCouponList", method = RequestMethod.POST)
	@ResponseBody
	public void getCouponListByUser(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		if(null == jsonString){
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
		} else {
			result =this.appUserInfoService.getCouponListByUser(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
		
	}

	/**
	 * 我的消息
	 * @author 赵彤
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/message/list", method = RequestMethod.POST)
	@ResponseBody
	public void message(HttpServletRequest request,HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if(null == jsonString){
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
		} else {
			result =this.appUserInfoService.messageList(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);

	}
	
}
