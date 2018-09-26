package com.ihere.voyage.util;

import java.util.regex.Pattern;

/**
 * 校验工具类
 * 
 * @author liu_xiaosong
 */
public class ValidateUtil {

	private ValidateUtil() {
	}

	private static final String DATE_REGEX = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-])(10|12|0[13578])([-])(3[01]|[12][0-9]|0[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-])(11|0[469])([-])(30|[12][0-9]|0[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-])(0?2)([-])(2[0-8]|1[0-9]|0[1-9])$)|(^([2468][048]00)([-])(0?2)([-])(29)$)|(^([3579][26]00)([-])(0?2)([-])(29)$)|(^([1][89][0][48])([-])(0?2)([-])(29)$)|(^([2-9][0-9][0][48])([-])(0?2)([-])(29)$)|(^([1][89][2468][048])([-])(0?2)([-])(29)$)|(^([2-9][0-9][2468][048])([-])(0?2)([-])(29)$)|(^([1][89][13579][26])([-])(0?2)([-])(29)$)|(^([2-9][0-9][13579][26])([-])(0?2)([-])(29)$))";

	private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);

	private static final String TIME_REGEX = "^(([0-1][0-9])|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.\\d{1,3})?$";

	private static final Pattern TIME_PATTERN = Pattern.compile(TIME_REGEX);

	private static final String DATE_TIME_REGEX = DATE_REGEX + " " + TIME_REGEX;

	private static final Pattern DATE_TIME_PATTERN = Pattern.compile(DATE_TIME_REGEX);

	private static final String MONEY_REGEX = "^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$";

	private static final Pattern MONEY_PATTERN = Pattern.compile(MONEY_REGEX);

	private static final String EMAIL_REGEX = "^([0-9a-zA-Z]+[_.0-9a-zA-Z-]+)@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2,3})$";

	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	private static final String MOBILE_REGEX = "^1[0-9]{10}$";

	private static final Pattern MOBILE_PATTERN = Pattern.compile(MOBILE_REGEX);

	private static final String TELPHONE_WITH_CONNECTOR_REGEX = "^((010|02\\d|0[3-9]\\d{2})-)?\\d{6,8}(-\\d{1,6})?$";

	private static final Pattern TELPHONE_WITH_CONNECTOR_PATTERN = Pattern.compile(TELPHONE_WITH_CONNECTOR_REGEX);

	private static final String TELPHONE_NONE_CONNECTOR_REGEX = "^(010|02\\d|0[3-9]\\d{2})?\\d{6,8}(\\d{1,6})?$";

	private static final Pattern TELPHONE_NONE_CONNECTOR_PATTERN = Pattern.compile(TELPHONE_NONE_CONNECTOR_REGEX);

	private static boolean matchers(Pattern pattern, String str) {
		if (StringUtil.isBlank(str)) {
			return false;
		}
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证字符串是否是日期（yyyy-MM-dd）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isDate(String str) {
		return matchers(DATE_PATTERN, str);
	}

	/**
	 * 验证字符串是否不是日期（yyyy-MM-dd）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotDate(String str) {
		return !isDate(str);
	}

	/**
	 * 验证字符串是否是时间（HH:mm:ss(.SSS)）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isTime(String str) {
		return matchers(TIME_PATTERN, str);
	}

	/**
	 * 验证字符串是否不是时间（HH:mm:ss(.SSS)）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotTime(String str) {
		return !isTime(str);
	}

	/**
	 * 验证字符串是否是日期时间（yyyy-MM-dd HH:mm:ss(.SSS)）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isDateTime(String str) {
		return matchers(DATE_TIME_PATTERN, str);
	}

	/**
	 * 验证字符串是否不是日期时间（yyyy-MM-dd HH:mm:ss(.SSS)）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotDateTime(String str) {
		return !isDateTime(str);
	}

	/**
	 * 验证字符串是否是金额
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isMoney(String str) {
		return matchers(MONEY_PATTERN, str);
	}

	/**
	 * 验证字符串是否不是金额
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotMoney(String str) {
		return !isMoney(str);
	}

	/**
	 * 验证字符串是否是邮箱
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isEmail(String str) {
		return matchers(EMAIL_PATTERN, str);
	}

	/**
	 * 验证字符串是否不是邮箱
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotEmail(String str) {
		return !isEmail(str);
	}

	/**
	 * 判断字符串是否是整型
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isInt(String str) {
		return StringConvertUtil.toInteger(str) != null;
	}

	/**
	 * 判断字符串是否不是整型
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotInt(String str) {
		return !isInt(str);
	}

	/**
	 * 判断字符串是否是长整型
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isLong(String str) {
		return StringConvertUtil.toLong(str) != null;
	}

	/**
	 * 判断字符串是否不是长整型
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotLong(String str) {
		return !isLong(str);
	}

	/**
	 * 判断字符串是否是身份证号
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isIDCard(String str) {
		if (StringUtil.isBlank(str)) {
			return false;
		}
		// 如果身份证号是10个大写的A开头，代表的是歪果仁
		if (str.startsWith("AAAAAAAAAA")) {
			return true;
		}
		return IDCardUtil.validate(str);
	}

	/**
	 * 判断字符串是否不是身份证号
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotIDCard(String str) {
		return !isIDCard(str);
	}

	/**
	 * 判断字符串是否是手机
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isMobile(String str) {
		return matchers(MOBILE_PATTERN, str);
	}

	/**
	 * 判断字符串是否不是手机
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotMobile(String str) {
		return !isMobile(str);
	}

	/**
	 * 判断字符串是否是固话（含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isTelphoneWithConnector(String str) {
		return matchers(TELPHONE_WITH_CONNECTOR_PATTERN, str);
	}

	/**
	 * 判断字符串是否不是固话（含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotTelphoneWithConnector(String str) {
		return !isTelphoneWithConnector(str);
	}

	/**
	 * 判断字符串是否是固话（不含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isTelphoneNoneConnector(String str) {
		return matchers(TELPHONE_NONE_CONNECTOR_PATTERN, str);
	}

	/**
	 * 判断字符串是否不是固话（不含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotTelphoneNoneConnector(String str) {
		return !isTelphoneNoneConnector(str);
	}

	/**
	 * 判断字符串是否是固话（不区分是否含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isTelphone(String str) {
		return isTelphoneWithConnector(str) || isTelphoneNoneConnector(str);
	}

	/**
	 * 判断字符串是否不是固话（不区分是否含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotTelphone(String str) {
		return !isTelphone(str);
	}

	/**
	 * 判断字符串是否是电话（手机或固话）（固话含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isPhoneWithConnector(String str) {
		return isMobile(str) || isTelphoneWithConnector(str);
	}

	/**
	 * 判断字符串是否不是电话（手机或固话）（固话含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotPhoneWithConnector(String str) {
		return !isPhoneWithConnector(str);
	}

	/**
	 * 判断字符串是否是电话（手机或固话）（固话不含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isPhoneNoneConnector(String str) {
		return isMobile(str) || isTelphoneNoneConnector(str);
	}

	/**
	 * 判断字符串是否不是电话（手机或固话）（固话不含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotPhoneNoneConnector(String str) {
		return !isPhoneNoneConnector(str);
	}

	/**
	 * 判断字符串是否是电话（手机或固话）（固话不区分是否含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isPhone(String str) {
		return isMobile(str) || isTelphone(str);
	}

	/**
	 * 判断字符串是否不是电话（手机或固话）（固话不区分是否含分割符[-]）
	 * 
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isNotPhone(String str) {
		return !isPhone(str);
	}

}
