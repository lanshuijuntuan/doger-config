package com.doger.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("test")
public class TestController {

    @Value("${env.profile}")
    private String envProfile;

    @Value("${appName}")
    private String appName;

    @GetMapping("printAppName")
    @ResponseBody
    public String printAppName(){
        return appName+":"+envProfile;
    }



    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("printConsumer")
    @ResponseBody
    public String printConsumer(){
       return restTemplate.getForEntity("http://doger-goods/test/printAppName",String.class).getBody();
    }


}
