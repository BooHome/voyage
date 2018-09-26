package com.ihere.voyage.util;


import com.ihere.voyage.constant.Constant;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 * 
 * @author liu_xiaosong
 */
public class StringUtil {

	private static final String EMPTY = "";
	
	public static final String STAR = "*";

	private static final String NULL = "null";

	private static final Charset DEFAULT_CHARSET = Constant.CHARSET_UTF8;

	private StringUtil() {
	}

	/**
	 * 验证字符串是否是empty
	 * 
	 * @param str 待验证的字符串
	 * @return str == null || str.isEmpty()
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * 验证字符串是否不是empty
	 * 
	 * @param str 待验证的字符串
	 * @return !isEmpty(str)
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 验证字符串是否是blank
	 * 
	 * @param str 待验证的字符串
	 * @return str == null || str.trim().isEmpty()
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	/**
	 * 验证字符串是否不是blank
	 * 
	 * @param str 待验证的字符串
	 * @return !isBlank(str)
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 验证字符串是否是blank或"null"
	 * 
	 * @param str 待验证的字符串
	 * @return isBlank(str) || "null".equalsIgnoreCase(str.trim())
	 */
	public static boolean isNull(String str) {
		return isBlank(str) || NULL.equalsIgnoreCase(str.trim());
	}

	/**
	 * 验证字符串是否不是blank或"null"
	 * 
	 * @param str 待验证的字符串
	 * @return !isNull(str)
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 去除前后空格
	 * 
	 * @param str
	 * @return str == null ? null : str.trim()
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 去除前后空格，如果str是null，返回""
	 * 
	 * @param str
	 * @return str == null ? "" : str.trim()
	 */
	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}

	/**
	 * 去除前后空格，如果str是blank，返回null
	 * 
	 * @param str
	 * @return isBlank(str) ? null : str.trim()
	 */
	public static String trimToNull(String str) {
		return isBlank(str) ? null : str.trim();
	}

	/**
	 * 如果对象为空，返回缺省字符串；如果不为空，返回obj.toString()
	 * 
	 * @param obj
	 * @param defaultString
	 * @return
	 */
	public static String toString(Object obj, String defaultString) {
		return obj == null ? defaultString : obj.toString();
	}

	/**
	 * 如果对象为空，返回""；如果不为空，返回obj.toString()
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		return toString(obj, EMPTY);
	}

	/**
	 * 把字符串转成byte[]后按长度分割为数组
	 * 
	 * @param str
	 * @param byteLength
	 * @return if str is empty, or if byteLength < 1, return null;
	 */
	public static String[] splitByByteLength(String str, int byteLength) {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		if (byteLength < 1) {
			return null;
		}
		byte[] byteValue = str.getBytes(DEFAULT_CHARSET);
		int length = byteValue.length;
		List<String> result = new ArrayList<>();
		int index = 0;
		while (index < length) {
			int offset = index;
			int subLength = Math.min(length - index, byteLength);
			String newValue = "";
			newValue = new String(byteValue, offset, subLength, DEFAULT_CHARSET);
			// 这种情况是中文被截断了
			while (byteLength < newValue.getBytes(DEFAULT_CHARSET).length) {
				index--;
				subLength--;
				newValue = new String(byteValue, offset, subLength, DEFAULT_CHARSET);
			}
			result.add(newValue);
			index += byteLength;
		}
		return result.toArray(new String[0]);
	}

	/**
	 * 将字符串text中由openToken和closeToken组成的占位符依次替换为args数组中的值
	 * @param openToken 被替换占位符起始字符串
	 * @param closeToken 被替换占位符关闭字符串
	 * @param text 文本内容
	 * @param args 替换文本内容
	 * @return 替换后的字符串
	 */
	public static String parse(String openToken, String closeToken, String text, Object... args) {
		if (args == null || args.length <= 0) {
			return text;
		}
		int argsIndex = 0;

		if (text == null || text.isEmpty()) {
			return "";
		}
		char[] src = text.toCharArray();
		int offset = 0;
		// search open token
		int start = text.indexOf(openToken, offset);
		if (start == -1) {
			return text;
		}
		final StringBuilder builder = new StringBuilder();
		StringBuilder expression = null;
		while (start > -1) {
			if (start > 0 && src[start - 1] == '\\') {
				// this open token is escaped. remove the backslash and continue.
				builder.append(src, offset, start - offset - 1).append(openToken);
				offset = start + openToken.length();
			} else {
				// found open token. let's search close token.
				if (expression == null) {
					expression = new StringBuilder();
				} else {
					expression.setLength(0);
				}
				builder.append(src, offset, start - offset);
				offset = start + openToken.length();
				int end = text.indexOf(closeToken, offset);
				while (end > -1) {
					if (end > offset && src[end - 1] == '\\') {
						// this close token is escaped. remove the backslash and continue.
						expression.append(src, offset, end - offset - 1).append(closeToken);
						offset = end + closeToken.length();
						end = text.indexOf(closeToken, offset);
					} else {
						expression.append(src, offset, end - offset);
						offset = end + closeToken.length();
						break;
					}
				}
				if (end == -1) {
					// close token was not found.
					builder.append(src, start, src.length - start);
					offset = src.length;
				} else {
					String value = (argsIndex <= args.length - 1) ?
							(args[argsIndex] == null ? "" : args[argsIndex].toString()) : expression.toString();
					builder.append(value);
					offset = end + closeToken.length();
					argsIndex++;
				}
			}
			start = text.indexOf(openToken, offset);
		}
		if (offset < src.length) {
			builder.append(src, offset, src.length - offset);
		}
		return builder.toString();
	}

	/**
	 * ${}占位符替换
	 * @param text 文本内容
	 * @param args 参数
	 * @return 替换后的字符串
	 */
	public static String parseDollarSign(String text, Object... args) {
		return parse("${", "}", text, args);
	}

	/**
	 * #{}占位符替换
	 * @param text 文本内容
	 * @param args 参数
	 * @return 替换后的字符串
	 */
	public static String parsePoundSign(String text, Object... args) {
		return parse("#{", "}", text, args);
	}
	/**
	 * {...}占位符替换
	 * @param text 文本内容
	 * @param args 参数
	 * @return 替换后的字符串
	 */
	public static String parseBrance(String text, Object... args) {
		return parse("{", "}", text, args);
	}

}
