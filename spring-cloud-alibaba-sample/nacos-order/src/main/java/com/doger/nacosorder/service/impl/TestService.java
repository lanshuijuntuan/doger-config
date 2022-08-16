package com.doger.nacosorder.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @SentinelResource(value = "helloEcho", fallback = "helloEchoFallback",blockHandler = "helloEchoBlock")
    public String helloEcho() {
        int rand = RandomUtils.nextInt(10);
        try {
            Thread.sleep(rand * 15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "echo:hello";
    }

    public String helloEchoFallback() {
        return "echo:error";
    }

    public String helloEchoBlock(BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "echo:block ";
    }
}
