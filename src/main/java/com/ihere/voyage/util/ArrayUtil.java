package com.ihere.voyage.util;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 数组操作类
 * 
 * @author liu_xiaosong
 */
public class ArrayUtil {

	private ArrayUtil() {
	}

	/**
	 * 合并两个数组并返回新的数组
	 * 
	 * @param first
	 * @param second
	 * @return 新的合并后数组
	 */
	public static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	/**
	 * 合并多个数组
	 * 
	 * @param src
	 * @param others
	 * @return
	 */
	@SafeVarargs
	public static <T> T[] concat(T[] src, T[]... others) {
		if (others == null || others.length == 0) {
			return Arrays.copyOf(src, src.length);
		}
		for (T[] other : others) {
			src = concat(src, other);
		}
		return src;
	}

	/**
	 * 数组去重
	 * 
	 * @param src
	 * @return
	 */
	public static <T> T[] distinct(T[] src) {
		Set<T> set = new LinkedHashSet<T>(Arrays.asList(src));
		return set.toArray(Arrays.copyOf(src, set.size()));
	}

	/**
	 * 字符串数组转换成字符串
	 * 
	 * @param array
	 * @return ["1", "2", "3"]
	 */
	public static String toArrayString(String[] array) {
		if (array == null) {
			return null;
		}
		if (array.length == 0) {
			return "[]";
		}
		StringBuilder b = new StringBuilder();
		b.append('[');
		int iMax = array.length - 1;
		for (int i = 0;; i++) {
			b.append('"');
			b.append(array[i]);
			b.append('"');
			if (i == iMax) {
				return b.append(']').toString();
			}
			b.append(", ");
		}
	}

	/**
	 * 给数组元素添加前缀和后缀
	 * 
	 * @param array 数组
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @return
	 */
	public static String[] arrayItemAppendPrefixAndSuffix(String[] array, String prefix, String suffix) {
		if (array == null) {
			return null;
		}
		if (prefix == null) {
			prefix = "";
		}
		if (suffix == null) {
			suffix = "";
		}
		String[] result = Arrays.copyOf(array, array.length);
		for (int i = 0; i < result.length; i++) {
			result[i] = prefix + result[i] + suffix;
		}
		return result;
	}

	/**
	 * 对String数组的每一项做trim，并且过滤掉为null或trim后为isEmpty的项
	 * 
	 * @param array
	 * @return
	 */
	public static String[] trimAndFilterEmptyItem(String[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		return Arrays.stream(array).map(str -> StringUtil.trimToNull(str)).filter(str -> str != null).toArray(String[]::new);
	}

}
