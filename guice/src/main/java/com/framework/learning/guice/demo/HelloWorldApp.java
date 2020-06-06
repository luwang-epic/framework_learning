package com.framework.learning.guice.demo;

import com.framework.learning.guice.demo.annotation.HelloWorldAnnotation;
import com.framework.learning.guice.demo.service.StringPrinterService;
import com.framework.learning.guice.demo.service.StringProviderService;
import com.google.inject.Guice;
import com.google.inject.Key;

/**
 * guice的演示
 *
 * @author wanglu
 * @date 2020/06/06
 */
public class HelloWorldApp {

//    public static void main(String[] args) {
//        StringPrinterService helloWorldPrinter = Configuration.getHelloWorldPrinter();
//        helloWorldPrinter.run();
//    }


    public static void main(String[] args) {

        /*
         * createInjector时只是记录所有表达式，并没有生成实例
         * getInstance时才更加表达式调用构造函数来生成实例
         *
         * 当创建实例比较耗时时，建议通过Provider<T>提供，实现懒加载
         *      这样在getInstance的时候就不会实例化，而是到调用Provider.get()方法时，才会加载
         */
        StringPrinterService stringPrinterService = Guice.createInjector(new MainModule())
                                                         .getInstance(StringPrinterService.class);
        stringPrinterService.run();

        // 当有多个实例时，通过Key来指定获取哪个实例
        StringProviderService stringProviderService = Guice.createInjector(new MainModule()).getInstance(Key.get(StringProviderService.class, HelloWorldAnnotation.class));
        System.out.println(stringProviderService.get());
    }

}
