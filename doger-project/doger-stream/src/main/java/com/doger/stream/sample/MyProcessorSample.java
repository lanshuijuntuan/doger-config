package com.doger.stream.sample;


import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@Log4j2
@EnableBinding({Processor.class})
public class MyProcessorSample {



    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String process(String  msg){
        log.info("get msg:"+msg);
        return "hello"+msg.toUpperCase();
    }



}
