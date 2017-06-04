package com.guoan.utils.httpservlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: HttpServletRequestReplacedFilter.java
 * @Package com.guoan.utils.httpservlet
 * @Description: request 拦截工具
 * @author 赵彤
 * @date 2015年5月13日 下午4:49:01
 * @version V1.0
 */
public class HttpServletRequestReplacedFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ServletRequest requestWrapper = null;
		if (request instanceof HttpServletRequest) {
			requestWrapper = new MAPIHttpServletRequestWrapper((HttpServletRequest) request);
		}
		if (requestWrapper == null) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(requestWrapper, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}


}
