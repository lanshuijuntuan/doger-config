package com.doger.mq.controller;

import com.doger.mq.config.RabbitConfig;
import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;


@Log4j2
@RestController
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
    public String printAppName() {
        String instanceId = serviceRegistration.getInstanceId();
        String host = serviceRegistration.getHost();
        Integer port = serviceRegistration.getPort();
        String repoStr = instanceId + ":" + host + ":" + port + "\n\r" + appName + ":" + envProfile;
        log.info(repoStr);
        return repoStr;
    }

}
