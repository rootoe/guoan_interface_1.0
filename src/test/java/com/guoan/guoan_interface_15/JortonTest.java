package com.guoan.guoan_interface_15;

import com.guoan.entity.base.common.Result;
import com.guoan.utils.DateUtil;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.util.SystemOutLogger;
import org.junit.Test;

import com.guoan.utils.encode.MD5Util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class JortonTest {


	@Test
	public void tteetet(){

		Result result = new Result();


		result.setCode(123);
		result.setMessage("hahahahah");
//		result.setData("");
		result.setData(null);
		System.out.println(result);

	}


	@Test
	public void s() throws Exception{

//		long parse = Date.parse("2015/5/28 15:00:00");
//
//		Date beginDate = new Date(parse);
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//		Date endDate = DateUtils.addMinutes(beginDate, 30);
//
//		String beginDateString = dateFormat.format(beginDate);
//		System.out.println(beginDateString);
//		dateFormat = new SimpleDateFormat("HH:mm");
//		String endDateString = dateFormat.format(endDate);
//		System.out.println(endDateString);
//		System.out.println(beginDateString+"-"+endDateString);
//		System.out.println("\\".replace("&quot;", "\""));
		String encode = URLEncoder.encode("%");
		System.out.println(encode);
//
		String decode = URLDecoder.decode(encode, "utf-8");
		System.out.println(decode);

//		try {
//			FileUtils.deleteDirectory(new File(""));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		String a = "123";
//		System.out.println(a);
		a = a + "aaaa";
//		System.out.println(a);

//		String a = "[55a750646db24992b3110c81c0c8a603]";
//		System.out.println(a.replace("[", "").replace("]", ""));
		System.out.println(MD5Util.getStringMD5("111111")+" --------------");


		System.out.println(UUID.randomUUID().toString().replace("-", ""));




//		System.out.println(2/3.0);
		System.out.println("---- "+MD5Util.getStringMD5("96e79218965eb72c92a549dd5a330112"+"d8cc0aa6e7ca45cda7718784da019cbe"));
//		
//		System.out.println("---------------------------");
//		
//		System.out.println(MD5Util.getStringMD5("qqqqqq"));
//		System.out.println(MD5Util.getStringMD5("0b4e7a0e5fe84ad35fb5f95b9ceeac79"+"a563cddb3f2d4707aeb63a7fdbd3a85c"));
//		
//		// 13642422424
//		System.out.println("----   "+MD5Util.getStringMD5("02c75fb22c75b23dc963c7eb91a062cc"+"9dd00a184b5f4ff8a7c387e59d0ffbc3"));
//		
//		String a1 = "#.";
//		System.out.println(a1.matches(".*#.*( ||$).+"));
//		
//		System.out.println("null".equals("null"));
//		System.out.println("null" == "null");
		
//		Object obj = null;
//		System.out.println(obj.toString()+"     --");
		
		
//		MemcachedClient memCachedClient = MemcacheUtil.getMemCachedClient();
//		
//		System.out.println(memCachedClient.set("1", 0, "你妹"));
//		
//		System.out.println(memCachedClient.get("1"));
//		
//		memCachedClient.replace("1", 0, "骂谁那");
//		
//		System.out.println(memCachedClient.get("1"));
		
		
//		System.out.println("dadas @1213 #haha".matches("(^\\S+[^\\s])|([@|#][^@#\\s]{1,32})"));
//		
//		
//		URL U = new URL("https://api.weibo.com/2/short_url/shorten.json?source=1842587009&url_long=http://img01-nacdn.oss-cn-hangzhou.aliyuncs.com/avatar/0698396cff6a4d9ab51664e3a03e9968.png");
//		URLConnection connection = U.openConnection();
//		connection.connect();
//		String result = "";
//		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//		String line;
//		while ((line = in.readLine()) != null) {
//			result += line;
//		}
//		in.close();
//		System.out.println(result);
		
//		System.out.println(MD5Util.getStringMD5("aaaaaa"));
		
		
//		StringBuffer sb = new StringBuffer();
//		sb.append("");
//		FieldPosition fp = new FieldPosition(NumberFormat.INTEGER_FIELD);
////		String currecy = NumberFormat.getNumberInstance().format(1245600000);
//		StringBuffer currecy = NumberFormat.getNumberInstance().format(1245600000, sb, fp);
//		System.out.println(currecy);

//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=0, l=3;i<l;i++){
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("price", i);
//			map.put("aaa", "1233444dsads"+i);
//			list.add(map);
//		}
//
//		for (Map<String, Object> i : list){
//			System.out.println(i.toString());
//		}
//		java.text.DecimalFormat myformat=new java.text.DecimalFormat("#.00");
//		BigDecimal bigDecimal = new BigDecimal(3);
//		BigDecimal bigDecimal1 = formatComma2BigDecimal(3);
//
//
//		System.out.println(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//		System.out.println(bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//		System.out.println(myformat.format(bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));







		Double double1 = 0.0;
		Double double2 = 0.00;

		System.out.println(double1 == double2);
		System.out.println(double1.equals(0));
		System.out.println(double1.equals(double2));
		System.out.println(double1 - double2 == 0);







	}

	private BigDecimal formatComma2BigDecimal(Object obj) {
		String val = String.valueOf(obj);
		if (val == null)
			return new BigDecimal("0.00");

		val = val.replaceAll(",", "");
		if (!isNumber(val))
			return new BigDecimal("0.00");

		BigDecimal decimal = new BigDecimal(val);
		decimal = decimal.setScale(2, RoundingMode.HALF_UP);

		return decimal;

	}
	private String formatCommaAnd2Point(Object obj) {
		BigDecimal decimal = formatComma2BigDecimal(obj);

		DecimalFormat df = new DecimalFormat("#,###.00");
		String decimalStr = df.format(decimal).equals(".00")?"0.00":df.format(decimal);
		if(decimalStr.startsWith(".")){
			decimalStr = "0"+decimalStr;
		}
		return decimalStr;
	}
	private boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	private boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	private boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}
}
