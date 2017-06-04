package com.guoan.utils.order;

import java.util.Random;
/**
* @Title: GenerateOrderSN.java
* @Package com.guoan.utils
* @Description: 生成订单编号
* @author 赵彤
* @date 2014年4月29日 下午10:15:28
* @version V1.0
 */
public class GenerateOrderSN {


	public static void main(String[] args) {
		System.out.println(GenerateOrderSN.nextCode());
		System.out.println(GenerateOrderSN.nextCode());
		System.out.println(GenerateOrderSN.nextCode());
	}

	public static synchronized String nextCode() {
        return System.currentTimeMillis()+new Random().nextInt(100000)+"";
    }
}
