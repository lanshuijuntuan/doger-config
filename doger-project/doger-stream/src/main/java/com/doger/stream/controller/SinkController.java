package com.doger.stream.controller;


import com.doger.stream.sample.MySourceSample;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sink")
public class SinkController {


    @Resource
    private MySourceSample sendBean;

    @RequestMapping("/sendHello")
    public String sendHello(){
        sendBean.sendHello();
        return "success";
    }


    @RequestMapping("/sendHello1")
    public String sendHello1(@RequestParam("name") String name){
        sendBean.sendHello1(name);
        return "success";
    }
}
