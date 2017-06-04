package com.guoan.utils.json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @Title: JsonDateValueProcessor.java
 * @Package com.guoan.utils.json
 * @Description: 格式化时间
 * @author 赵彤
 * @date 2015年5月11日 下午2:52:19
 * @version V1.0
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	private String format = "yyyy-MM-dd HH:mm:ss";

	public JsonDateValueProcessor() {
		super();
	}

	public JsonDateValueProcessor(String format) {
		super();
		this.format = format;
	}

	@Override
	public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	@Override
	public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	private Object process(Object value) {
		if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}

}
