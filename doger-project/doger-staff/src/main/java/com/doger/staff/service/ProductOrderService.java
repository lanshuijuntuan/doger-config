package com.doger.staff.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("doger-order")
public interface ProductOrderService {


    @PostMapping("/order/add")
    String add(String json);

    @GetMapping("/order/list")
    String list();

    @GetMapping("/order/findById")
    String findById(@RequestParam("id") Long id);
}
