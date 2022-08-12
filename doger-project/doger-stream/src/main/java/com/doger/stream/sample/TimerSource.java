package com.doger.stream.sample;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableBinding(Source.class)
public class TimerSource {

    private String format="yyyy-MM-dd'T'HH:mm:ss";

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT,poller = @Poller(fixedDelay = "5000",maxMessagesPerPoll = "1"))
    public MessageSource<String> timerMessageSource() {
        return ()->new GenericMessage<>(new SimpleDateFormat(format).format(new Date()));
    }
}
