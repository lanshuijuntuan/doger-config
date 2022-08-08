package com.doger.goods.service.impl;

import com.doger.goods.service.GreetingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Resource
    private Registration serviceRegistration;

    @HystrixCommand(fallbackMethod ="fallBackGreeting" )
    @Override
    public String greeting(String name) {
        Random random=new Random();
        int randVal=random.nextInt(100);
        if(randVal>80){
            throw new IllegalArgumentException();
        }
        String instanceId=serviceRegistration.getInstanceId();
        Date now=new Date();
        String dateStr=DateFormatUtils.ISO_DATE_FORMAT.format(now);
        return String.format("%s: hello %s  from %s", dateStr, name, instanceId);
    }


    private String fallBackGreeting(String name){
        return "hello "+name;
    }
}
