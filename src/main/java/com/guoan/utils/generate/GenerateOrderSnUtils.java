package com.guoan.utils.generate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.FactoryBean;
/**
 * @Title: GenerateOrderSnUtils.java
 * @Package com.guoan.utils.generate
 * @Description: 生成订单编号工具类
 * @author 赵彤 
 * @date 2015年5月6日 下午2:49:43
 * @version V1.0
 */
public class GenerateOrderSnUtils implements FactoryBean<String> {
	private static long counter = 0;

	public synchronized String getObject() throws Exception {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " + ";
		String sequ = new DecimalFormat("00000000").format(counter++);
		return date + sequ;
	}
	public Class<String> getObjectType() {
		return String.class;
	}
	public boolean isSingleton() {
		return false;
	}

	public static void reset() {
		GenerateOrderSnUtils.counter = 0;
	}
}
