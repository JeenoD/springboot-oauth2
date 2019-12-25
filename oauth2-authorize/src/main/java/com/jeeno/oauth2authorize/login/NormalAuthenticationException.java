package com.jeeno.oauth2authorize.login;

import org.springframework.security.core.AuthenticationException;

/**
 * 常规帐号密码登录的异常类
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 22:42
 */
public class NormalAuthenticationException extends AuthenticationException {
    public NormalAuthenticationException(String msg) {
        super(msg);
    }
}
