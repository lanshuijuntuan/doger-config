package com.doger.goods.controller;


import com.doger.goods.service.GreetingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class GreetingController {

    @Resource
    private GreetingService greetingService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam("name") String name){
        return greetingService.greeting(name);
    }
}
