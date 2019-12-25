package com.jeeno.oauth2client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author 杜家浩
 * @version 2.1.1
 * @date 2019/12/23 16:09
 */
@Slf4j
@RestController
public class UserController {

    @GetMapping("/me")
    public Principal userInfo(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

}
