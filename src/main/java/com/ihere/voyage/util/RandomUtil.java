package com.ihere.voyage.util;

import java.util.Random;

/**
 * @模块名 : com.chinahrs.social.web.util
 * @文件名 : RandomUtil.java
 * @文件实现功能 : 随机字符串工具类
 * @作者 : yangpeng
 * @版本 : 1.0
 * @备注 : 无
 * @创建时间 : 2015-2-28 下午4:01:21
 * @修改时间 : 2015-2-28 下午4:01:21
 * @修改版本 :
 * @修改人 :
 * @修改内容 :
 */
public class RandomUtil {
	/** 数字标识：0 */
	public static String SMS_VALIDATE_NUMBER = "0";

	/** 大写字母标识：1 */
	public static String SMS_VALIDATE_BIG_LETTER = "1";

	/** 小写字母标识：2 */
	public static String SMS_VALIDATE_SMALL_LETTER = "2";

	/** 全部字母标识：3 */
	public static String SMS_VALIDATE_ALL_LETTER = "3";

	/** 数字与大写字母混合标识：4 */
	public static String SMS_VALIDATE_NUM_BIG = "4";

	/** 数字与小写字母混合标识：5 */
	public static String SMS_VALIDATE_NUM_SMALL = "5";

	/** 数字与全部字母混合标识：6 */
	public static String SMS_VALIDATE_NUM_ALL = "6";

	/** 数字字符串：0-9 */
	private static String STR_NUMBER = "0123456789";

	/** 大写字母字符串：A-Z */
	private static String STR_BIG_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/** 小写字母字符串：a-z */
	private static String STR_SMALL_LETTER = "abcdefghijklmnopqrstuvwxyz";

	/** 所有字母字符串：a-zA-Z */
	private static String STR_ALL_LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 定义唯一实例
	 */
	private static RandomUtil instance = null;

	/**
	 * 获取唯一实例
	 * 
	 * @return
	 */
	public static synchronized RandomUtil getInstance() {
		if (instance == null) {
			instance = new RandomUtil();
		}
		return instance;
	}

	/**
	 * 生成任意指定长度字符串
	 * 
	 * @param len
	 *            字符串长度
	 * @param type
	 *            数字或字母标识
	 * @return
	 */
	public String getRandomStr(int len, String type) {
		String str = "";
		if (type.equals(SMS_VALIDATE_NUMBER)) {
			str = STR_NUMBER;
		} else if (type.equals(SMS_VALIDATE_BIG_LETTER)) {
			str = STR_BIG_LETTER;
		} else if (type.equals(SMS_VALIDATE_SMALL_LETTER)) {
			str = STR_SMALL_LETTER;
		} else if (type.equals(SMS_VALIDATE_ALL_LETTER)) {
			str = STR_ALL_LETTER;
		} else if (type.equals(SMS_VALIDATE_NUM_BIG)) {
			str = STR_NUMBER + STR_BIG_LETTER;
		} else if (type.equals(SMS_VALIDATE_NUM_SMALL)) {
			str = STR_NUMBER + STR_SMALL_LETTER;
		} else if (type.equals(SMS_VALIDATE_NUM_ALL)) {
			str = STR_NUMBER + STR_ALL_LETTER;
		} else {
			str = STR_NUMBER;
		}
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		int range = str.length();
		for (int i = 0; i < len; i++) {
			buffer.append(str.charAt(random.nextInt(range)));
		}
		return buffer.toString();
	}

	/**
	 * 生成默认长度随机字符串（6位长度数字字符串）
	 * 
	 * @return
	 */
	public String getDefaultRandomStr() {
		return getRandomStr(6, SMS_VALIDATE_NUMBER);
	}

	/**
	 * @函数名称 : getRandom
	 * @函数功能描述 : 获取指定范围内的整数
	 * @参数说明 : start-开始的数字；end-结束的数字
	 * @函数返回值 : 指定范围内的整数
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	public int getRandom(int start, int end) {
		if (start > end || start < 0 || end < 0) {
			return -1;
		}
		return (int) (Math.random() * (end - start + 1)) + start;
	}

	/**
	 * @函数名称 : getRandomDou
	 * @函数功能描述 : 获取指定范围内指定小数位数的小数
	 * @参数说明 : start-开始的数字；end-结束的数字；length-小数位数
	 * @函数返回值 : 指定范围内指定小数位数的小数
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	public String getRandomDou(int start, int end, int length) {
		if (start > end || start < 0 || end < 0 || length < 0) {
			return "0";
		}
		Random random = new Random();
		String value = String.valueOf(random.nextDouble() * (end - start) + start);
		String[] values = value.split("\\.");
		return values[0] + "." + (length == 0 ? "0" : values[1].substring(0, length));
	}
}