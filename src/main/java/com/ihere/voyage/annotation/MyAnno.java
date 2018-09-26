package com.ihere.voyage.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fengshibo
 * @create 2018-07-04 14:53
 * @desc ${DESCRIPTION}
 **/
@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME )
public @interface MyAnno {
    /**
     * 是否能为null
     * @return
     */
    boolean isCanNull() default true;

    /**
     * 是否能为空字符串
     * @return
     */
    boolean isCanEmpty() default true;

    /**
     * 是否能为0
     * @return
     */
    boolean isCanZero() default true;
}
