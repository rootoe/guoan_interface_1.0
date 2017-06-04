package com.guoan.controller.shequ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.service.guoan.AppUserAddressService;
import com.guoan.utils.JsonUtil;

/**
 * 用户管理地址
 * @author gaochao
 *
 */
@Controller
@RequestMapping("/user/address")
public class UserAddressController extends BaseController<UserAddressController> {

	private static final long serialVersionUID = -2503548742032387965L;

	@Autowired
	AppUserAddressService addressService;
	
	/**
	 * 设置默认地址
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/setDefault", method = RequestMethod.POST)
	@ResponseBody
	public void setDefault(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.addressService.setDefault(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	/**
	 * 新增地址
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public void save(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.addressService.save(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	/**
	 * 变更地址
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.addressService.update(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	/**
	 * 删除地址
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.addressService.delete(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	/**
	 * 取得某个地址
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	@ResponseBody
	public void getById(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.addressService.getById(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	/**
	 * 取得用户地址列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getListByUser", method = RequestMethod.POST)
	@ResponseBody
	public void getListByUser(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.addressService.getListByUser(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	/**
	 * 取得用户的地址,设置某个区域外的地址为无效
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getListByUserFilterArea", method = RequestMethod.POST)
	@ResponseBody
	public void getListByUserFilterArea(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		if(null == jsonString){
			
			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());
			
		} else {
			result =this.addressService.getListByUserFilterArea(jsonString);
		}
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

}
