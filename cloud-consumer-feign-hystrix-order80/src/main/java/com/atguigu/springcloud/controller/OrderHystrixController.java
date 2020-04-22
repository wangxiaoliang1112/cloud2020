package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService service;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String payment_info(@PathVariable("id") Integer id) {
        String res =  service.payment_info(id);
        return res;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFall",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String payment_timeout(@PathVariable("id") Integer id) {
        int i  = 1/0 ;
        String res =  service.payment_timeout(id);
        return res;
    }


    public String paymentTimeoutFall(@PathVariable("id")Integer id){
        return " 我是消费者80 支付侧接口调用超时，请稍后再试 "  ;
    }


    /**
     * 全局fallback方法
     *
     * @return
    */
    private String payment_Global_FallbackMethod() {
            return "Global异常处理信息,请稍后再试。";
        }
}
