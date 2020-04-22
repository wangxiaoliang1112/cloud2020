package com.atguigu.myrole;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRole {

//    @Bean
//    public IRule myRule() {
//        return new RandomRule();
//    }
}
