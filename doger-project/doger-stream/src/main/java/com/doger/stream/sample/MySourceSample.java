package com.doger.stream.sample;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Log4j2
@EnableBinding({MySource.class})
@Component
public class MySourceSample {

    @Autowired
    public MySourceSample(MySource source){
        this.source=source;
    }

    private MySource source;

    public void sendHello(){
        source.output().send(new GenericMessage<>("hello"));
    }
    public void sendHello1(String name){
        source.output().send(new GenericMessage<>("hello "+name));
    }

    @StreamListener(MySource.NAME)
    private void receive(Object payLoad){
        log.info("rec :"+payLoad);
    }

}
