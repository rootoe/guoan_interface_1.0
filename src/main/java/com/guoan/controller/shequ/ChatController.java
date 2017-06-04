package com.guoan.controller.shequ;

import com.guoan.controller.base.BaseController;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.service.guoan.AppActivityService;
import com.guoan.service.guoan.ChatService;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;

/**
 * 聊天
 * @author 赵彤
 *
 */
@Controller
@RequestMapping("/chat")
public class ChatController extends BaseController<ChatController> {

	private static final long serialVersionUID = -2454126179679275844L;
	
	@Autowired
	ChatService chatService;

	/**
	 * 添加
	 * 
	 * @author 赵彤
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public void send(HttpServletRequest request, HttpServletResponse response) {

		String savePath = request.getSession().getServletContext().getRealPath("/") + "vedio_template"+ File.separator;// 文件保存目录路径

		System.out.println("语音文件保存地址 "+savePath);

		Result result = new Result();
		String jsonString = new JsonUtil().getDataFromRequestWithStream(request);

		if (StringUtils.isBlank(jsonString)) {

			result.setCode(CodeEnum.paramErr.getValue());
			result.setMessage(CodeEnum.paramErr.getDescription());

		} else {
			result = this.chatService.send(jsonString, savePath);
		}

		JsonUtil jsonUtils = new JsonUtil();
		jsonUtils.renderJson(result, response);
	}
	
}
