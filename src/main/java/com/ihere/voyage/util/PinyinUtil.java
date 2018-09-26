package com.ihere.voyage.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PinyinUtil {

	private static final HanyuPinyinOutputFormat DEFAULT_FORMAT = new HanyuPinyinOutputFormat();
	static {
		DEFAULT_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		DEFAULT_FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);
	}

	private static final Map<Character, String[]> pinYinCache = new HashMap<>(256);

	private static final Map<Character, String[]> firstCharCache = new HashMap<>(256);

	private PinyinUtil() {
	}

	/**
	 * 获得字符串的所有的拼音组合和首字母缩写组合和源串合并的数组并转小写，例如： "河北省Abc123" -> ["hebeishengabc123", "hebeixingabc123", "hbsabc123", "hbxabc123", "河北省abc123"]
	 * 
	 * @param str
	 * @return if str is empty, return length of array is 0
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String[] toSearchStringArray(String str) throws BadHanyuPinyinOutputFormatCombination {
		if (StringUtil.isEmpty(str)) {
			return new String[0];
		}
		int length = str.length();
		String[][] longPinyinAssemble = new String[length][];
		String[][] shortPinyinAssemble = new String[length][];
		for (int i = 0; i < length; i++) {
			char ch = str.charAt(i);
			toHanyuPinyinStringArray(ch);
			String[] pinyins = pinYinCache.get(ch);
			String[] firstChars = firstCharCache.get(ch);
			longPinyinAssemble[i] = pinyins;
			shortPinyinAssemble[i] = firstChars;
		}
		String[] searchArray = ArrayUtil.concat(assembleArray(longPinyinAssemble), assembleArray(shortPinyinAssemble), new String[] { str });
		for (int i = 0; i < searchArray.length; i++) {
			searchArray[i] = searchArray[i].toLowerCase();
		}
		return ArrayUtil.distinct(searchArray);
	}

	private static void toHanyuPinyinStringArray(char ch) throws BadHanyuPinyinOutputFormatCombination {
		if (!pinYinCache.containsKey(ch)) {
			String[] pinyin = ArrayUtil.distinct(PinyinHelper.toHanyuPinyinStringArray(ch, DEFAULT_FORMAT));
			if (pinyin.length == 0) {
				pinyin = new String[] { String.valueOf(ch) };
			}
			pinYinCache.put(ch, pinyin);
			firstCharCache.put(ch, getFirstChar(pinyin));
		}
	}

	private static String[] getFirstChar(String[] src) {
		String[] target = new String[src.length];
		for (int i = 0; i < src.length; i++) {
			target[i] = String.valueOf(src[i].charAt(0));
		}
		return target;
	}

	private static String[] assembleArray(String[][] src) {
		String[] array = src[0];
		if (src.length == 1) {
			return array;
		}
		for (int i = 1; i < src.length; i++) {
			array = assembleArray(array, src[i]);
		}
		return array;
	}

	private static String[] assembleArray(String[] one, String[] two) {
		if (one.length == 0) {
			return Arrays.copyOf(two, two.length);
		}
		if (two.length == 0) {
			return Arrays.copyOf(one, one.length);
		}
		String[] newArray = new String[one.length * two.length];
		int index = 0;
		for (int i = 0; i < one.length; i++) {
			for (int j = 0; j < two.length; j++) {
				newArray[index] = one[i] + two[j];
				index++;
			}
		}
		return newArray;
	}

}
