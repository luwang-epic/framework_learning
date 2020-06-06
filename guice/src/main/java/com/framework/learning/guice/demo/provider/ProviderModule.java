package com.framework.learning.guice.demo.provider;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * provider模块相关的依赖注入
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class ProviderModule extends AbstractModule {

    @Override
    protected void configure() {
        /*
         * SessionManager类对象不需要绑定的，会自动注入到guice中，使用@Inject即可获取
         */

        // 这种方式绑定的对象是固定的，即使通过Provider获取，两次获取的值也是一样的
        bind(Double.class).toInstance(Double.parseDouble(String.valueOf(System.currentTimeMillis())));
    }

    /**
     * 这是方式绑定的对象通过Provider获取有生命周期的概念，当超过生命周期后将重新获取，因此两次取值不一样
     */
    @Provides
    private Long generateSessionId() {
        return System.currentTimeMillis();
    }
}
