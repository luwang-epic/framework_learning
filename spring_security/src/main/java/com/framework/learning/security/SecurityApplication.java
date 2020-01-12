package com.framework.learning.security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wanglu
 * @date 2020/01/12
 */
@SpringBootApplication
public class SecurityApplication {


    /**
     * Spring Security在很多企业中作为后台角色权限框架、授权认证oauth2.0、安全防护（防止跨站点请求）、Session攻击、非常容易融合SpringMVC使用等
     *
     * Spring Security有两种模式
     *      1. formLogin模式：表单提交认证模式，也就是提交表单的时候，认证，如果没有登录或者权限，跳转到相应的页面，一般使用该模式
     *      2. HttpBasic模式，浏览器与服务器做认证授权，如果没有权限，会弹出没有权限的对话框，一般不用这个模式
     *
     * Spring Security底层是基于拦截器做的，是基于RBAC模式和Oauth2.0协议的
     *
     *
     * 这个demo演示的场景是：（403：权限不足  401：没有授权）
     *      admin账户  所有请求都有权限访问，包括：查询，添加，修改和删除订单
     *      user账户   只能访问查询和添加订单
     */

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
