package com.miniw.kitchen.annotation;

import com.miniw.kitchen.renum.ElementTypeEnum;

import java.lang.annotation.*;

/**
 * 文档类型注解
 * @author linsongjin
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ElementTypeAnnotation {

    ElementTypeEnum elementType();

}
