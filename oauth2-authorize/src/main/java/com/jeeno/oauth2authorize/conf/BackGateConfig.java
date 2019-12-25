package com.jeeno.oauth2authorize.conf;

import com.jeeno.oauth2authorize.filter.BackGateFilter;
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
 * @date 2019/12/25 00:11
 */
@Slf4j
@Component
public class BackGateConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private MyAuthenticationFailureHandler loginFailedHandler;

    @Resource
    private BackGateAuthenticationSuccessHandler successHandler;

    @Override
    public void configure(HttpSecurity http) {
        // 后门登录过滤器
        BackGateFilter loginFilter = new BackGateFilter();
        loginFilter.setAuthenticationSuccessHandler(successHandler);
        loginFilter.setAuthenticationFailureHandler(loginFailedHandler);
        loginFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        loginFilter.setSessionAuthenticationStrategy(http.getSharedObject(SessionAuthenticationStrategy.class));
        // 注册过滤器
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
    }
}