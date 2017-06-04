package com.guoan.controller.work;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.service.work.WorkUserService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务人员用户模块
 * Created by 赵彤 on 2015/5/18.
 */
@Controller
@RequestMapping("/work/service")
public class WorkServiceController extends BaseController<WorkServiceController> {
    private static final long serialVersionUID = 1L;

    @Autowired
    WorkUserService workUserService;

    /**
     * 空方法
     */
    @RequestMapping(value = {""})
    @ResponseBody
    public void nothing (HttpServletRequest request,HttpServletResponse response) {
        Result result = Result.successResult();

        JsonUtil jsonUtils = new JsonUtil();
        jsonUtils.renderJson(result, response);
    }

    /**
     * 已完成订单
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/order/complete", method = RequestMethod.POST)
    @ResponseBody
    public void orderComplete(HttpServletRequest request,HttpServletResponse response) {
        JsonUtil jsonUtils = new JsonUtil();

        Result result = new Result();
        String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
        if(StringUtils.isEmpty(jsonString)){
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
            jsonUtils.renderJson(result, response);
            return;
        } else {
            result = this.workUserService.orderComplete(jsonString);
            jsonUtils.renderJson(result, response);
        }
    }

    /**
     * 我的订单
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/order/my", method = RequestMethod.POST)
    @ResponseBody
    public void orderMy(HttpServletRequest request,HttpServletResponse response) {
        JsonUtil jsonUtils = new JsonUtil();

        Result result = new Result();
        String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
//		jsonString = "{\"token\":\"c4104681fe2c407b2a744bc315a97cf0\",\"page\":\"1\",\"serviceType\":\"wash\"}";
        if(StringUtils.isEmpty(jsonString)){
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
            jsonUtils.renderJson(result, response);
            return;
        } else {
            result = this.workUserService.orderMy(jsonString);
            jsonUtils.renderJson(result, response);
        }
    }

    /**
     * 订单列表
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/order/list", method = RequestMethod.POST)
    @ResponseBody
    public void orderList(HttpServletRequest request,HttpServletResponse response) {
        JsonUtil jsonUtils = new JsonUtil();

        Result result = new Result();
        String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
//		jsonString = "{\"token\":\"c4104681fe2c407b2a744bc315a97cf0\",\"page\":\"1\",\"serviceType\":\"wash\"}";
        if(StringUtils.isEmpty(jsonString)){
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
            jsonUtils.renderJson(result, response);
            return;
        } else {
            result = this.workUserService.orderList(jsonString);
            jsonUtils.renderJson(result, response);
        }

        long endTime = System.currentTimeMillis();
    }


    /**
     * 已完成的列表
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/order/complete/list", method = RequestMethod.POST)
    @ResponseBody
    public void orderCompleteList(HttpServletRequest request,HttpServletResponse response) {
        JsonUtil jsonUtils = new JsonUtil();

        Result result = new Result();
        String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
//		jsonString = "{\"token\":\"c4104681fe2c407b2a744bc315a97cf0\",\"page\":\"1\",\"serviceType\":\"wash\"}";
        if(StringUtils.isEmpty(jsonString)){
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
            jsonUtils.renderJson(result, response);
            return;
        } else {
            result = this.workUserService.orderCompleteList(jsonString);
            jsonUtils.renderJson(result, response);
        }

        long endTime = System.currentTimeMillis();
    }

    /**
     * 取得某个订单
     * @param request
     * @param response
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/order/info", method = RequestMethod.POST)
    @ResponseBody
    public void getOrderInfo(HttpServletRequest request, HttpServletResponse response) {

        JsonUtil jsonUtils = new JsonUtil();

        Result result = new Result();
        String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
//		jsonString = "{\"token\":\"c4104681fe2c407b2a744bc315a97cf0\",\"page\":\"1\",\"serviceType\":\"wash\"}";
        if(StringUtils.isEmpty(jsonString)){
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
            jsonUtils.renderJson(result, response);
            return;
        } else {
//            result = this.workUserService.orderInfo(jsonString);
            result = null;
            jsonUtils.renderJson(result, response);
        }
    }

    /**
     * 更新订单
     * 谁在什么时间更把订单状态改成了什么.
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/order/update", method = RequestMethod.POST)
    @ResponseBody
    public void orderUpdate(HttpServletRequest request,HttpServletResponse response) {
        JsonUtil jsonUtils = new JsonUtil();

        Result result = new Result();
        String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
        if(StringUtils.isEmpty(jsonString)){
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
            jsonUtils.renderJson(result, response);
            return;
        } else {
            result = this.workUserService.orderUpdate(jsonString);
            jsonUtils.renderJson(result, response);
        }

    }


}
