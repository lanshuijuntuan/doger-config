package com.doger.stream.sample;


import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


@Log4j2
@EnableBinding(Sink.class)
public class SinkReceiver {



    @StreamListener(Sink.INPUT)
    private void receive(Object payLoad){
        log.info("rec :"+payLoad);
    }
}
