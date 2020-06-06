package com.framework.learning.guice.demo.scope;

import com.framework.learning.guice.demo.annotation.LoggedAnnotation;
import com.google.common.base.Joiner;
import com.google.common.cache.Cache;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * scope模块的依赖注入
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class ScopeModule extends AbstractModule {

    @Override
    protected void configure() {
        // 默认不是单例的，不同的类中获取到的是不一样的对象， 或者说每次@Inject注解获取到的是不同的对象
        bind(new TypeLiteral<Cache<String, String>>(){}).annotatedWith(Names.named("multi")).to(GuiceDemoCache.class);

        // 类似于在GuiceDemoCache类上加@Singleton注解，也类似于下面的形式 singleton的对象需要保证线程安全
        bind(new TypeLiteral<Cache<String, String>>(){}).annotatedWith(Names.named("singleton"))
                .to(GuiceDemoCache.class).in(Singleton.class);

        // aop
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(LoggedAnnotation.class),
                new MethodInterceptor() {
                    @Override
                    public Object invoke(MethodInvocation invocation) throws Throwable {
                        Method method = invocation.getMethod();
                        System.out.println(String.format("class: %s \n method: %s \n params: %s",
                                method.getDeclaringClass().getName(),
                                method.getName(),
                                Joiner.on(", ").join(invocation.getArguments())));
                        return invocation.proceed();
                    }
                });
    }


//    @Provides @Named("singleton") @Singleton Cache<String, String> getCache() {
//        return new GuiceDemoCache();
//    }
}
