package com.doger.goods.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {

    @Value("${from}")
    private String from;

    @GetMapping("printFrom")
    @ResponseBody
    public String printFrom(){
        return from;
    }
}
