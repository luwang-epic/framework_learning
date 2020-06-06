package com.framework.learning.guice.demo.annotation;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * LoggedAnnotation注解, 用于guice aop记录日志
 *
 * @author wanglu
 * @date 2020/06/06
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggedAnnotation {
}
