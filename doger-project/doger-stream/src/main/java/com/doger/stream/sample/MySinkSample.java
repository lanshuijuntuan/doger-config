package com.doger.stream.sample;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@EnableBinding(MySinkSample.SinkSample.class)
public class MySinkSample {

    @StreamListener(SinkSample.INPUT)
    private void rec(String msg){
        log.info("MySinkSample rec:"+msg);
    }


    public interface SinkSample {

        /**
         * Input channel name.
         */
        String INPUT = "output";

        /**
         * @return input channel.
         */
        @Input(SinkSample.INPUT)
        SubscribableChannel input();

    }
}
