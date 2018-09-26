package com.ihere.voyage.constant;

import java.nio.charset.Charset;

/**
 * 常量
 * 
 * @author liu_xiaosong
 */
public class Constant {

	/** RSA加密明文的长度 */
	public static final int RSA_DATA_LENGTH = 128 - 11; // 11为padding的长度

	/** 布尔类型对应的整型值：0-否 */
	public static final int FALSE = 0;

	/** 布尔类型对应的整型值：1-是 */
	public static final int TRUE = 1;

	/** utf-8 */
	public static final String CHARSET_UTF8_NAME = "utf-8";

	/** utf-8 */
	public static final Charset CHARSET_UTF8 = Charset.forName(CHARSET_UTF8_NAME);
}
