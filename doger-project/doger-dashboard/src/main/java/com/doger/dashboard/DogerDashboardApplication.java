package com.doger.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class DogerDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogerDashboardApplication.class, args);
    }

}
