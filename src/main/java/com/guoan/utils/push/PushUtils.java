package com.guoan.utils.push;

import com.tencent.xinge.XingeApp;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaotongbeyond@qq.com on 2015/6/29.
 */
public class PushUtils {

    private static final Logger logger = LoggerFactory.getLogger(PushUtils.class);

    //推送目标应用的 ID
    public final static long accessId = 2100126727;
    //目标应用的密钥
    public final static String secretKey = "ab036cf9df42af1cc284017c449131a2";



    public static synchronized JSONObject push(String title, String content, String token){
        boolean isContinue = true;
        int pushCount = 0;
        JSONObject jsonObject = new JSONObject();
        if(isContinue && pushCount <3){
            jsonObject = XingeApp.pushTokenAndroid(PushUtils.accessId, PushUtils.secretKey, title, content, token);
            if(null != jsonObject){
                // 0 是成功 其他的都是失败
                if(0 != jsonObject.getInt("ret_code")){
                    pushCount++;
                    logger.info("信鸽--推送失败消息返回值 :"+jsonObject);
                } else {
                    isContinue = false;
                    pushCount = 0;
                    logger.info("信鸽--推送成功消息返回值 :"+jsonObject);
                }
            }
        }
        return jsonObject;
    }






}
