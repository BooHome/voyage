package com.ihere.voyage.annotation;

import org.apache.poi.ss.formula.functions.T;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: fengshibo
 * @date: 2018/9/26 15:13
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Paging {

    String value() default "";

}
