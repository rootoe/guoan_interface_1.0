package com.guoan.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
* @Title: Exceptions.java
* @Package com.guoan.common.utils
* @Description: 关于异常的工具类.
* @author 赵彤  
* @date 2013-10-10 下午05:38:51
* @version V1.0
 */
public class Exceptions {

    /**
     * 将CheckedException转换为UncheckedException.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     */
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
    	//直接引起的异常
    	for (Class<? extends Exception> causeClass : causeExceptionClasses) {
            if (causeClass.isInstance(ex)) {
                return true;
            }
        }
    	Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}