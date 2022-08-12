package com.doger.stream.sample;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomizeSource {


    @Output("customizeQue")
    MessageChannel send();
}
