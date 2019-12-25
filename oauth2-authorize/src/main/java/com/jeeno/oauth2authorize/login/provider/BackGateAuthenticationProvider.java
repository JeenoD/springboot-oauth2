package com.jeeno.oauth2authorize.login.provider;

import com.jeeno.oauth2authorize.login.token.BackGateLoginAuthenticationToken;
import com.jeeno.oauth2authorize.pojo.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 00:24
 */
@Slf4j
@Component
public class BackGateAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String)authentication.getPrincipal();
        UserDO user = (UserDO)userDetailsService.loadUserByUsername(username);
        return new BackGateLoginAuthenticationToken(user, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BackGateLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}