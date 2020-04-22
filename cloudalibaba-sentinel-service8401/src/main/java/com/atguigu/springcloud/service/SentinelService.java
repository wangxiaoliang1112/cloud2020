package com.atguigu.springcloud.service;

import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SentinelService {
    @SentinelResource(value="getTest")
    public String getTest(){
        log.info("get test ... ");
        return "test";
    }
}
