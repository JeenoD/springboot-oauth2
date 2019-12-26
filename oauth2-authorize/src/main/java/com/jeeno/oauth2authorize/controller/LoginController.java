package com.jeeno.oauth2authorize.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 14:45
 */
@Slf4j
@Controller
@SessionAttributes("authorizationRequest")
public class LoginController {

    /**
     * 授权认证页面
     * @return HTML
     */
    @GetMapping("/loginPage")
    public String loginPage() {
        return "/loginPage";
    }

    /**
     * 后门登录成功后的返回
     * @return String
     */
    @GetMapping("/index")
    @ResponseBody
    public String loginSuccess() {
        return "认证服务器：后门登录已成功。";
    }

    /**
     * 自定义的授权页面
     * @return HTML
     */
    @GetMapping("/custom/confirm_access")
    public ModelAndView approvalPage(Map<String, Object> model) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView("/approval");
        view.addObject("clientId", authorizationRequest.getClientId());
        return view;
    }
}
