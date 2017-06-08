package com.zhidi.util;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

/**
 * Map转化为实体类
 * @author DELL
 *
 */
public class BeanUtils {

	public static void parse(Map<String, String> map, Object obj) {
		ConvertUtils.register(new Converter() {
			
			@Override
			public Object convert(Class type, Object value) {
				 
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                try {
                    return simpleDateFormat.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
			}
		}, Date.class);
		try {
			org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
			System.out.println(EncryptUtil.encryptMD5(111111+"aaa"));
	}
}
