package com.framework.learning.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登录失败
 * @author wanglu
 * @date 2020/01/12
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败， user: {}, password: {}", httpServletRequest.getParameter("username"), httpServletRequest.getParameter("password"));
        httpServletResponse.sendRedirect("/login");
    }
}
