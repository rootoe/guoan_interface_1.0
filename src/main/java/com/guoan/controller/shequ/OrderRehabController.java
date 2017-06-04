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
import com.guoan.service.guoan.OrderRehabService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;

/**
 * 保洁订单
 *
 */
@Controller
@RequestMapping("/order/rehab")
public class OrderRehabController extends BaseController<OrderRehabController> {

}
