package com.doger.stream.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;


/**
 * 通道注入发送消息
 */
@Component
public class MyMessageSourceSample {


    private MessageChannel myMessageSource;

    @Autowired 
    public MyMessageSourceSample(@Qualifier("MYSOURCE") MessageChannel messageChannel){
        this.myMessageSource=messageChannel;
    }


    public void sendHello2(String name) {
        myMessageSource.send(new GenericMessage<>("hello 2 "+name));
    }
}
