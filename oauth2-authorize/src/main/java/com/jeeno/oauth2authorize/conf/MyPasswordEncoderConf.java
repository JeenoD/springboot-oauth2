package com.jeeno.oauth2authorize.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/23 12:46
 */
@Component
public class MyPasswordEncoderConf {
    @Bean
    public PasswordEncoder myPassEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("secret"));
//    }
}
