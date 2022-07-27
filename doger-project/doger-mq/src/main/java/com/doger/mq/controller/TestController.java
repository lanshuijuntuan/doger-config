package com.doger.mq.controller;

import com.doger.mq.config.RabbitRecConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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

    @Resource
    private ConnectionFactory rabbitFactory;

    @Resource
    private RabbitRecConfig rabbitRecConfig;

    @GetMapping("printAppName")
    @ResponseBody
    public String printAppName() {
        String instanceId = serviceRegistration.getInstanceId();
        String host = serviceRegistration.getHost();
        Integer port = serviceRegistration.getPort();
        String repoStr =instanceId+":"+ host + ":" + port + "\n\r" + appName + ":" + envProfile;
        log.info(repoStr);
        return repoStr;
    }

    @GetMapping("/send")
    @ResponseBody
    public String send(@RequestParam("msg") String msg) {
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(rabbitRecConfig.getQueueName(), false, false, false, null);
            if (StringUtils.isEmpty(msg)) {
                msg = "hello world";
            }
            channel.basicPublish("", rabbitRecConfig.getQueueName(), null, msg.getBytes(StandardCharsets.UTF_8));
            log.info("send message:" + msg);
            return "success";
        } catch (Exception ex) {
            log.error("发送异常", ex);
            return "error";
        }
    }





}
