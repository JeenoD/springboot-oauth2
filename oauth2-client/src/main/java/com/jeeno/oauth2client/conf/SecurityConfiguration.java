package com.jeeno.oauth2client.conf;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 杜家浩
 * @version 2.1.1
 * @date 2019/12/20 15:56
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin();

        http.authorizeRequests()
                .antMatchers("/hi").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
    }

}