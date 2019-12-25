package com.jeeno.oauth2authorize.conf;

import com.alibaba.fastjson.JSONObject;
import com.jeeno.oauth2authorize.pojo.ReturnDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/24 23:34
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.debug("MyAuthenticationFailureHandler");
        response.setContentType("application/json;charset=utf-8");
        ReturnDTO<String> result = ReturnDTO.<String>builder()
                .status(ReturnDTO.EnumStatus.FAILURE)
                .data(e.getMessage())
                .message("认证失败").build();
        response.getWriter().print(JSONObject.toJSONString(result));
        response.getWriter().flush();
    }
}
