package com.guoan.controller.shequ;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.OrderExpress;
import com.guoan.service.guoan.OrderShopService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;
import com.pingplusplus.model.Charge;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物订单
 * 赵彤
 */
@Controller
@RequestMapping("/order/shop")
public class OrderShopController extends BaseController<OrderShopController> {

	private static final long serialVersionUID = -6872466958933268672L;

	@Autowired
	private OrderShopService shopService;


	/**
	 * 付款回调函数
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	@ResponseBody
	public String callback(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Charge charge) {
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		return this.shopService.callback(jsonString);
	}

	/**
	 * 付款
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public void pay(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.shopService.pay(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 取消订单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	@ResponseBody
	public void cancelOrder(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.shopService.cancelOrder(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 删除订单
	 * 
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	@ResponseBody
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.shopService.deleteOrder(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 评价订单
	 * 
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/commentOrder", method = RequestMethod.POST)
	@ResponseBody
	public void commentOrder(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.shopService.commentOrder(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

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
			result = this.shopService.getOrderList(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 更新订单状态
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
	@ResponseBody
	public void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.shopService.updateOrderStatus(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

}
