package com.doger.mq.config;

import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Log4j2
@Component
public class RabbitRpcClient {


    @Resource
    private RabbitConfig rabbitConfig;

    @Resource
    private ConnectionFactory rabbitFactory;

    public String call(String num){
        try (Connection connection = rabbitFactory.newConnection();
             Channel channel = connection.createChannel()) {
            String queueName=rabbitConfig.getRpcKey();
            channel.queueDeclare(queueName,false, false, false, null);
            String correlationId= UUID.randomUUID().toString().replace("-","");
            String replyQueue=channel.queueDeclare().getQueue();
            AMQP.BasicProperties basicProperties=new AMQP.BasicProperties().builder()
                    .replyTo(replyQueue)
                    .correlationId(correlationId)
                    .build();


            channel.basicPublish("", queueName, basicProperties,num.getBytes(StandardCharsets.UTF_8));

            final BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<String>(1);

            DeliverCallback deliverCallback=(consumerTag, delivery) -> {
                if(correlationId.equals(delivery.getProperties().getCorrelationId())){
                    blockingQueue.offer(new String(delivery.getBody(), StandardCharsets.UTF_8));
                }
            };
            String consumeTag=channel.basicConsume(replyQueue,true,deliverCallback,consumerTag -> {
            });
            String result=blockingQueue.take();
            channel.basicCancel(consumeTag);
            return result;


        } catch (Exception ex) {
            log.error("发送异常", ex);
            return "error";
        }



    }



}
