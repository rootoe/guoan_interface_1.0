package com.guoan.utils.sendurl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 模拟发送url请求
 * Created by zhaotongbeyond@qq.com on 2015/6/14.
 */
public class SendURL {

    private static final Logger logger = LoggerFactory.getLogger(SendURL.class);

    /**
     * 程序中访问http数据接口
     */
//    public static void send(String urlStr) {
//
//        /** 网络的url地址 */
//        URL url = null;
//
//        /** http连接 */
//        HttpURLConnection httpConn = null;
//
//        /**//** 输入流 */
//        BufferedReader in = null;
//
//
//
//        StringBuffer sb = new StringBuffer();
//        try {
//            url = new URL(urlStr);
//            url.openConnection();
//            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//            String str = null;
//            while ((str = in.readLine()) != null) {
//                sb.append(str);
//            }
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                logger.error(ex.getMessage(), ex);
//            }
//        }
//        String result =sb.toString();
//        System.out.println(result);
//        return result;
//    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static void send(String url) {
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            connection.getInputStream();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally { // 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
