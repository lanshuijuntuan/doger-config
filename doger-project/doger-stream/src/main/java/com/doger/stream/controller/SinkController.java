package com.doger.stream.controller;


import com.doger.stream.sample.CustomizeSample;
import com.doger.stream.sample.MyMessageSourceSample;
import com.doger.stream.sample.MySourceSample;
import com.doger.stream.sample.SourceSample;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sink")
public class SinkController {

    @Resource
    private MySourceSample sendBean;

    @Resource
    private MyMessageSourceSample myMessageSourceSample;

    @RequestMapping("/sendHello")
    public String sendHello() {
        sendBean.sendHello();
        return "success";
    }


    @RequestMapping("/sendHello1")
    public String sendHello1(@RequestParam("name") String name) {
        sendBean.sendHello1(name);
        return "success";
    }

    @RequestMapping("/sendHello2")
    public String sendHello2(@RequestParam("name") String name) {
        myMessageSourceSample.sendHello2(name);
        return "success";
    }


    @Resource
    private SourceSample sourceSample;

    @RequestMapping("/sendHello3")
    public String sendHello3(@RequestParam("name") String name) {
        sourceSample.sendHello(name);
        return "success";
    }

    @Resource
    private CustomizeSample customizeSample;

    @RequestMapping("/sendHello4")
    public String sendHello4(@RequestParam("name") String name) {
        customizeSample.send(name);
        return "success";
    }
}
