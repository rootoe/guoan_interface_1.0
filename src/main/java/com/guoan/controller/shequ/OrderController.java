package com.guoan.controller.shequ;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guoan.service.guoan.*;
import com.guoan.utils.OrderConst;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.service.guoan.impl.OrderService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;

/**
 * 订单
 * 赵彤
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController<OrderController> {

	private static final long serialVersionUID = 1574459499803505897L;

	@Autowired
	OrderCleanService cleanService;

	@Autowired
	OrderPayService payService;

	@Autowired
	OrderMaintainService maintainService;

	@Autowired
	OrderWashService washService;

	@Autowired
	OrderRehabService rehabService;

	@Autowired
	OrderExpressService expressService;

	@Autowired
	OrderShopService shopService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDrugService drugService;  // add drugService On 2015-09-15
	
	@Autowired
	OrderMassageService massageService;  //add massageService ON 2015-09-24

	/**
	 * 新增订单
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	@ResponseBody
	public void addOrder(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {

			JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
			 //{"orderAddressId":"c8aaf79823de401c8702903a9407cbcd","orderType":1,"serviceType":"massage","areaId":"308","couponId":"","orderRemark":"","appointmentBeginTime":"2015-09-25 09:30:00"}
			JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
           
			String type = paramsObject.getString("serviceType");

			if (StringUtils.isNotBlank(type)) {
				switch (type) {
					case OrderConst.SERVICE_TYPE_WASH:
						result = washService.addOrder(jsonString);
						break;
					case OrderConst.SERVICE_TYPE_EXPRESS:
						result = expressService.addOrder(jsonString);
						break;
					case OrderConst.SERVICE_TYPE_CLEAN:
						result = cleanService.addOrder(jsonString);
						break;
					case OrderConst.SERVICE_TYPE_MAINTAIN:
						result = maintainService.addOrder(jsonString);
						break;
					case OrderConst.SERVICE_TYPE_REHAB:
						result = rehabService.addOrder(jsonString);
						break;
					case OrderConst.SERVICE_TYPE_SHOP:
						result = shopService.addOrder(jsonString);
						break;
					case OrderConst.SERVICE_TYPE_PAY:
						result = payService.addOrder(jsonString);
						break;
					case OrderConst.SERVICE_TYPE_DRUG:  //add drug ON 2015-09-15
						result = drugService.addOrder(jsonString);
						break;
					case OrderConst.SERVICE_TYPE_MASSAGE://add massage ON 2015-09-24
						result = massageService.addOrder(jsonString);
						break;
					default:
						result.setCode(CodeEnum.paramErr.getValue());
						result.setMessage(CodeEnum.paramErr.getDescription());
				}
			}else {
				result.setCode(CodeEnum.paramErr.getValue());
				result.setMessage(CodeEnum.paramErr.getDescription());
			}
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

		result = orderService.cancelOrder(jsonString);

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 删除订单
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	@ResponseBody
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		result = orderService.deleteOrder(jsonString);

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 评价订单
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/commentOrder", method = RequestMethod.POST)
	@ResponseBody
	public void commentOrder(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		result = orderService.commentOrder(jsonString);

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 取得某个订单
	 * 
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
			JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
			//报错
			JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

			String type = paramsObject.getString("serviceType");

			if (StringUtils.isNotBlank(type)) {
				switch (type) {
				case OrderConst.SERVICE_TYPE_WASH:
					result = washService.getOrder(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_EXPRESS:
					result = expressService.getOrder(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_CLEAN:
					result = cleanService.getOrder(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_MAINTAIN:
					result = maintainService.getOrder(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_PAY:
					result = payService.getOrder(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_REHAB:
					result = rehabService.getOrder(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_SHOP:
					result = shopService.getOrder(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_DRUG:
					result = drugService.getOrder(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_MASSAGE: // add massage ON 2015-09-24
					result = massageService.getOrder(jsonString);
					break;
				default:
					result.setCode(CodeEnum.paramErr.getValue());
					result.setMessage(CodeEnum.paramErr.getDescription());
				}
			}
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}

	/**
	 * 取得订单列表
	 * 
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
			JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
			JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));

			String type = paramsObject.getString("serviceType");

			switch (type) {
				case OrderConst.SERVICE_TYPE_WASH:
					result = washService.getOrderList(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_EXPRESS: 
					result = expressService.getOrderList(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_CLEAN:
					result = cleanService.getOrderList(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_MAINTAIN:
					result = maintainService.getOrderList(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_PAY:
					result = payService.getOrderList(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_REHAB:
					result = rehabService.getOrderList(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_SHOP:
					result = shopService.getOrderList(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_DRUG:  // add drug ON 2015-09-24
					result = drugService.getOrderList(jsonString);
					break;
				case OrderConst.SERVICE_TYPE_MASSAGE: // add massage ON 2015-09-24
					result = massageService.getOrderList(jsonString);
					break;
				default:
					result.setCode(CodeEnum.ServiceTypeNotSupport.getValue());
					result.setMessage(CodeEnum.ServiceTypeNotSupport.getDescription());
			}
		}
		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
	public static void main(String[] args) {
		
		String uuid = UUID.randomUUID().toString().replace("-","");
		System.out.println(uuid);
	
	}
}
