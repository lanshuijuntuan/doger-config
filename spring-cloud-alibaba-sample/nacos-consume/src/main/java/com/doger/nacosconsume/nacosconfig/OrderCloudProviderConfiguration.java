package com.doger.nacosconsume.nacosconfig;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 某个服务客户端负载均衡策略
 */
@Configuration
public class OrderCloudProviderConfiguration {

//    @Bean
//    public IRule ribbonRule() {
//        return new RoundRobinRule();
//    }

    //    @Bean
//    public IRule ribbonRule(){
//        return new RandomRule();
//    }

//    @Bean
//    public IRule ribbonRule() {
//        return new NacosRule();
//    }
}
