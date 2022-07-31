package com.doger.mq;

import com.doger.mq.rabbit.Sender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DogerMqApplication.class)
class DogerMqApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private Sender sender;

    @Test
    private void send(){
        System.out.println("start test");
        sender.send("ggag");
        System.out.println("end test");
    }

}
