package com.framework.learning.mysql.sharding.exception;

/**
 * 路由策略不匹配异常
 * @author wanglu
 * @date 2020/01/01
 */
public class LoadRoutingStrategyUnMatchException extends RuntimeException {

    public LoadRoutingStrategyUnMatchException() {

    }

    public LoadRoutingStrategyUnMatchException(String message) {
        super(message);
    }

    public LoadRoutingStrategyUnMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
