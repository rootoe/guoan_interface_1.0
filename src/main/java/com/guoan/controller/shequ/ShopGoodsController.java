package com.guoan.controller.shequ;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.service.guoan.AppActivityService;
import com.guoan.service.guoan.ShopGoodService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品控制器
 * @author 赵彤
 *
 */
@Controller
@RequestMapping("/common/good")
public class ShopGoodsController extends BaseController<ShopGoodsController> {

	private static final long serialVersionUID = -2454126179679275844L;
	
	@Autowired
	ShopGoodService shopGoodService;

	/**
	 * 搜索产品
	 *
	 * @param request
	 * @param response
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
			result = this.shopGoodService.search(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 商品列表
	 *
	 * @author 赵彤
	 * @param request
	 * @param response
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
			result = shopGoodService.list(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 商品首页列表
	 * 
	 * @author 赵彤
	 * @param request
	 * @param response
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
			result = shopGoodService.index(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 商品详情
	 *
	 * @author 赵彤
	 * @param request
	 * @param response
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
			result = shopGoodService.info(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 商品详情
	 *
	 * @author 赵彤
	 * @param request
	 * @param response
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
			result = shopGoodService.buy(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
}
