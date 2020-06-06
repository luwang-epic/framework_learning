package com.framework.learning.guice.demo.provider;

import com.google.inject.Provider;

import javax.inject.Inject;

/**
 * 演示guice的 Provider<T> 使用
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class SessionManager {

    /**
     * Provider<T>是一个接口，只有一个get方法，用于提供实例T 对象
     *  用Provider封装对象，会使得对象有生命周期概念，当超过生命周期，Provider将会重新获取T对象
     */

    private final Provider<Long> sessionIdProvider;
    private final Long sessionId;
    private final Double configureSessionId;

    @Inject
    public SessionManager(Provider<Long> sessionIdProvider, Long sessionId, Double configureSessionId) {
        this.sessionIdProvider = sessionIdProvider;
        this.sessionId = sessionId;
        this.configureSessionId = configureSessionId;
    }

    public Long getSessionIdInProvider() {
        return sessionIdProvider.get();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Double getConfigureSessionId() {
        return configureSessionId;
    }
}
