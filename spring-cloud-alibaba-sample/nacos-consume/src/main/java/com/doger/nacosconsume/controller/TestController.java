package com.doger.nacosconsume.controller;


import com.doger.nacosconsume.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Log4j2
@RequestMapping("/test")
@RestController
@RefreshScope
public class TestController {


    @Value("${env.profile}")
    private String envProfile;

    @Value("${appName}")
    private String appName;

    @Resource
    private Registration serviceRegistration;

    @GetMapping("printAppName")
    @ResponseBody
    public String printAppName(){
        String instanceId=serviceRegistration.getInstanceId();
        String host=serviceRegistration.getHost();
        Integer port=serviceRegistration.getPort();
        String repoStr= host+":"+port+"\n\r"+appName+":"+envProfile;
        log.info(repoStr);
        return repoStr;
    }


    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumeMsg")
    public String consumeMsg(){
       return restTemplate.getForObject("http://nacos-order/test/printAppName",String.class);
    }

    @Resource
    private OrderService orderService;

    @RequestMapping("/consumeMsg1")
    public String consumeMsg1(){
        return orderService.printAppName();
    }
}
