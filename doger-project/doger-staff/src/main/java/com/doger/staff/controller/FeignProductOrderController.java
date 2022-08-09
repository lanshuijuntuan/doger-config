package com.doger.staff.controller;


import com.doger.staff.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Log4j2
@RestController
@RequestMapping("feignProductOrder")
public class FeignProductOrderController {

    @Resource
    private ProductOrderService productOrderService;



    @PostMapping("/add")
    String add(@RequestBody String json){
        return productOrderService.add(json);
    }


    @GetMapping(value = "/list")
    String list(){
        return productOrderService.list();
    }


    @HystrixCommand(fallbackMethod ="findByIdFallBack" )
    @GetMapping("/findById")
    String findById(@RequestParam("id") Long id){
        return productOrderService.findById(id);
    }


    String findByIdFallBack(Long id){
        return "api fallBack";
    }
}
