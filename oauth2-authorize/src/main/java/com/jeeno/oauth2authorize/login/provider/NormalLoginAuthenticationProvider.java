package com.jeeno.oauth2authorize.login.provider;

import com.jeeno.oauth2authorize.login.NormalAuthenticationException;
import com.jeeno.oauth2authorize.login.token.NormalLoginAuthenticationToken;
import com.jeeno.oauth2authorize.pojo.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自定义通用帐号、密码登录的认证器逻辑
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 21:46
 */
@Slf4j
@Component
public class NormalLoginAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        NormalLoginAuthenticationToken token = (NormalLoginAuthenticationToken)authentication;
        String username = (String)token.getPrincipal();
        String password = (String)token.getCredentials();

        UserDO user = (UserDO)userDetailsService.loadUserByUsername(username);
        // 校验密码(这里是传过来的明文密码 与 数据库中的加密密码比较)
        if (user==null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new NormalAuthenticationException("用户名或密码错误");
        }
        return new NormalLoginAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return NormalLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
