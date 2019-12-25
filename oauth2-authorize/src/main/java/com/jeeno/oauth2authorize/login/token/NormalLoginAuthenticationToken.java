package com.jeeno.oauth2authorize.login.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 通用
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 14:56
 */
public class NormalLoginAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * 用户名/用户信息
     */
    private Object principle;
    /**
     * 密码
     */
    private Object credential;

    public NormalLoginAuthenticationToken(Object principle, Object credential) {
        super(null);
        this.principle = principle;
        this.credential = credential;
        this.setAuthenticated(false);
    }

    public NormalLoginAuthenticationToken(Object principle, Object credential, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principle = principle;
        this.credential = credential;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credential;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }
}
