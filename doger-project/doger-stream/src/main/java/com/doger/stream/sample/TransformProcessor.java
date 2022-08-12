package com.doger.stream.sample;


import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;


@Log4j2
@Component
@EnableBinding(Processor.class)
public class TransformProcessor {
    @Transformer(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public Object transformer(String msg){
       return "transformer:"+msg.toUpperCase();
    }
}
