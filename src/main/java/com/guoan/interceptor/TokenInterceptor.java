package com.guoan.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.entity.work.bo.WorkUserInfo;
import com.guoan.service.redis.RedisService;
import com.guoan.service.token.TokenGenerator;
import com.guoan.utils.JsonUtil;
import com.guoan.utils.StringUtils;
import com.guoan.utils.redis.RedisContants;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * @author 赵彤
 * @version V1.0
 * @Title: TokenInterceptor.java
 * @Package com.guoan.interceptor
 * @Description: token 拦截器
 * @date 2014年9月11日 下午2:16:39
 */
public class TokenInterceptor extends RedisService implements HandlerInterceptor {

    private Logger logger = Logger.getLogger(TokenInterceptor.class.getName());

    @Autowired
    TokenGenerator tokenGenerator;

    private static ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //*/
        Result result = new Result();
        logger.info("拦截器：" + this.getClass().getName() + " ，我主要看看来的这些人有没有带上他们的token。");
        try {
            String jsonString = new JsonUtil().getDataFromRequestWithStream(request);
            String token = JSONObject.fromObject(jsonString).getString("token");

            if (StringUtils.isNotEmpty(token)) {
                logger.info("拦截器：" + this.getClass().getName() + " ，用户所带的token值为： " + token);
                AppUserInfo appUserInfo = null;
                WorkUserInfo workUserInfo = null;

                if (token.contains(RedisContants.USER_APP)) {
                    appUserInfo = super.getRedisUserObject(RedisContants.USER_APP, token, appUserInfo.getClass());

                    if (StringUtils.isEmpty(appUserInfo.getUserId())) {
                        logger.info("拦截器：" + this.getClass().getName() + " ，token验证失败。");
                        result.setCode(CodeEnum.tokenErr.getValue());
                        result.setMessage(CodeEnum.tokenErr.getDescription());

                        response.setContentType("application/json;charset=UTF-8");
                        mapper.writeValue(response.getWriter(), result);
                        return false;
                    }

                } else if (token.contains(RedisContants.USER_WORK)) {
                    workUserInfo = super.getRedisUserObject(RedisContants.USER_APP, token, workUserInfo.getClass());

                    if (0 == workUserInfo.getId()) {
                        logger.info("拦截器：" + this.getClass().getName() + " ，token验证失败。");
                        result.setCode(CodeEnum.tokenErr.getValue());
                        result.setMessage(CodeEnum.tokenErr.getDescription());

                        response.setContentType("application/json;charset=UTF-8");
                        mapper.writeValue(response.getWriter(), result);
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            logger.info("拦截器：" + this.getClass().getName() + " ，token验证失败。");
            logger.info("失败原因：" + e.getMessage());
            result.setCode(CodeEnum.tokenErr.getValue());
            result.setMessage(CodeEnum.tokenErr.getDescription());

            response.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(response.getWriter(), result);
            return false;
        }
        //*/
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        logger.info("拦截器：token 验证完毕。");
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

    }
}