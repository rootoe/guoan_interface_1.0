package com.guoan.utils.httpservlet;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @Title: MAPIHttpServletRequestWrapper.java
 * @Package com.guoan.utils.httpservlet
 * @Description: request 拦截工具
 * @author 赵彤 
 * @date 2015年5月13日 下午6:10:29
 * @version V1.0
 */
public class MAPIHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private final byte[] body; // 报文

	public MAPIHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		InputStream is = request.getInputStream();

		byte[] bytes = new byte[1024 * 1024];

		int nRead = 1;
		int nTotalRead = 0;
		while (nRead > 0) {
			nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
			if (nRead > 0)
				nTotalRead = nTotalRead + nRead;
		}
		body = bytes;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);
		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}
}