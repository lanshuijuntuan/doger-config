package com.doger.mq.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Log4j2
@Configuration
public class RabbitRecConfig {

    public String getQueueName() {
        return QUEUE_NAME;
    }

    @Value("${rabbit.queueName:doger-mq.test}")
    private String QUEUE_NAME;

    private static final Object lock=new Object();

    @Resource
    private ConnectionFactory rabbitFactory;

    private void rec(){
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(getQueueName(), false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info("rec message:" + msg);
            };
            channel.basicConsume(getQueueName(), true, deliverCallback, consumerTag -> {
            });
            log.info("rec block...........");
            synchronized (lock){
                lock.wait();
            }
            log.info("rec release...........");

//                while (true) {
//                    Thread.sleep(1000);
//                }
        } catch (Exception ex) {
            log.error("接收异常", ex);
        }
    }

    @PostConstruct
    private void init() {
        log.info("executorService init............");
        ExecutorService executorService= Executors.newFixedThreadPool(4);
        executorService.execute(this::rec);
        log.info("executorService start............");
    }

    @PreDestroy
    private void destroy(){
        log.info("destroy rabbitRecConfig...........");
        synchronized (lock) {
            lock.notifyAll();
        }
    }


}
