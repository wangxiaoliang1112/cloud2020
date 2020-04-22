package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @Value("${server.port}")
    private String port;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_info(@PathVariable("id") Integer id) {
        String res =  paymentService.payment_info(id);
        log.info("ok 结果是" +  res );
        return res;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id) {
        String res =  paymentService.payment_timeout(id);
        log.info("error 结果是" +  res );
        return res;
    }



    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String res =  paymentService.paymentCircuitBreaker(id);
        log.info(" 结果是" +  res );
        return res;
    }
}