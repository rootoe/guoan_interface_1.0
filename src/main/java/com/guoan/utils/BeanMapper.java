package com.guoan.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanMapper {

	public static Map<String, String> beanToMap(final Object obj, boolean isShowNull, String[] excludeArray) {
		
		Map<String, String> map = new HashMap<String, String>();

		try {
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	        
	        for (PropertyDescriptor property : propertyDescriptors) {
	        	
	            String key = property.getName();  

				if ("serialVersionUID".equals(key)  ) {
					continue;
				}
				
				if (excludeArray != null) {
					if (Arrays.asList(excludeArray).contains(key)) {
						continue;
					}
				}
				
	            Method getter = property.getReadMethod();  
	            Object value = getter.invoke(obj);  

				if (value != null && !"".equals(value.toString())) {
					map.put(key, value.toString());
				} else {
					// 这里是否给一个空字段
					if (isShowNull) {
						map.put(key, "");
					}
				}
	       	}

        } catch (Exception e) {
			e.printStackTrace();
		}
        
		return map;
	}

	/**
	 * 默认显示空字段
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, String> beanToMap(final Object obj) {
		return beanToMap(obj, true, null);
	}

	/**
	 * 默认显示空字段
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, String> beanToMap(final Object obj, String[] excludeArray) {
		return beanToMap(obj, true, excludeArray);
	}

	/**
	 * map 转 Bean
	 * 
	 * @param map
	 * @param cls
	 * @return
	 */
	public static <T> Object mapToBean(Map map, Class<T> cls) {
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取出bean里的所有方法
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			// 取方法名
			String method = methods[i].getName();
			// 取出方法的类型
			Class[] cc = methods[i].getParameterTypes();
			if (cc.length != 1)
				continue;

			// 如果方法名没有以set开头的则退出本次for
			if (method.indexOf("set") < 0)
				continue;
			// 类型
			String type = cc[0].getSimpleName();

			try {
				Object value = method.substring(3, 4).toLowerCase() + method.substring(4);
//				Object value = method.substring(3);
				// 如果map里有该key
				if (map.containsKey(value) && map.get(value) != null) {
					// 调用其底层方法
					setValue(type, map.get(value), i, methods, obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/***************************************************************************
	 * 调用底层方法设置值
	 */
	private static void setValue(String type, Object value, int i, Method[] method, Object bean) {
		if (value != null && !value.equals("")) {
			try {
				if (type.equals("String")) {
					// 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
					method[i].invoke(bean, new Object[] { value });
				} else if (type.equals("int") || type.equals("Integer")) {
					method[i].invoke(bean, new Object[] { new Integer("" + value) });
				} else if (type.equals("short") || type.equals("Short")) {
					method[i].invoke(bean, new Object[] { new Short("" + value) });
				} else if (type.equals("double") || type.equals("Double")) {
					method[i].invoke(bean, new Object[] { new Double("" + value) });
				} else if (type.equals("float") || type.equals("Float")) {
					method[i].invoke(bean, new Object[] { new Float("" + value) });
				} else if (type.equals("long") || type.equals("Long")) {
					method[i].invoke(bean, new Object[] { new Long("" + value) });
				} else if (type.equals("boolean") || type.equals("Boolean")) {
					method[i].invoke(bean, new Object[] { Boolean.valueOf("" + value) });
				} else if (type.equals("BigDecimal")) {
					method[i].invoke(bean, new Object[] { new BigDecimal("" + value) });
				} else if (type.equals("Date")) {
					Date date = null;
					if (value.getClass().getName().equals("java.util.Date")) {
						date = (Date) value;
					} else {
						// SimpleDateFormat sfEnd = new
						// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						// SimpleDateFormat sfStart = new
						// SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH)
						// ;
						// String format = ((String) value).indexOf(":") > 0 ?
						// "yyyy-MM-dd hh:mm:ss" : "yyyy-MM-dd";
						// String format =
						SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.ENGLISH);
						// sf.applyPattern(format);
						date = sf.parse((String) (value));
					}
					if (date != null) {
						method[i].invoke(bean, new Object[] { date });
					}
				} else if (type.equals("byte[]")) {
					method[i].invoke(bean, new Object[] { new String(value + "").getBytes() });
				}
			} catch (Exception e) {
				System.out.println("将linkHashMap 或 HashTable 里的值填充到javabean时出错,请检查!");
				e.printStackTrace();
			}
		}
	}

}