package com.doger.mq.controller;


import com.doger.mq.config.RabbitConfig;
import com.doger.mq.config.RabbitRpcClient;
import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("rabbitSample")
public class RabbitSampleController {
    @Resource
    private Registration serviceRegistration;

    @Resource
    private ConnectionFactory rabbitFactory;

    @Resource
    private RabbitConfig rabbitConfig;


    @GetMapping("/send")
    @ResponseBody
    public String send(@RequestParam("msg") String msg) {
        String instanceId = serviceRegistration.getInstanceId();
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(rabbitConfig.getSendQueue(), false, false, false, null);
            if (StringUtils.isEmpty(msg)) {
                msg = "hello world";
            }
            channel.basicPublish("", rabbitConfig.getSendQueue(), null, msg.getBytes(StandardCharsets.UTF_8));
            log.info(instanceId + "send message:" + msg);
            return instanceId + "send success";
        } catch (Exception ex) {
            log.error("发送异常", ex);
            return "error";
        }
    }


    @GetMapping("/broadcast")
    @ResponseBody
    public String broadcast(@RequestParam("msg") String msg) {
        String instanceId = serviceRegistration.getInstanceId();
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(rabbitConfig.getBroadcastExchange(), BuiltinExchangeType.FANOUT);
            if (StringUtils.isEmpty(msg)) {
                msg = "hello world";
            }
            channel.basicPublish(rabbitConfig.getBroadcastExchange(), "", null,
                    msg.getBytes(StandardCharsets.UTF_8));
            log.info("broadcast message:" + msg);
            return instanceId + "broadcast success";
        } catch (Exception ex) {
            log.error("发送异常", ex);
            return "error";
        }
    }

    @GetMapping("/route")
    @ResponseBody
    public String route(@RequestParam("routeKey") String routeKey, @RequestParam("msg") String msg) {
        if (StringUtils.isEmpty(routeKey)) {
            return "routeKey error";
        }
        if (StringUtils.isEmpty(msg)) {
            return "msg error";
        }
//        if(!ArrayUtils.contains(rabbitConfig.getRouteKyes(),routeKey)){
//            return "routeKey value error";
//        }
        String instanceId = serviceRegistration.getInstanceId();
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(rabbitConfig.getRouteExchange(), BuiltinExchangeType.DIRECT);
            channel.basicPublish(rabbitConfig.getRouteExchange(), routeKey, null, msg.getBytes(StandardCharsets.UTF_8));
            log.info(instanceId + "route message:" + msg);
            return instanceId + "route success";
        } catch (Exception ex) {
            log.error("发送异常", ex);
            return "error";
        }
    }


    @GetMapping("/topic")
    @ResponseBody
    public String topic(@RequestParam("key") String key, @RequestParam("msg") String msg) {
        if (StringUtils.isEmpty(key)) {
            return "key error";
        }
        if (StringUtils.isEmpty(msg)) {
            return "msg error";
        }
//        if(!ArrayUtils.contains(rabbitConfig.getRouteKyes(),routeKey)){
//            return "routeKey value error";
//        }
        String instanceId = serviceRegistration.getInstanceId();
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(rabbitConfig.getTopicExchange(), BuiltinExchangeType.TOPIC);
            channel.basicPublish(rabbitConfig.getTopicExchange(), key, null, msg.getBytes(StandardCharsets.UTF_8));
            log.info(instanceId + "topic message:" + msg);
            return instanceId + "topic success";
        } catch (Exception ex) {
            log.error("发送异常", ex);
            return "error";
        }
    }


    @GetMapping("/header")
    @ResponseBody
    public String header(@RequestParam("sex") String sex, @RequestParam("age") Integer age,
                         @RequestParam("msg") String msg) {

        if (StringUtils.isEmpty(msg)) {
            return "msg error";
        }

        Map<String, Object> headers = new HashMap<>();
        headers.put("sex", sex);
        headers.put("age", age);
        String instanceId = serviceRegistration.getInstanceId();
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(rabbitConfig.getHeaderExchange(), BuiltinExchangeType.HEADERS);

            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            builder.deliveryMode(MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode());
            builder.priority(MessageProperties.PERSISTENT_TEXT_PLAIN.getPriority());
            builder.headers(headers);

            AMQP.BasicProperties properties = builder.build();

            channel.basicPublish(rabbitConfig.getHeaderExchange(), rabbitConfig.getHeaderRouteKey(), properties, msg.getBytes(StandardCharsets.UTF_8));
            log.info(instanceId + "header message:" + msg);
            return instanceId + "header success";
        } catch (Exception ex) {
            log.error("发送异常", ex);
            return "error";
        }
    }


    @Resource
    private RabbitRpcClient rabbitRpcClient;


    @GetMapping("/rpc")
    @ResponseBody
    public String header(@RequestParam("num") String num) {
        log.info("start rpc.............");
        if (StringUtils.isEmpty(num)) {
            return "num error";
        }
        String ret = rabbitRpcClient.call(num);
        log.info("end rpc.............");
        return ret;
    }
}
