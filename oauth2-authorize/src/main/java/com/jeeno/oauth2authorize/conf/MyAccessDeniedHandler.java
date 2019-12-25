package com.jeeno.oauth2authorize.conf;

import com.alibaba.fastjson.JSONObject;
import com.jeeno.oauth2authorize.pojo.ReturnDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败的处理器
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/24 15:44
 */
@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        log.debug("#MyAccessDeniedHandler#");
        response.setContentType("application/json;charset=utf-8");
        ReturnDTO<String> result = ReturnDTO.<String>builder()
                .status(ReturnDTO.EnumStatus.SUCCESS)
                .data(e.getMessage())
                .message("认证失败").build();
        response.getWriter().print(JSONObject.toJSONString(result));
        response.getWriter().flush();
    }
}
