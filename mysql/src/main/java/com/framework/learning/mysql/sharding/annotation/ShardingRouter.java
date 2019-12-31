package com.framework.learning.mysql.sharding.annotation;

import com.framework.learning.mysql.sharding.constant.ShardingConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 路由注解
 * @author wanglu
 * @date 2020/01/01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ShardingRouter {

    String routingField() default ShardingConstant.DEFAULT_ROUTING_FIELD;
}
