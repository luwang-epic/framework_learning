package com.framework.learning.guice.demo.annotation;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * TestAnnotation注解, 用于guice区分不同的实例
 *
 * @author wanglu
 * @date 2020/06/06
 */
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface TestAnnotation {
}
