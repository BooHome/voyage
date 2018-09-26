package com.ihere.voyage.util;

/**
 * 身份证号验证工具
 * 
 * @author liu_xiaosong
 */
public class IDCardUtil {

	private IDCardUtil() {
	}

	/**
	 * 验证身份证号格式
	 * 
	 * @param idCard
	 * @return
	 */
	public static boolean validate(String idCard) {
		if (StringUtil.isBlank(idCard)) {
			return false;
		}
		if (idCard.length() == 18) {
			return check18(idCard);
		} else if (idCard.length() == 15) {
			return check15(idCard);
		}
		return false;
	}

	/**
	 * 验证18位身份证号码
	 * 
	 * @param idCard
	 * @return
	 */
	private static boolean check18(String idCard) {
		final String id17 = idCard.substring(0, 17);
		final String id18 = idCard.toUpperCase().replaceAll("X", "0");
		if (StringConvertUtil.toBaseLong(id17) < Math.pow(10, 16) || ValidateUtil.isNotLong(id18)) {
			return false;
		}
		final String address = "11x22x35x44x53x12x23x36x45x54x13x31x37x46x61x14x32x41x50x62x15x33x42x51x63x21x34x43x52x64x65x71x81x82x91";
		if (!address.contains(idCard.substring(0, 2))) { // 省份验证
			return false;
		}
		String birthYear = idCard.substring(6, 10);
		String birthMonth = idCard.substring(10, 12);
		String birthDay = idCard.substring(12, 14);
		StringBuilder birthday = new StringBuilder(10);
		birthday.append(birthYear);
		birthday.append("-");
		birthday.append(birthMonth);
		birthday.append("-");
		birthday.append(birthDay);
		if (ValidateUtil.isNotDate(birthday.toString())) { // 生日验证
			return false;
		}
		final String[] arrVarifyCode = new String[] { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
		String[] wi = new String[] { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
		final char[] ai = id17.toCharArray();
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum += Integer.parseInt(wi[i]) * Integer.parseInt(String.valueOf(ai[i]));
		}
		int y = sum % arrVarifyCode.length;
		String idLast = idCard.substring(17);
		if (!arrVarifyCode[y].equals(idLast)) { // 校验码验证
			return false;
		}
		return true;
	}

	/**
	 * 验证15位身份证号码
	 * 
	 * @param idCard
	 * @return
	 */
	private static boolean check15(String idCard) {
		final String id15 = idCard;
		if (StringConvertUtil.toBaseLong(id15) < Math.pow(10, 14)) { // 数字验证
			return false;
		}
		final String address = "11x22x35x44x53x12x23x36x45x54x13x31x37x46x61x14x32x41x50x62x15x33x42x51x63x21x34x43x52x64x65x71x81x82x91";
		if (!address.contains(idCard.substring(0, 2))) { // 省份验证
			return false;
		}
		String birthYear = idCard.substring(6, 8);
		String birthMonth = idCard.substring(8, 10);
		String birthDay = idCard.substring(10, 12);
		StringBuilder birthday = new StringBuilder(10);
		birthday.append(19);
		birthday.append(birthYear);
		birthday.append("-");
		birthday.append(birthMonth);
		birthday.append("-");
		birthday.append(birthDay);
		if (ValidateUtil.isNotDate(birthday.toString())) { // 生日验证
			return false;
		}
		return true;
	}

}
