package com.doger.nacosconsume.service;

import com.doger.nacosconsume.nacosconfig.OrderCloudProviderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "nacos-order",configuration = OrderCloudProviderConfiguration.class)
public interface OrderService {

    @RequestMapping("/test/printAppName")
    String printAppName();
}
