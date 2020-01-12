package com.framework.learning.security.config;

import com.framework.learning.security.handler.MyAuthenticationFailureHandler;
import com.framework.learning.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Security配置
 * @author wanglu
 * @date 2020/01/12
 */
@Component
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * EnableWebSecurity注解：开启web过滤器，去拦截请求
     */

    /**
     * 配置认证用户信心和权限，这里设置某些用户有哪些权限，也就是权限规则
     *  springboot 2.* 密码都需要加密，因此，这样会出错，这里使用一个废弃的方法来跳过密码加密
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //如果想实现动态账号与数据库关联，在该地方改为查询数据库，而不是使用写死了

        //authorities为集合，表示某一个用户可能拥有多个角色权限
        auth.inMemoryAuthentication().withUser("admin").password("123456").authorities("queryOrder", "addOrder", "updateOrder", "deleteOrder");

        //添加另一个账号，user账号   queryOrder和addOrder为给路径分配的权限名称
        auth.inMemoryAuthentication().withUser("user").password("123456").authorities("queryOrder", "addOrder");


    }

    /**
     * 密码的加密方式
     */
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    /**
     * spring security底层是基于拦截器的，底层已经使用了拦截器，这里暴露了该方法，让用户去配置需要配置拦截请求资源，
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //如果控制权限 ： 给每一个请求路径，分配一个权限名称，然后账号只需要关联该名称，就可以有权限访问了

        // /**表示拦截所有请求,  httpBasic： httpBasic模式    formLogin: formLogin模式
        http.authorizeRequests()
                //配置查询订单权限, 给查询订单/queryOrder路径分配一个权限名称为queryOrder，后面雷同
                .antMatchers("/queryOrder").hasAnyAuthority("queryOrder")
                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
                //不拦截登录请求路径
                .antMatchers("/login").permitAll()
                .antMatchers("/**").fullyAuthenticated().and().formLogin()
                //处理登录成功或者失败的，需要返回对应的页面
                .successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler);
    }


}
