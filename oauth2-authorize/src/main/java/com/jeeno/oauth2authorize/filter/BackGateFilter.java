package com.jeeno.oauth2authorize.filter;

import com.jeeno.oauth2authorize.login.token.BackGateLoginAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 00:04
 */
@Slf4j
public class BackGateFilter extends AbstractAuthenticationProcessingFilter {

    private static String PATH = "/back/gate";

    public BackGateFilter() {
        // 该过滤器所匹配的请求url配置
        super(new AntPathRequestMatcher(PATH , "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.debug("#BackGateFilter#");
        String username = request.getParameter("username");
        BackGateLoginAuthenticationToken backGateAuthentication = new BackGateLoginAuthenticationToken(username);
        return this.getAuthenticationManager().authenticate(backGateAuthentication);
    }
}
