package com.guoan.utils.encode;

import java.util.UUID;

public class GeneratorUtil {

	/**
	 * 生成UUID
	 * @author ZP
	 * @create 2014年8月13日 下午2:36:28
	 * @return
	 */
	public static String getUUID(){
		UUID result = UUID.randomUUID();
		return result.toString().replace("-", "");
	}
}
