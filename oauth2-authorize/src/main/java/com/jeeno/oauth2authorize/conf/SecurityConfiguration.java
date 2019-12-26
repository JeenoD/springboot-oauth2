package com.jeeno.oauth2authorize.conf;

import com.jeeno.oauth2authorize.login.provider.BackGateAuthenticationProvider;
import com.jeeno.oauth2authorize.login.provider.NormalLoginAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/23 10:45
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private AccessDeniedHandler deniedHandler;

    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failureHandler;

    @Resource
    private NormalLoginAuthenticationProvider normalLoginProvider;

    @Resource
    private NormalLoginConfig normalLoginConfig;

    @Resource
    private BackGateConfig backGateConfig;

    @Resource
    private BackGateAuthenticationProvider backGateAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 从db中加载用户信息
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

        // 注册通用帐号密码登录的自定义认证器
        builder.authenticationProvider(normalLoginProvider);
        // 注册后门登录的自定义认证器
        builder.authenticationProvider(backGateAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling().accessDeniedHandler(deniedHandler);

        http.authorizeRequests()
                .antMatchers("/auth/oauth/**", "/oauth/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/auth/login")
                .permitAll()
                // 认证成功处理逻辑(这里不应该加，默认会跳回到之前的请求资源服务器路径)
//                .successHandler(successHandler)
                // 认证失败处理逻辑
                .failureHandler(failureHandler);

        // 常规登录的过滤器
        http.apply(normalLoginConfig);
        // 后门登录的过滤器
        http.apply(backGateConfig);

        // 禁用csrf防护
        http.csrf().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
