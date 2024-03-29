package com.doger.staff.controller;


import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("ribbonProductOrder")
public class RibbonProductOrderController {


    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    String add(String json) {
        return restTemplate.postForObject("http://doger-order/order/add", json, String.class);
    }


    @GetMapping("/list")
    String list() {
        return restTemplate.getForObject("http://doger-order/order/list", String.class);
    }


    @GetMapping("/findById")
    String findById(@RequestParam("id") Long id) {
        log.info("findById id=" + id);
        return findByCache(id);
    }

        @HystrixCommand(commandProperties = {@HystrixProperty(name= "requestCache.enabled",value = "true")},
            fallbackMethod ="findByIdFallBack" )
//    @HystrixCommand(fallbackMethod = "findByIdFallBack")
    @CacheResult
    private String findByCache(@CacheKey Long id) {
        //urlVariableMap
//        Map map = new HashMap();
//        map.put("id", id);
//        return restTemplate.getForObject("http://doger-order/order/findById?id={id}", String.class, map);
        //uriVariables 数组
//        return restTemplate.getForObject("http://doger-order/order/findById?id={1}", String.class, id);

            try {
                URI uri= UriComponentsBuilder.fromUriString("http://doger-order/order/findById?id={1}")
                        .build(id);
//                URI uri=new URI("http://doger-order/order/findById?id=1");
               return restTemplate.getForObject(uri,String.class);
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }


        }


    String findByIdFallBack(Long id) {
        return "api fallBack";
    }
}
