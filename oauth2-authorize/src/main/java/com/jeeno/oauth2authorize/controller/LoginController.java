package com.jeeno.oauth2authorize.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 14:45
 */
@Slf4j
@Controller
public class LoginController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "/loginPage";
    }

    @GetMapping("/index")
    @ResponseBody
    public String loginSuccess() {
        return "认证服务器：后门登录已成功。";
    }
}
