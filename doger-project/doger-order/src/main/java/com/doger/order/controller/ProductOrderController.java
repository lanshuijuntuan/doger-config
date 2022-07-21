package com.doger.order.controller;


import com.doger.order.entity.ProductOrder;
import com.doger.order.mapper.ProductOrderMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("order")
@RestController
public class ProductOrderController {


    @Resource
    private ProductOrderMapper productOrderMapper;

    @RequestMapping("list")
    public List<ProductOrder> list(){
       return  productOrderMapper.selectAll();
    }

}
