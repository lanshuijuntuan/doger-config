package com.doger.nacosstorage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.doger.nacosstorage.dao")
@SpringBootApplication
public class NacosStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosStorageApplication.class, args);
    }

}
