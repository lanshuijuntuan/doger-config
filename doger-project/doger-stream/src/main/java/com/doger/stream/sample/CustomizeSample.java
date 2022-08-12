package com.doger.stream.sample;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@EnableBinding({CustomizeSource.class,CustomizeSink.class})
public class CustomizeSample {

    @StreamListener("customizeQue")
    private void rec(String msg){
       log.info("customizeQue rec msg:"+msg);
    }


    private MessageChannel messageChannel;

    @Autowired
    public CustomizeSample(@Qualifier("customizeQue") MessageChannel mc){
        this.messageChannel=mc;
    }


    public void send(String msg){
        messageChannel.send(new GenericMessage<>("customizeQue send msg:"+msg));
    }

}
