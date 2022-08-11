package com.doger.stream.sample;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MySource {


    /**
     * Name of the output channel.
     */
    String NAME = "MYSOURCE";

    /**
     * @return output channel
     */
    @Output(MySource.NAME)
    MessageChannel output();


    @Input(MySource.NAME)
    SubscribableChannel input();


}
