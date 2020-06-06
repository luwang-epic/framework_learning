package com.learning.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解，用于注入HelloWorldConfiguration类中的配置
 * @author wanglu
 * @date 2020/03/21
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({HelloWorldConfiguration.class}) // 导入HelloWorldConfiguration
public @interface EnableHelloWorld {
}
