package com.doger.goods.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Random;

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

    @EventListener({ApplicationReadyEvent.class,
    RefreshRemoteApplicationEvent.class,
            RefreshScopeRefreshedEvent.class
    })
    public void refresh(){
        System.out.println("refresh appName:"+environment.getProperty("appName"));
    }


    @HystrixCommand(commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
            value ="1000" )},
            fallbackMethod = "defaultPrintAppName")
    @GetMapping("printAppName")
    @ResponseBody
    public String printAppName(){
        Random random=new Random();
        Integer randVal=random.nextInt(100);
        try {
            Thread.sleep(randVal*25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(randVal%5==0){
         throw new IllegalArgumentException();
        }


        String host=serviceRegistration.getHost();
        Integer port=serviceRegistration.getPort();
        String repoStr= host+":"+port+"\n\r"+appName+":"+envProfile+"randVal:"+randVal;
        log.info(repoStr);
        return repoStr;
    }

    public String defaultPrintAppName(){
        return "default value";
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
