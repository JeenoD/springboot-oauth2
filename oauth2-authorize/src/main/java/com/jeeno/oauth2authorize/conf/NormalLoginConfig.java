package com.jeeno.oauth2authorize.conf;

import com.jeeno.oauth2authorize.filter.NormalLoginFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 16:57
 */
@Slf4j
@Component
public class NormalLoginConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) {
        // 通用登录过滤器
        NormalLoginFilter loginFilter = new NormalLoginFilter();
//        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        loginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        loginFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        loginFilter.setSessionAuthenticationStrategy(http.getSharedObject(SessionAuthenticationStrategy.class));
        // 注册过滤器
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
