package com.ihere.voyage.util;

import java.math.BigDecimal;

/**
 * 字符串转换工具类
 * 
 * @author liu_xiaosong
 */
public class StringConvertUtil {

	private static final int DEFAULT_INT_VALUE = 0;

	private static final long DEFAULT_LONG_VALUE = 0L;

	private static final boolean DEFAULT_BOOLEAN_VALUE = false;

	private StringConvertUtil() {
	}

	/**
	 * 字符串转换为整型
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static Integer toInteger(String str, Integer defaultValue) {
		if (StringUtil.isBlank(str)) {
			return defaultValue;
		}
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为整型
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return null
	 */
	public static Integer toInteger(String str) {
		return toInteger(str, null);
	}

	/**
	 * 字符串先trim再转换为整型
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static Integer trimToInteger(String str, Integer defaultValue) {
		return toInteger(StringUtil.trimToNull(str), defaultValue);
	}

	/**
	 * 字符串先trim再转换为整型
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return null
	 */
	public static Integer trimToInteger(String str) {
		return toInteger(StringUtil.trimToNull(str), null);
	}

	/**
	 * 字符串转换为整型
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static int toBaseInt(String str, int defaultValue) {
		if (StringUtil.isBlank(str)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为整型
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return 0
	 */
	public static int toBaseInt(String str) {
		return toBaseInt(str, DEFAULT_INT_VALUE);
	}

	/**
	 * 字符串先trim再转换为整型
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static int trimToBaseInt(String str, int defaultValue) {
		return toBaseInt(StringUtil.trimToNull(str), defaultValue);
	}

	/**
	 * 字符串先trim再转换为整型
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return 0
	 */
	public static int trimToBaseInt(String str) {
		return toBaseInt(StringUtil.trimToNull(str), DEFAULT_INT_VALUE);
	}

	/**
	 * 字符串转换为长整型
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static Long toLong(String str, Long defaultValue) {
		if (StringUtil.isBlank(str)) {
			return defaultValue;
		}
		try {
			return Long.valueOf(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为长整型
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return null
	 */
	public static Long toLong(String str) {
		return toLong(str, null);
	}

	/**
	 * 字符串先trim再转换为长整型
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static Long trimToLong(String str, Long defaultValue) {
		return toLong(StringUtil.trimToNull(str), defaultValue);
	}

	/**
	 * 字符串先trim再转换为长整型
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return null
	 */
	public static Long trimToLong(String str) {
		return toLong(StringUtil.trimToNull(str), null);
	}

	/**
	 * 字符串转换为长整型
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static long toBaseLong(String str, long defaultValue) {
		if (StringUtil.isBlank(str)) {
			return defaultValue;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为长整型
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return 0
	 */
	public static long toBaseLong(String str) {
		return toBaseLong(str, DEFAULT_LONG_VALUE);
	}

	/**
	 * 字符串先trim再转换为长整型
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static long trimToBaseLong(String str, long defaultValue) {
		return toBaseLong(StringUtil.trimToNull(str), defaultValue);
	}

	/**
	 * 字符串先trim再转换为长整型
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return 0
	 */
	public static long trimToBaseLong(String str) {
		return toBaseLong(StringUtil.trimToNull(str), DEFAULT_LONG_VALUE);
	}

	/**
	 * 字符串转换为BigDecimal
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static BigDecimal toBigDecimal(String str, BigDecimal defaultValue) {
		if (StringUtil.isBlank(str)) {
			return defaultValue;
		}
		try {
			return new BigDecimal(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 字符串转换为BigDecimal
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return null
	 */
	public static BigDecimal toBigDecimal(String str) {
		return toBigDecimal(str, null);
	}

	/**
	 * 字符串先trim再转换为BigDecimal
	 * 
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return if str is blank, or if parse exception, return defaultValue
	 */
	public static BigDecimal trimToBigDecimal(String str, BigDecimal defaultValue) {
		return toBigDecimal(StringUtil.trimToNull(str), defaultValue);
	}

	/**
	 * 字符串先trim再转换为BigDecimal
	 * 
	 * @param str 待转换的字符串
	 * @return if str is blank, or if parse exception, return null
	 */
	public static BigDecimal trimToBigDecimal(String str) {
		return toBigDecimal(StringUtil.trimToNull(str), null);
	}

	/**
	 * 字符串转换成boolean类型
	 * @param str 待转换的字符串
	 * @return 如果str为空或者转换失败，返回null
	 */
	public static Boolean toBoolean(String str){
		 return toBoolean(str,null);
	}

	/**
	 * 字符串转换成boolean类型
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return 如果str为空或者转换失败，返回缺省值
	 */
	public static Boolean toBoolean(String str,Boolean defaultValue){
		if (StringUtil.isBlank(str)) {
			return defaultValue;
		}
		if(str.equals("1") || str.equalsIgnoreCase("true")){
			return true;
		}else if(str.equals("0") || str.equalsIgnoreCase("false")){
			return false;
		}
		return defaultValue;
	}

	/**
	 * 字符串转换成boolean类型
	 * @param str 待转换的字符串
	 * @return 如果str为空，或者转换失败，返回false
	 */
	public static boolean toBaseBoolean(String str){
		return toBaseBoolean(str,DEFAULT_BOOLEAN_VALUE);
	}

	/**
	 * 字符串转换成boolean类型
	 * @param str 待转换的字符串
	 * @param defaultValue 缺省值
	 * @return 如果str为空，或者转换失败，返回缺省值
	 */
	public static boolean toBaseBoolean(String str,boolean defaultValue){
		if (StringUtil.isBlank(str)) {
			return defaultValue;
		}
		return str.equals("1") || str.equalsIgnoreCase("true");
	}
}
