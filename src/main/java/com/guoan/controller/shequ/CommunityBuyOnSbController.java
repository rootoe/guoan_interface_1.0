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
import com.guoan.service.guoan.CommunityBuyOnSbService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;

@Controller
@RequestMapping("/shop/goods")
public class CommunityBuyOnSbController extends BaseController<CommunityBuyOnSbController> {

private static final long serialVersionUID = -2454126179679275844L;

@Autowired
CommunityBuyOnSbService communityBuyOnSbService;
	
/**  
* @MethodName: search
* @Description: 搜索产品
* @Author: 
* @Version: V_1.5
* @Create Date: 2015-09-06
* @Parameters: request，response
* @Return: 无
*/
@RequestMapping(value = "/search", method = RequestMethod.POST)
@ResponseBody
public void search(HttpServletRequest request, HttpServletResponse response) {

	Result result = new Result();
	String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

	if (StringUtils.isBlank(jsonString)) {

		result.setCode(CodeEnum.paramErr.getValue());
		result.setMessage(CodeEnum.paramErr.getDescription());

	} else {
		result = this.communityBuyOnSbService.search(jsonString);
	}

	JsonUtil jsonUtils = new JsonUtil();
	jsonUtils.renderJson(result, response);
}

/**  
* @MethodName: list
* @Description: 商品列表
* @Author: 李天宇
* @Version: V_1.5
* @Create Date: 2015-09-06
* @Parameters: request，response
* @Return: 无
*/
@RequestMapping(value = "/list", method = RequestMethod.POST)
@ResponseBody
public void list(HttpServletRequest request, HttpServletResponse response) {

	Result result = new Result();
	String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

	if (StringUtils.isBlank(jsonString)) {

		result.setCode(CodeEnum.paramErr.getValue());
		result.setMessage(CodeEnum.paramErr.getDescription());

	} else {
		result = communityBuyOnSbService.list(jsonString);
	}

	JsonUtil jsonUtils = new JsonUtil();
	jsonUtils.renderJson(result, response);
}

/**  
* @MethodName: index
* @Description: 商品首页列表
* @Author: 李天宇
* @Version: V_1.5
* @Create Date: 2015-09-06
* @Parameters: request，response
* @Return: 无
*/
@RequestMapping(value = "/index", method = RequestMethod.POST)
@ResponseBody
public void index(HttpServletRequest request, HttpServletResponse response) {

	Result result = new Result();
	String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

	if (StringUtils.isBlank(jsonString)) {

		result.setCode(CodeEnum.paramErr.getValue());
		result.setMessage(CodeEnum.paramErr.getDescription());

	} else {
		result = communityBuyOnSbService.index(jsonString);
	}

	JsonUtil jsonUtils = new JsonUtil();
	jsonUtils.renderJson(result, response);
}

/**  
* @MethodName: info
* @Description: 商品详情
* @Author: 李天宇
* @Version: V_1.5
* @Create Date: 2015-09-06
* @Parameters: request，response
* @Return: 无
*/
@RequestMapping(value = "/info", method = RequestMethod.POST)
@ResponseBody
public void info(HttpServletRequest request, HttpServletResponse response) {

	Result result = new Result();
	String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

	if (StringUtils.isBlank(jsonString)) {

		result.setCode(CodeEnum.paramErr.getValue());
		result.setMessage(CodeEnum.paramErr.getDescription());

	} else {
		result = communityBuyOnSbService.info(jsonString);
	}

	JsonUtil jsonUtils = new JsonUtil();
	jsonUtils.renderJson(result, response);
}

/**  
* @MethodName: buy
* @Description: 商品详情
* @Author: 李天宇
* @Version: V_1.5
* @Create Date: 2015-09-06
* @Parameters: request，response
* @Return: 无
*/
@RequestMapping(value = "/buy", method = RequestMethod.POST)
@ResponseBody
public void buy(HttpServletRequest request, HttpServletResponse response) {

	Result result = new Result();
	String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

	if (StringUtils.isBlank(jsonString)) {

		result.setCode(CodeEnum.paramErr.getValue());
		result.setMessage(CodeEnum.paramErr.getDescription());

	} else {
		result = communityBuyOnSbService.buy(jsonString);
	}

	JsonUtil jsonUtils = new JsonUtil();
	jsonUtils.renderJson(result, response);
}
	

	
}
