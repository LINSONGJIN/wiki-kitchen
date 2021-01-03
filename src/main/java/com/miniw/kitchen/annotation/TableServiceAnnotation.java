package com.miniw.kitchen.annotation;

import com.miniw.kitchen.renum.TableServiceEnum;

import java.lang.annotation.*;

/**
 * dao 注解
 * 用于指定要处理的 dao
 * @author linsongjin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TableServiceAnnotation {

    TableServiceEnum value();
}
