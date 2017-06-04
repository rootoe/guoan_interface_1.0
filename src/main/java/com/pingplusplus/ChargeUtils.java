package com.pingplusplus;

import com.pingplusplus.exception.*;
import com.pingplusplus.model.App;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Charge 对象相关示例
 *
 */
public class ChargeUtils {

	/**
	 * pingpp 管理平台对应的API key
	 */
	public static String apiKey = "sk_test_yL4yLSOOmnn5nfbXn1Kav5C4";
	/**
	 * pingpp 管理平台对应的应用I
	 */
	public static String appId = "app_iTOWHC0ifXX5vfbD";
	
    public static void main(String[] args) {
        Pingpp.apiKey = apiKey;
        ChargeUtils ce = new ChargeUtils();
        System.out.println("---------创建charge");
        Charge charge = ce.charge();
        System.out.println("---------查询charge");
        ce.retrieve(charge.getId());
        System.out.println("---------查询charge列表");
        ce.all();
    }

    /**
     * 创建Charge
     * @return
     */
    public Charge charge() {
        Charge charge = null;
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", 100);
        chargeMap.put("currency", "cny");
        chargeMap.put("subject", "Your Subject");
        chargeMap.put("body", "Your Body");
        chargeMap.put("order_no", "123456789");
        chargeMap.put("channel", "alipay");
        chargeMap.put("client_ip", "127.0.0.1");
        Map<String, String> app = new HashMap<String, String>();
        app.put("id",appId);
        chargeMap.put("app", app);
        try {
            //发起交易请求
            charge = Charge.create(chargeMap);
            System.out.println(charge);
        } catch (PingppException e) {
            e.printStackTrace();
        }
        return charge;
    }

    /**
     * 查询Charge
     * @param id
     */
    public void retrieve(String id) {
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            List<String> expande = new ArrayList<String>();
            expande.add("app");
            param.put("expand", expande);
            //Charge charge = Charge.retrieve(id);
            //Expand app
            Charge charge = Charge.retrieve(id, param);
            if (charge.getApp() instanceof App) {
                App app = (App) charge.getApp();
                // System.out.println("App Object ,appId = " + app.getId());
            } else {
                // System.out.println("String ,appId = " + charge.getApp());
            }

            System.out.println(charge);

        } catch (PingppException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页查询Charge
     * @return
     */
    public ChargeCollection all() {
        ChargeCollection chargeCollection = null;
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("limit", 3);

//增加此处设施，刻意获取app expande 
//        List<String> expande = new ArrayList<String>();
//        expande.add("app");
//        chargeParams.put("expand", expande);

        try {
            chargeCollection = Charge.all(chargeParams);
            System.out.println(chargeCollection);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return chargeCollection;
    }
}
