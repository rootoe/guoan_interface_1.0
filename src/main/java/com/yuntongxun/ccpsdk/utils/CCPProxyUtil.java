package com.yuntongxun.ccpsdk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ytx.org.apache.http.HttpHost;
import ytx.org.apache.http.auth.AuthScope;
import ytx.org.apache.http.auth.UsernamePasswordCredentials;
import ytx.org.apache.http.conn.params.ConnRouteParams;
import ytx.org.apache.http.impl.client.DefaultHttpClient;

import com.guoan.utils.Const;

public class CCPProxyUtil {
	private static final Logger logger = LoggerFactory.getLogger(CCPProxyUtil.class);

	public static DefaultHttpClient getHttpClient(DefaultHttpClient httpClient) {
		// DefaultHttpClient httpClient = new DefaultHttpClient();
		String proxyHost = Const.PROXY_HOST;
		int proxyPort = Const.PROXY_PORT;
		String userName = Const.PROXY_USERNAME;
		String password = Const.PROXY_PASSWD;
		if (Const.YUNTONGXUN_PROXY_ANONYMOUS_SWITCH) {
			logger.info("使用云通讯代理用户名密码");
			httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort), new UsernamePasswordCredentials(userName, password));
		}
		HttpHost proxy = new HttpHost(proxyHost, proxyPort);
		httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		return httpClient;
	}
}
