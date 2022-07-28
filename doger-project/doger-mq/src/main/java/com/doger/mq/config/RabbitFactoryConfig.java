package com.doger.mq.config;

import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class RabbitFactoryConfig {


    @Value("${rabbit.host:192.168.1.120}")
    private String rabbitServerHost;

    @Value("${rabbit.port:5672}")
    private Integer rabbitServerPort;


    @Value("${rabbit.userName:test}")
    private String rabbitUser;

    @Value("${rabbit.password:test}")
    private String rabbitPassword;

    @Bean("rabbitFactory")
    public ConnectionFactory rabbitFactory(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitServerHost);
        connectionFactory.setPort(rabbitServerPort);
        connectionFactory.setUsername(rabbitUser);
        connectionFactory.setPassword(rabbitPassword);
        connectionFactory.setVirtualHost("/test");
        return connectionFactory;
    }
}
