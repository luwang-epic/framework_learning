package com.framework.learning.guice.demo.service;

import com.framework.learning.guice.demo.annotation.HelloWorldAnnotation;
import com.framework.learning.guice.demo.annotation.TestAnnotation;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.io.PrintStream;

/**
 * HelloWorld模块相关的依赖注入
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class HelloWorldModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StringPrinterService.class).to(StringPrinterServiceImpl.class);
        bind(PrintStreamWriterService.class).to(PrintStreamWriterServiceImpl.class);
        // 绑定某个类的对象到一个实例
        bind(PrintStream.class).toInstance(System.out);
    }


    /**
     * StringProviderService没有实现类，可以通过@Provides注解来提供
     *  但是如果有多个实现怎么办？ 可以通过注解的方式来区分
     *      该注解需要使用@BindingAnnotation来注解，同时作用域为运行时 @Retention(RetentionPolicy.RUNTIME)，
     *      从而让guice可以识别， 使用的时候使用自定义的注解来区分
     */
    @Provides @HelloWorldAnnotation
    private StringProviderService getHelloWorldStringProviderService() {
        return new StringProviderService() {
            @Override
            public String get() {
                return "hello world";
            }
        };
    }

    @Provides @TestAnnotation
    private StringProviderService getTestStringProviderService() {
        return new StringProviderService() {
            @Override
            public String get() {
                return "test";
            }
        };
    }
}
