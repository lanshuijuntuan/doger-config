package com.doger.stream.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SourceSample {

    private Processor processor;

    @Autowired
    public SourceSample(Processor processor) {
        this.processor = processor;
    }

    public void sendHello(String name) {
        this.processor.output().send(new GenericMessage<>("hahahaha hello " + name));
    }
}
