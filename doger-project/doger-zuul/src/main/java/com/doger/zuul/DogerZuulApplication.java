package com.doger.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy
@SpringBootApplication
public class DogerZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogerZuulApplication.class, args);
    }

}
