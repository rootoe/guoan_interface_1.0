package com.guoan.controller.work;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.service.work.WorkUserService;
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
 * 服务人员用户模块
 * Created by 赵彤 on 2015/5/18.
 */
@Controller
@RequestMapping("/work/user")
public class WorkUserController extends BaseController<WorkUserController> {
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
     * 登陆
     */
    @SuppressWarnings("static-access")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletRequest request,HttpServletResponse response) {
        JsonUtil jsonUtils = new JsonUtil();

        Result result = new Result();
        String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
//		jsonString = "{\"password\":\"96e79218965eb72c92a549dd5a330112\",\"telephone\":\"15011276277\"}";
        if(StringUtils.isEmpty(jsonString)){
            result.setCode(CodeEnum.paramErr.getValue());
            result.setMessage(CodeEnum.paramErr.getDescription());
            jsonUtils.renderJson(result, response);
            return;
        } else {
            result = this.workUserService.login(jsonString);
            jsonUtils.renderJson(result, response);
        }
    }

}
