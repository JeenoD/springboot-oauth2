package com.jeeno.oauth2authorize.conf;

import com.alibaba.fastjson.JSONObject;
import com.jeeno.oauth2authorize.pojo.ReturnDTO;
import com.jeeno.oauth2authorize.pojo.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/24 23:32
 */
@Slf4j
@Primary
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.debug("MyAuthenticationSuccessHandler");
        UserDO userDO = (UserDO)authentication.getPrincipal();
        response.setContentType("application/json;charset=utf-8");
        ReturnDTO<UserDO> result = ReturnDTO.<UserDO>builder()
                .status(ReturnDTO.EnumStatus.SUCCESS)
                .data(userDO)
                .message("认证成功").build();
        response.getWriter().print(JSONObject.toJSONString(result));
        response.getWriter().flush();
    }
}
