//package com.doger.nacoszuul.nacosconfig;
//
//import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
//import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
//import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
//import com.netflix.zuul.ZuulFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ZuulConfig {
//
//    @Bean
//    public ZuulFilter sentinelZuulPreFilter(){
//        return new SentinelZuulPreFilter();
//    }
//
//
//    @Bean
//    public ZuulFilter sentinelZuulPostFilter(){
//        return new SentinelZuulPostFilter();
//    }
//
//    @Bean
//    public ZuulFilter sentinelZuulErrorFilter(){
//        return new SentinelZuulErrorFilter();
//    }
//}
