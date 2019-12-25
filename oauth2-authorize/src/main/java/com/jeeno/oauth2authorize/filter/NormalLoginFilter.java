package com.jeeno.oauth2authorize.filter;

import com.jeeno.oauth2authorize.login.token.NormalLoginAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 常规的账号密码登录过滤器
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 14:52
 */
@Slf4j
public class NormalLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final String NORMAL_LOGIN_PATH = "/login";

    public NormalLoginFilter() {
        // 该过滤器所匹配的请求url配置
        super(new AntPathRequestMatcher(NORMAL_LOGIN_PATH, HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.debug("NormalLoginFilter");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        NormalLoginAuthenticationToken loginAuthenticationToken = new NormalLoginAuthenticationToken(username, password);
        return this.getAuthenticationManager().authenticate(loginAuthenticationToken);
    }
}
