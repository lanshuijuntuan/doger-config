package com.doger.nacosorder.controller;


import com.doger.nacosorder.dto.APIResponse;
import com.doger.nacosorder.entity.Order;
import com.doger.nacosorder.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j2
@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/create")
    public APIResponse create(@RequestBody Order order){
        try {
            return orderService.createOrder(order);
        }catch (Exception ex){
            log.error("下单异常",ex);
            return APIResponse.fail();
        }
    }

}
