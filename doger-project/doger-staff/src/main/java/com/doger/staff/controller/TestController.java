package com.doger.staff.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RefreshScope
@Log4j2
@Controller
@RequestMapping("test")
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



    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("printConsumer")
    @ResponseBody
    public String printConsumer(){
        log.info("printConsumer start...............");
       return restTemplate.getForEntity("http://doger-goods/test/printAppName",String.class).getBody();
    }


    @RequestMapping("consumerGreeting")
    @ResponseBody
    public String consumerGreeting(@RequestParam("name") String name){
        log.info("consumerGreeting start...............");
        return restTemplate.getForEntity("http://doger-goods/greeting?name="+name,String.class).getBody();
//        return restTemplate.getForEntity("http://192.168.57.75:8061/greeting?name="+name,String.class).getBody();
    }

}
