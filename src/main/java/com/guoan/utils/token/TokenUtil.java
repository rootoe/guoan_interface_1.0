package com.guoan.utils.token;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class TokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String getTokenStringByUserId(String sourceString) {

		Date time = new Date();
		String token = "";
		try {
			byte[] b = (DigestUtils.md5Hex(sourceString)).getBytes("utf-8");
			token = DigestUtils.md5Hex(b);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
	}

}
