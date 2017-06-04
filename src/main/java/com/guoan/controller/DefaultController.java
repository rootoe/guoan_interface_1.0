package com.guoan.controller;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.service.guoan.OrderWashService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class DefaultController extends BaseController<DefaultController> {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public void auto(HttpServletRequest request, HttpServletResponse response) {

        Result result = Result.successResult();

        JsonUtil jsonUtils = new JsonUtil();
        jsonUtils.renderJson(result, response);
    }
}
