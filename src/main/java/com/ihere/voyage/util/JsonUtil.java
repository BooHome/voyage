package com.ihere.voyage.util;

import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 基于Gson的JSON工具类
 * 
 * @author liu_xiaosong
 */
public class JsonUtil {

	/** {@code "yyyy-MM-dd HH:mm:ss"} */
	public static final String DEFAULT_DATE_PATTERN = DateUtil.DATETIME_PATTERN;

	private JsonUtil() {
	}

	/**
	 * 使用指定时间格式解析json
	 * 
	 * @param <T> the type of the desired object
	 * @param json the string from which the object is to be deserialized
	 * @param typeOfT The specific genericized type of src. You can obtain this type by using the {@link com.google.gson.reflect.TypeToken} class. For example, to get the type for
	 *            {@code Collection<Foo>}, you should use: <br>
	 *            <code>
	 *            Type typeOfT = new TypeToken&lt;Collection&lt;Foo&gt;&gt;() {}.getType();
	 *            </code>
	 * @param typeAdapters converts Java objects to and from JSON
	 * @param datePattern a date pattern, if the datePattern is blank, will use the {@link JsonUtil#DEFAULT_DATE_PATTERN}
	 * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
	 * @throws Exception if json parse exception
	 */
	public static <T> T fromJson(String json, Type typeOfT, Map<Type, TypeAdapter<?>> typeAdapters, String datePattern) throws Exception {
		try {
			GsonBuilder gb = new GsonBuilder() //
			        .setDateFormat(StringUtil.isBlank(datePattern) ? DEFAULT_DATE_PATTERN : datePattern);
			if (typeAdapters != null && !typeAdapters.isEmpty()) {
				for (Map.Entry<Type, TypeAdapter<?>> entry : typeAdapters.entrySet()) {
					gb.registerTypeAdapter(entry.getKey(), entry.getValue());
				}
			}
			return gb.create().fromJson(json, typeOfT);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 使用指定时间格式解析json
	 * 
	 * @param <T> the type of the desired object
	 * @param json the string from which the object is to be deserialized
	 * @param typeOfT The specific genericized type of src. You can obtain this type by using the {@link com.google.gson.reflect.TypeToken} class. For example, to get the type for
	 *            {@code Collection<Foo>}, you should use: <br>
	 *            <code>
	 *            Type typeOfT = new TypeToken&lt;Collection&lt;Foo&gt;&gt;() {}.getType();
	 *            </code>
	 * @param datePattern a date pattern, if the datePattern is blank, will use the {@link JsonUtil#DEFAULT_DATE_PATTERN}
	 * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
	 * @throws Exception if json parse exception
	 */
	public static <T> T fromJson(String json, Type typeOfT, String datePattern) throws Exception {
		return fromJson(json, typeOfT, null, datePattern);
	}

	/**
	 * 使用缺省时间格式{@code "yyyy-MM-dd HH:mm:ss"}解析json
	 * 
	 * @param <T> the type of the desired object
	 * @param json the string from which the object is to be deserialized
	 * @param typeOfT The specific genericized type of src. You can obtain this type by using the {@link com.google.gson.reflect.TypeToken} class. For example, to get the type for
	 *            {@code Collection<Foo>}, you should use: <br>
	 *            <code>
	 *            Type typeOfT = new TypeToken&lt;Collection&lt;Foo&gt;&gt;() {}.getType();
	 *            </code>
	 * @param typeAdapters converts Java objects to and from JSON
	 * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
	 * @throws Exception if json parse exception
	 */
	public static <T> T fromJson(String json, Type typeOfT, Map<Type, TypeAdapter<?>> typeAdapters) throws Exception {
		return fromJson(json, typeOfT, typeAdapters, null);
	}

	/**
	 * 使用缺省时间格式{@code "yyyy-MM-dd HH:mm:ss"}解析json
	 * 
	 * @param <T> the type of the desired object
	 * @param json the string from which the object is to be deserialized
	 * @param typeOfT The specific genericized type of src. You can obtain this type by using the {@link com.google.gson.reflect.TypeToken} class. For example, to get the type for
	 *            {@code Collection<Foo>}, you should use: <br>
	 *            <code>
	 *            Type typeOfT = new TypeToken&lt;Collection&lt;Foo&gt;&gt;() {}.getType();
	 *            </code>
	 * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
	 * @throws Exception if json parse exception
	 */
	public static <T> T fromJson(String json, Type typeOfT) throws Exception {
		return fromJson(json, typeOfT, null, null);
	}

	/**
	 * 使用指定时间格式输出json<br>
	 * 不使用html转义<br>
	 * null值也序列化
	 * 
	 * @param obj
	 * @param typeAdapters converts Java objects to and from JSON
	 * @param datePattern a date pattern, if the datePattern is blank, will use the {@link JsonUtil#DEFAULT_DATE_PATTERN}
	 * @return
	 */
	public static String toJson(Object obj, Map<Type, TypeAdapter<?>> typeAdapters, String datePattern) {
		return toJson(obj, typeAdapters, datePattern, true);
	}

	/**
	 * 使用指定时间格式输出json<br>
	 * 不使用html转义<br>
	 * 
	 * @param obj
	 * @param typeAdapters converts Java objects to and from JSON
	 * @param datePattern a date pattern, if the datePattern is blank, will use the {@link JsonUtil#DEFAULT_DATE_PATTERN}
	 * @param serializeNulls null值是否序列化
	 * @return
	 */
	public static String toJson(Object obj, Map<Type, TypeAdapter<?>> typeAdapters, String datePattern, boolean serializeNulls) {
		GsonBuilder gb = new GsonBuilder() //
		        .disableHtmlEscaping(); //
		if (serializeNulls) {
			gb.serializeNulls();
		}
		gb.setDateFormat(StringUtil.isBlank(datePattern) ? DEFAULT_DATE_PATTERN : datePattern) //
		        .setExclusionStrategies(new ExclusionStrategy());
		if (typeAdapters != null && !typeAdapters.isEmpty()) {
			for (Map.Entry<Type, TypeAdapter<?>> entry : typeAdapters.entrySet()) {
				gb.registerTypeAdapter(entry.getKey(), entry.getValue());
			}
		}
		return gb.create().toJson(obj);
	}

	/**
	 * 使用指定时间格式输出json<br>
	 * 不使用html转义<br>
	 * null值也序列化
	 * 
	 * @param obj
	 * @param datePattern a date pattern, if the datePattern is blank, will use the {@link JsonUtil#DEFAULT_DATE_PATTERN}
	 * @return
	 */
	public static String toJson(Object obj, String datePattern) {
		return toJson(obj, null, datePattern);
	}

	/**
	 * 使用缺省时间格式{@code "yyyy-MM-dd HH:mm:ss"}输出json<br>
	 * 不使用html转义<br>
	 * null值也序列化
	 * 
	 * @param obj
	 * @param typeAdapters converts Java objects to and from JSON
	 * @return
	 */
	public static String toJson(Object obj, Map<Type, TypeAdapter<?>> typeAdapters) {
		return toJson(obj, typeAdapters, null);
	}

	/**
	 * 使用缺省时间格式{@code "yyyy-MM-dd HH:mm:ss"}输出json<br>
	 * 不使用html转义<br>
	 * null值也序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return toJson(obj, null, null);
	}

	/**
	 * json序列化时排除的字段
	 * 
	 * @author liu_xiaosong
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Exclusion {
	}

	private static class ExclusionStrategy implements com.google.gson.ExclusionStrategy {

		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			return f.getAnnotation(Exclusion.class) != null;
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}
	}

}
