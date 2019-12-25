package com.jeeno.oauth2client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/23 14:11
 */
@Slf4j
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "/index";
    }

}
