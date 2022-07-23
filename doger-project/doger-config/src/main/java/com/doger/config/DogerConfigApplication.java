package com.doger.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigServer
@SpringBootApplication
public class DogerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogerConfigApplication.class, args);
    }

}
