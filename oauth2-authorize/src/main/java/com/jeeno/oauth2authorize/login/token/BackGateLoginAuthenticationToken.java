package com.jeeno.oauth2authorize.login.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 00:08
 */
public class BackGateLoginAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * 用户名/用户信息
     */
    private Object principle;

    public BackGateLoginAuthenticationToken(Object principle) {
        super(null);
        this.principle = principle;
        this.setAuthenticated(false);
    }

    public BackGateLoginAuthenticationToken(Object principle, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principle = principle;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }
}
