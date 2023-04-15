package com.microservice.student.services.feign;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@Deprecated
@LoadBalancerClient(value = "address-service")
public class AddressServiceLoadBalancer {
    @Bean
    @LoadBalanced
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
