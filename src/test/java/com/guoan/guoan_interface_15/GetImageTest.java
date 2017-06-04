package com.guoan.guoan_interface_15;

import com.aliyun.common.utils.HttpUtil;
import com.easemob.server.example.httpclient.utils.HTTPClientUtils;
import com.guoan.utils.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhaotongbeyond@qq.com on 2015/7/2.
 */
public class GetImageTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {

//        HttpRequest.readTxtFile("D:\\test\\test.txt");
        JSONObject jsonObject = new JSONObject();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("barcode", "6923450657638");
        jsonObject.put("sku", jsonObject1);
        jsonObject.put("bcid", "6");
        jsonObject.put("shopid", "6ca7c7873065e9d2393dd010a58b45c8");

//        System.out.println(jsonObject.toString());
//        String s = HttpRequest.sendGet("http://jms.jimaisong.com/jms.do?m=queryproduct1", jsonObject.toString());
//        System.out.println(s);

        HttpsUtil.post("http://jms.jimaisong.com/jms.do?m=queryproduct1", jsonObject.toString());

    }

}
