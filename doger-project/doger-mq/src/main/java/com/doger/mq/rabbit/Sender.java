package com.doger.mq.rabbit;


import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class Sender {

    @Resource
    private AmqpTemplate amqpTemplate;

    public void send(String message){
        message= "Sender:"+DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date())+":"+message;
        amqpTemplate.convertAndSend("hello",message);
    }
}
