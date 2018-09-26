package com.ihere.voyage.init.util;

import com.ihere.voyage.annotation.MyAnno;
import com.ihere.voyage.custom.MyException;
import com.ihere.voyage.entity.Mouse;
import com.ihere.voyage.util.EntityUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author fengshibo
 * @create 2018-07-04 14:55
 * @desc ${DESCRIPTION}
 **/

public class IntactCheckUtil {

    public static boolean check(Object obj) {
        List<Field> list = Arrays.asList(obj.getClass().getDeclaredFields());
        for (int i = 0; i < list.size(); i++) {
            Field field = list.get(i);
            //是否使用MyAnno注解
            if (field.isAnnotationPresent(MyAnno.class)) {
                //获得所有的注解
                for (Annotation anno : field.getDeclaredAnnotations()) {
                    //找到自己的注解
                    if (anno.annotationType().equals(MyAnno.class)) {
                        EntityUtil entityUtil = new EntityUtil();
                        //注解的值
                        if (!((MyAnno) anno).isCanNull()) {
                            if (entityUtil.getFieldValueByName(field.getName(), obj) == null) {
                                throw new MyException("参数错误", "类：" + Mouse.class + "的属性：" + field.getName() + "不能为空，实际的值:" + entityUtil.getFieldValueByName(field.getName(), obj) + ",注解："+field.getDeclaredAnnotations());
                            }
                        }
                        if (!((MyAnno) anno).isCanEmpty()) {
                            if (entityUtil.getFieldValueByName(field.getName(), obj).equals("")) {
                                throw new MyException("参数错误", "类：" + Mouse.class + "的属性：" + field.getName() + "不能为空字符串，实际的值:" + entityUtil.getFieldValueByName(field.getName(), obj) + ",注解："+field.getDeclaredAnnotations());
                            }
                        }
                        if (!((MyAnno) anno).isCanZero()) {
                            if (entityUtil.getFieldValueByName(field.getName(), obj).toString().equals("0") || entityUtil.getFieldValueByName(field.getName(), obj).toString().equals("0.0")) {
                                throw new MyException("参数错误", "类：" + Mouse.class + "的属性：" + field.getName() + "不能为空字符0，实际的值:" + entityUtil.getFieldValueByName(field.getName(), obj) + ",注解："+field.getDeclaredAnnotations());
                            }
                        }
                    }
                }
            }

        }
        return true;
    }
}