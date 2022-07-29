package com.doger.goods.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Log4j2
@RefreshScope
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
        String host=serviceRegistration.getHost();
        Integer port=serviceRegistration.getPort();
        String repoStr= host+":"+port+"\n\r"+appName+":"+envProfile;
        log.info(repoStr);
        return repoStr;
    }

    @Resource
    private Environment environment;

    @GetMapping("printEnvName")
    @ResponseBody
    public String printEnvName(){
        String repoStr= environment.getProperty("appName")+":"+environment.getProperty("env.profile");
        log.info(repoStr);
        return repoStr;
    }



}
