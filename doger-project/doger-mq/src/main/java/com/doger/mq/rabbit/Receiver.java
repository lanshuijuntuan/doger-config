package com.doger.mq.rabbit;


import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Log4j2
@Component
@RabbitListener(queues="hello")
public class Receiver {

    @RabbitHandler
    public void process(String message){
        log.info("Receiver:"+message);
    }
}
