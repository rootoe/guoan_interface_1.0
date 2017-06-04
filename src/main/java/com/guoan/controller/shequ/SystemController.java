package com.guoan.controller.shequ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.Result;
import com.guoan.service.guoan.AppSystemService;
import com.guoan.utils.JsonUtil;

/**
 * 取得系统数据
 * @author gaochao
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController<SystemController> {
	
	private static final long serialVersionUID = -5485768428435896536L;

	@Autowired
	private AppSystemService sysService;
	
	/**
	 * 取得地址树
	 * @param request
	 * @param response
	 * @author gaochao
	 */
	@RequestMapping(value = "/getAddrTree", method = RequestMethod.POST)
	@ResponseBody
	public void getAddrTree(HttpServletRequest request,HttpServletResponse response) {
		
		Result result = this.sysService.getAddressTree();
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 根据级别,取得地址
	 * @author gaochao
	 */
	@RequestMapping(value = "/getAddressByLevel", method = RequestMethod.POST)
	@ResponseBody
	public void getAddressByLevel(HttpServletRequest request,HttpServletResponse response) {

		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		Result result = this.sysService.getAddressByLevel(jsonString);
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 根据类型和区域,取得服务商列表
	 * @author gaochao
	 */
	@RequestMapping(value = "/getServiceProvider", method = RequestMethod.POST)
	@ResponseBody
	public void getServiceProvider(HttpServletRequest request,HttpServletResponse response) {

		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		Result result = this.sysService.getProviderListByTypeAndArea(jsonString);
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 根据类型和区域,取得服务商列表
	 * @author gaochao
	 */
	@RequestMapping(value = "/getServiceProviderF", method = RequestMethod.POST)
	@ResponseBody
	public void getServiceProviderF(HttpServletRequest request,HttpServletResponse response) {

		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		Result result = this.sysService.getProviderListByTypeAndForwardArea(jsonString);
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 根据类型类型、区域及父区域,取得价格套餐
	 * @author gaochao
	 */
	@RequestMapping(value = "/getPricePackageF", method = RequestMethod.POST)
	@ResponseBody
	public void getPricePackageF(HttpServletRequest request,HttpServletResponse response) {

		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		Result result = this.sysService.getPricePackageListByTypeAndForwardArea(jsonString);
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 根据类型和区域,取得价格套餐
	 * @author gaochao
	 */
	@RequestMapping(value = "/getPricePackage", method = RequestMethod.POST)
	@ResponseBody
	public void getPricePackage(HttpServletRequest request,HttpServletResponse response) {

		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		Result result = this.sysService.getPricePackageListByTypeAndArea(jsonString);
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 根据code,取得数据字典
	 * @author gaochao
	 */
	@RequestMapping(value = "/getDictionaryByType", method = RequestMethod.POST)
	@ResponseBody
	public void getDictionaryByType(HttpServletRequest request,HttpServletResponse response) {

		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
		
		Result result = this.sysService.getDictionaryByType(jsonString);
		
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 取得轮播广告列表
	 * 
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getRollAdList", method = RequestMethod.POST)
	@ResponseBody
	public void getRollAdList(HttpServletRequest request, HttpServletResponse response) {

		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		Result result = this.sysService.getRollAdList(jsonString);

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

}
