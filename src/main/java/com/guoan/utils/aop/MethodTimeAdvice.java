package com.guoan.utils.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* @Title: MethodTimeAdvice.java
* @Package com.guoan.utils.aop
* @Description: 记录方法的执行时间
* @author 赵彤 
* @date 2014年8月20日 下午2:44:01
* @version V1.0
 */
public class MethodTimeAdvice implements MethodInterceptor {
	protected final Log log = LogFactory.getLog(MethodTimeAdvice.class);
	/**
	 * 拦截要执行的目标方法
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		// 用 commons-lang 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		Object result = invocation.proceed();
		clock.stop(); // 计时结束

		// 方法参数类型，转换成简单类型
		Class[] params = invocation.getMethod().getParameterTypes();
		String[] simpleParams = new String[params.length];
		for (int i = 0; i < params.length; i++) {
			simpleParams[i] = params[i].getSimpleName();
		}
		log.debug("------------------------------------------------------------------------------------------------------------------------");
		log.debug("接口-> [ "+invocation.getThis().getClass().getName() + "." + invocation.getMethod().getName() +"(" + StringUtils.join(simpleParams, ",") + ")] "+ "  用时: " + clock.getTime() + " ms ");
		log.debug("------------------------------------------------------------------------------------------------------------------------");
		return result;
	}
}