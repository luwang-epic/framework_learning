package com.learning.spring.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 演示基于@import注解实现 "@Enable模块驱动" 同时说明基于@Bean注解的是轻量模式，而基于@Configuration是完全模式
 *      轻量模式：原始的类
 *      全量模式：通过cglib字节码增量的类
 *
 * @author wanglu
 * @date 2020/03/21
 */
@Configuration
@EnableHelloWorld
public class EnableHelloWorldBootstrap {

    public static void main(String[] args) {
        // 构建基于注解配置驱动spring上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前引导类（被@Configuration标注）到spring上下文
        context.register(EnableHelloWorldBootstrap.class);
        // 启动上下文
        context.refresh();
        // 获取被命名为"helloWorld"的bean对象
        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println("helloWorld = " + helloWorld);
        System.out.println(context.getBean("enableHelloWorldBootstrap"));
        // 关闭上下文
        context.close();
    }
}
