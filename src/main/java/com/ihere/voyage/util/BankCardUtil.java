package com.ihere.voyage.util;

/**
 * @模块名 : com.chinahrs.social.web.util
 * @文件名 : BankCardUtil.java
 * @文件实现功能 : 银行卡工具类
 * @作者 : yangpeng
 * @版本 : 1.0
 * @备注 : 无
 * @创建时间 : 2016-1-15 下午2:20:13
 * @修改时间 : 2016-1-15 下午2:20:13
 * @修改版本 :
 * @修改人 :
 * @修改内容 :
 */
public class BankCardUtil {
	/**
	 * @函数名称 : checkBankCard
	 * @函数功能描述 : 检查银行卡号是否正确
	 * @参数说明 : bankCard-银行卡号
	 * @函数返回值 : true-正确；false-错误
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	public static boolean checkBankCard(String bankCard) {
		char checkCode = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
		if (checkCode == 'N') {
			return false;
		}
		return bankCard.charAt(bankCard.length() - 1) == checkCode;
	}

	/**
	 * @函数名称 : getBankCardCheckCode
	 * @函数功能描述 : 获取银行卡号的校验位
	 * @参数说明 : noCheckCodeBankCard-不包含校验位的银行卡号
	 * @函数返回值 : 银行卡号的校验位
	 * @其它说明 : 无
	 * @抛出异常 : 无
	 */
	private static char getBankCardCheckCode(String noCheckCodeBankCard) {
		if (StringUtil.isEmpty(noCheckCodeBankCard) || !noCheckCodeBankCard.matches("\\d+")) {
			return 'N';
		}
		char[] chs = noCheckCodeBankCard.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}
}