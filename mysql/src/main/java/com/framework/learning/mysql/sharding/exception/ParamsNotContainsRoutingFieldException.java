package com.framework.learning.mysql.sharding.exception;

/**
 * 路由策略不匹配异常
 * @author wanglu
 * @date 2020/01/01
 */
public class ParamsNotContainsRoutingFieldException extends RuntimeException {

    public ParamsNotContainsRoutingFieldException() {

    }

    public ParamsNotContainsRoutingFieldException(String message) {
        super(message);
    }

    public ParamsNotContainsRoutingFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
