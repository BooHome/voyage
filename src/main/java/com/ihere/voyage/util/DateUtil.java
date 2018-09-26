package com.ihere.voyage.util;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.LocalTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author liu_xiaosong
 * @version 1.0 2017年3月15日 下午2:34:20
 */
public class DateUtil {

	/** {@code "yyyy-MM-dd HH:mm:ss.SSS"} */
	public static final String FULL_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	/** {@code "yyyy-MM-dd HH:mm:ss"} */
	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** {@code "yyyy-MM-dd HH:mm"} */
	public static final String DATETIME_WITHOUR_SECOND_PATTERN = "yyyy-MM-dd HH:mm";
	/** {@code "yyyy-MM-dd"} */
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	/** {@code "HH:mm:ss.SSS"} */
	public static final String FULL_TIME_PATTERN = "HH:mm:ss.SSS";

	/** {@code "HH:mm:ss"} */
	public static final String TIME_PATTERN = "HH:mm:ss";

	/** {@code "yyyy-MM"} */
	public static final String MONTH_PATTERN = "yyyy-MM";

	/** {@code "yyyy年MM月dd日"} */
	public static final String DATE_CH_PATTERN = "yyyy年MM月dd日";

	/** {@code "yyyyMMddHHmmss"} */
	public static final String DATE_NUMBER_1_PATTERN = "yyyyMMddHHmmss";

	/** {@code "yyyyMMdd"} */
	public static final String DATE_NUMBER_2_PATTERN = "yyyyMMdd";

	/** {@code "HHmmss"} */
	public static final String DATE_NUMBER_3_PATTERN = "HHmmss";

	/** {@code 23:59:59.999} */
	private static final LocalTime END_OF_DAY_LOCAL_TIME = new LocalTime(23, 59, 59, 999);

	private DateUtil() {
	}

	/**
	 * 按照指定format格式化时间
	 * 
	 * @param date 待格式化的时间
	 * @param format 指定的format
	 * @return if date is null, or if format is null, return null
	 */
	public static String formatDate(Date date, DateFormat format) {
		if (date == null || format == null) {
			return null;
		}
		return format.format(date);
	}

	/**
	 * 按照指定pattern格式化时间
	 * 
	 * @param date 待格式化的时间
	 * @param pattern 指定的pattern
	 * @return if date is null, or if pattern is null, return null
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null || pattern == null) {
			return null;
		}
		return formatDate(date, new SimpleDateFormat(pattern));
	}

	/**
	 * 按照{@code "yyyy-MM-dd HH:mm:ss"}格式化时间
	 * 
	 * @param date 待格式化的时间
	 * @return if date is null, return null
	 */
	public static String formatDateTime(Date date) {
		if (date == null) {
			return null;
		}
		return formatDate(date, DATETIME_PATTERN);
	}

	/**
	 * 按照{@code "yyyy-MM-dd HH:mm:ss.SSS"}格式化时间
	 * 
	 * @param date 待格式化的时间
	 * @return if date is null, return null
	 */
	public static String formatFullDateTime(Date date) {
		if (date == null) {
			return null;
		}
		return formatDate(date, FULL_DATETIME_PATTERN);
	}

	/**
	 * 按照{@code "yyyy-MM-dd"}格式化时间
	 * 
	 * @param date 待格式化的时间
	 * @return if date is null, return null
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		return formatDate(date, DATE_PATTERN);
	}

	/**
	 * 按照{@code "HH:mm:ss"}格式化时间
	 * 
	 * @param date 待格式化的时间
	 * @return if date is null, return null
	 */
	public static String formatTime(Date date) {
		if (date == null) {
			return null;
		}
		return formatDate(date, TIME_PATTERN);
	}

	/**
	 * 按照{@code "HH:mm:ss.SSS"}格式化时间
	 * 
	 * @param date 待格式化的时间
	 * @return if date is null, return null
	 */
	public static String formatFullTime(Date date) {
		if (date == null) {
			return null;
		}
		return formatDate(date, FULL_TIME_PATTERN);
	}

	/**
	 * 按照{@code "yyyy-MM"}格式化时间
	 * 
	 * @param date 待格式化的时间
	 * @return if date is null, return null
	 */
	public static String formatMonth(Date date) {
		if (date == null) {
			return null;
		}
		return formatDate(date, MONTH_PATTERN);
	}

	/**
	 * 按照指定format解析时间
	 * 
	 * @param source 待解析的时间字符串
	 * @param format 指定的format
	 * @return if source is empty, or if format is null, or if parse exception, return null
	 */
	public static Date parseDate(String source, DateFormat format) {
		if (source == null || source.isEmpty() || format == null) {
			return null;
		}
		try {
			return format.parse(source);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 按照指定pattern解析时间
	 * 
	 * @param source 待解析的时间字符串
	 * @param pattern 指定的pattern
	 * @return if source is empty, or if pattern is empty, or if parse exception, return null
	 */
	public static Date parseDate(String source, String pattern) {
		if (source == null || source.isEmpty() || pattern == null || pattern.isEmpty()) {
			return null;
		}
		return parseDate(source, new SimpleDateFormat(pattern));
	}

	/**
	 * 按照{@code "yyyy-MM-dd HH:mm:ss"}解析时间
	 * 
	 * @param source 待解析的时间字符串
	 * @return if source is empty, or parse exception, return null
	 */
	public static Date parseDateTime(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return parseDate(source, DATETIME_PATTERN);
	}

	/**
	 * 按照{@code "yyyy-MM-dd HH:mm:ss.SSS"}解析时间
	 * 
	 * @param source 待解析的时间字符串
	 * @return if source is empty, or parse exception, return null
	 */
	public static Date parseFullDateTime(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return parseDate(source, FULL_DATETIME_PATTERN);
	}

	/**
	 * 按照{@code "yyyy-MM-dd"}解析时间
	 * 
	 * @param source 待解析的时间字符串
	 * @return if source is empty, or parse exception, return null
	 */
	public static Date parseDate(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return parseDate(source, DATE_PATTERN);
	}

	/**
	 * 按照{@code "HH:mm:ss"}解析时间
	 * 
	 * @param source 待解析的时间字符串
	 * @return if source is empty, or parse exception, return null
	 */
	public static Date parseTime(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return parseDate(source, TIME_PATTERN);
	}

	/**
	 * 按照{@code "HH:mm:ss.SSS"}解析时间
	 * 
	 * @param source 待解析的时间字符串
	 * @return if source is empty, or parse exception, return null
	 */
	public static Date parseFullTime(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return parseDate(source, FULL_TIME_PATTERN);
	}

	/**
	 * 按照{@code "yyyy-MM"}解析时间
	 * 
	 * @param source 待解析的时间字符串
	 * @return if source is empty, or parse exception, return null
	 */
	public static Date parseMonth(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		return parseDate(source, MONTH_PATTERN);
	}

	/**
	 * 创建一个指定的时间
	 * 
	 * @param values [年, 月, 日, 时, 分, 秒, 毫秒]
	 * @return
	 */
	public static Date createDate(int... values) {
		if (values == null || values.length == 0) {
			return null;
		}
		int[] newValues = Arrays.copyOf(values, 7);
		if (values.length < 2) {
			newValues[1] = 1;
		}
		if (values.length < 3) {
			newValues[2] = 1;
		}
		DateTime dateTime = new DateTime(newValues[0], newValues[1], newValues[2], newValues[3], newValues[4], newValues[5], newValues[6]);
		return dateTime.toDate();
	}

	/**
	 * 修改时间
	 * 
	 * @param date 时间
	 * @param unit 时间单位
	 * @param value 修改的值
	 * @return
	 */
	public static Date changeDate(Date date, DateUnit unit, int value) {
		if (date == null) {
			return null;
		}
		if (unit == null || value == 0) {
			return new Date(date.getTime());
		}
		DateTime dateTime = new DateTime(date);
		return unit.changeDateTime(dateTime, value).toDate();
	}

	/**
	 * 转换时间精度
	 * 
	 * @param date 时间
	 * @param scale 时间精度
	 * @return
	 */
	public static Date transformDate(Date date, DateScale scale) {
		if (date == null) {
			return null;
		}
		if (scale == null) {
			return new Date(date.getTime());
		}
		DateTime dateTime = new DateTime(date);
		return scale.transformDate(dateTime).toDate();
	}

	/**
	 * 修改时间并转换时间精度
	 * 
	 * @param date 时间
	 * @param unit 时间单位
	 * @param value 修改的值
	 * @param scale 时间精度
	 * @return
	 */
	public static Date changeAndTransformDate(Date date, DateUnit unit, int value, DateScale scale) {
		return transformDate(changeDate(date, unit, value), scale);
	}

	/**
	 * 修改时间为一天的开始
	 * 
	 * @param date
	 * @return
	 */
	public static Date changeTimeToStartOfDay(Date date) {
		if (date == null) {
			return null;
		}
		return new DateTime(date).withTimeAtStartOfDay().toDate();
	}

	/**
	 * 修改时间为一天的结束
	 * 
	 * @param date
	 * @return
	 */
	public static Date changeTimeToEndOfDay(Date date) {
		if (date == null) {
			return null;
		}
		return new DateTime(date).withTime(END_OF_DAY_LOCAL_TIME).toDate();
	}

	/**
	 * 修改时间为一个月的开始
	 * 
	 * @param date
	 * @return
	 */
	public static Date changeTimeToStartOfMonth(Date date) {
		if (date == null) {
			return null;
		}
		Property dayOfMonth = new DateTime(date).dayOfMonth();
		return dayOfMonth.setCopy(dayOfMonth.getMinimumValue()).withTimeAtStartOfDay().toDate();
	}

	/**
	 * 修改时间为一个月的结束
	 * 
	 * @param date
	 * @return
	 */
	public static Date changeTimeToEndOfMonth(Date date) {
		if (date == null) {
			return null;
		}
		Property dayOfMonth = new DateTime(date).dayOfMonth();
		return dayOfMonth.setCopy(dayOfMonth.getMaximumValue()).withTime(END_OF_DAY_LOCAL_TIME).toDate();
	}

	/**
	 * 判断两个时间段是否有交集
	 * 
	 * @return true - 两个时间段有交集, false - 两个时间段没有交集
	 */
	public static boolean hasIntersection(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
		boolean flag = ((startDate1.getTime() >= startDate2.getTime()) && startDate1.getTime() < endDate2.getTime()) || ((startDate1.getTime() > startDate2.getTime()) && startDate1.getTime() <= endDate2.getTime()) || ((startDate2.getTime() >= startDate1.getTime()) && startDate2.getTime() < endDate1.getTime()) || ((startDate2.getTime() > startDate1.getTime()) && startDate2.getTime() <= endDate1.getTime());
		return flag;
	}

	/**
	 * 计算两个日期的年份差值
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static int calcDiffYear(Date left, Date right) {
		Calendar leftCalendar = Calendar.getInstance();
		leftCalendar.setTime(left);
		Calendar rightCalendar = Calendar.getInstance();
		rightCalendar.setTime(right);
		int diffYear = leftCalendar.get(Calendar.YEAR) - rightCalendar.get(Calendar.YEAR);
		if (leftCalendar.get(Calendar.MONTH) > rightCalendar.get(Calendar.MONTH)) {
			diffYear--;
		} else if (leftCalendar.get(Calendar.MONTH) == rightCalendar.get(Calendar.MONTH)) {
			if (leftCalendar.get(Calendar.DATE) < rightCalendar.get(Calendar.DATE)) {
				diffYear--;
			}
		}
		return diffYear;
	}

	/**
	 * 时间单位
	 * 
	 * @author liu_xiaosong
	 */
	public enum DateUnit {

		YEAR {
			@Override
			DateTime changeDateTime(DateTime dateTime, int value) {
				return dateTime.plusYears(value);
			}
		},
		MONTH {
			@Override
			DateTime changeDateTime(DateTime dateTime, int value) {
				return dateTime.plusMonths(value);
			}
		},
		DAY {
			@Override
			DateTime changeDateTime(DateTime dateTime, int value) {
				return dateTime.plusDays(value);
			}
		},
		HOUR {
			@Override
			DateTime changeDateTime(DateTime dateTime, int value) {
				return dateTime.plusHours(value);
			}
		},
		MINUTE {
			@Override
			DateTime changeDateTime(DateTime dateTime, int value) {
				return dateTime.plusMinutes(value);
			}
		},
		SECOND {
			@Override
			DateTime changeDateTime(DateTime dateTime, int value) {
				return dateTime.plusSeconds(value);
			}
		},
		MILLISECOND {
			@Override
			DateTime changeDateTime(DateTime dateTime, int value) {
				return dateTime.plusMillis(value);
			}
		};

		abstract DateTime changeDateTime(DateTime dateTime, int value);

	}

	/**
	 * 时间精度
	 * 
	 * @author liu_xiaosong
	 */
	public enum DateScale {

		YEAR {
			@Override
			DateTime transformDate(DateTime dateTime) {
				return MONTH.transformDate(dateTime).withMonthOfYear(1);
			}
		},
		MONTH {
			@Override
			DateTime transformDate(DateTime dateTime) {
				return DAY.transformDate(dateTime).withDayOfMonth(1);
			}
		},
		DAY {
			@Override
			DateTime transformDate(DateTime dateTime) {
				return HOUR.transformDate(dateTime).withHourOfDay(0);
			}
		},
		HOUR {
			@Override
			DateTime transformDate(DateTime dateTime) {
				return MINUTE.transformDate(dateTime).withMinuteOfHour(0);
			}
		},
		MINUTE {
			@Override
			DateTime transformDate(DateTime dateTime) {
				return SECOND.transformDate(dateTime).withSecondOfMinute(0);
			}
		},
		SECOND {
			@Override
			DateTime transformDate(DateTime dateTime) {
				return dateTime.withMillisOfSecond(0);
			}
		};

		abstract DateTime transformDate(DateTime dateTime);

	}
}
