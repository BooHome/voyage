package com.ihere.voyage.util.file;


import java.io.File;
import java.math.BigDecimal;
import java.net.URISyntaxException;

/**
 * 文件工具类
 * 
 * @author liu_xiaosong
 */
public class FileUtil {

	private FileUtil() {
	}

	/**
	 * 格式化文件大小
	 * 
	 * @param length
	 * @return if length &lt; 1024 return **B, else if length &lt; 1024 * 1024 return **KB, else return **MB
	 */
	public static String formatFileLength(long length) {
		long k = 1024;
		long m = 1024 * 1024;
		String lengthStr = null;
		if (length < k) {
			lengthStr = length + "B";
		} else if (length < m) {
			if (length % k != 0) {
				lengthStr = BigDecimal.valueOf(length / (k * 1.0)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			} else {
				lengthStr = String.valueOf(length / k);
			}
			lengthStr = lengthStr + "KB";
		} else {
			if (length % m != 0) {
				lengthStr = BigDecimal.valueOf(length / (m * 1.0)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			} else {
				lengthStr = String.valueOf(length / m);
			}
			lengthStr = lengthStr + "MB";
		}
		return lengthStr;
	}

	/**
	 * 获取文件（暂时只支持从classpath中加载）
	 * 
	 * @param filename
	 * @return
	 */
	public static File getFile(String filename) {
		try {
			return new File(Thread.currentThread().getContextClassLoader().getResource(filename).toURI());
		} catch (URISyntaxException e) {
			throw new UnsupportedOperationException(e);
		}
	}

}
