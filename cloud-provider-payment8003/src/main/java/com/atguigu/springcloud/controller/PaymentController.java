package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String port;


    @GetMapping("/payment/zk")
    public String getZK() {
        return "zk conn " + port + UUID.randomUUID().toString();
    }

}
