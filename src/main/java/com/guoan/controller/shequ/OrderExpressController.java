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
import com.guoan.service.guoan.OrderExpressService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;

/**
 * 快递订单
 * @author gaochao
 *
 */
@Controller
@RequestMapping("/order/express")
public class OrderExpressController extends BaseController<OrderExpressController> {

	private static final long serialVersionUID = -6872466958933268672L;

	@Autowired
	OrderExpressService expressService;

	/**
	 * 取得订单列表
	 * 
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
	@ResponseBody
	public void getOrderList(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.expressService.getOrderList(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 取得某个订单
	 * 
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getOrder", method = RequestMethod.POST)
	@ResponseBody
	public void getOrder(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.expressService.getOrder(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
}
