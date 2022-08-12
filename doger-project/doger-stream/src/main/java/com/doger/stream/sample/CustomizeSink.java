package com.doger.stream.sample;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomizeSink {

    @Input("customizeQue")
    SubscribableChannel receive();
}
