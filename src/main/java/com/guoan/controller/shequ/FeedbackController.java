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
import com.guoan.service.guoan.AppFeedbackService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;

/**
 * 反馈
 * @author gaochao
 *
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController<FeedbackController> {

	private static final long serialVersionUID = -2454126179679275844L;
	
	@Autowired
	AppFeedbackService feedbackService;

	/**
	 * 添加反馈
	 * 
	 * @author gaochao
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response) {

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.feedbackService.addFeedback(jsonString);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
}
