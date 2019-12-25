package com.jeeno.oauth2authorize.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后门登录成功的逻辑处理
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 23:15
 */
@Slf4j
@Component
public class BackGateAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.debug("BackGateAuthenticationSuccessHandler");

        response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
        response.sendRedirect("/auth/index");
    }
}
