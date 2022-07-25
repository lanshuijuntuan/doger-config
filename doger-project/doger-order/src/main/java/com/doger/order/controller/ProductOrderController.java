package com.doger.order.controller;


import com.doger.order.JacksonUtil;
import com.doger.order.dto.APIResponse;
import com.doger.order.entity.ProductOrder;
import com.doger.order.mapper.ProductOrderMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Log4j2
@RequestMapping("order")
@RestController
public class ProductOrderController {


    @Resource
    private ProductOrderMapper productOrderMapper;

    @RequestMapping("list")
    public List<ProductOrder> list() {
        return productOrderMapper.selectAll();
    }


    @RequestMapping("findById")
    public APIResponse findById(@RequestParam("id") Long id) {
        ProductOrder productOrder = productOrderMapper.selectByPrimaryKey(id);
        if (productOrder == null) {
            return APIResponse.fail("未找到对象");
        } else {
            return APIResponse.ok(productOrder);
        }

    }


    @RequestMapping(value = "add")
    public APIResponse add(@RequestBody ProductOrder productOrder) {
        Date now=new Date();
        productOrder.setCreateTime(now);
        productOrder.setVersion(1);
        productOrder.setUpdateTime(null);
        int ret = productOrderMapper.insert(productOrder);
        log.info("add productOrder ret:" + ret);
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("add productOrder:" + JacksonUtil.toJson(productOrder));
        ProductOrder addProductOrder = productOrderMapper.selectByOrderNo(productOrder.getOrderNo());
        return APIResponse.ok(addProductOrder);
    }


}
