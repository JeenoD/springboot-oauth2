package com.jeeno.oauth2client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/23 13:10
 */
@RestController
public class MyTestController {

    @GetMapping("/hi")
    public String hi() {
        return "Hello";
    }

    @GetMapping("/test")
    public String test() {
        return "该方法只有在登录后才能访问";
    }
}
