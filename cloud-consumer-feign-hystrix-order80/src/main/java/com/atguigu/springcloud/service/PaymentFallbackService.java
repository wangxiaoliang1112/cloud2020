package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class PaymentFallbackService  implements PaymentHystrixService{


    @Override
    public String payment_info(Integer id) {
        return "PaymentFallbackService  payment_info  ok  ...... ";
    }

    @Override
    public String payment_timeout(Integer id) {
        return "PaymentFallbackService    payment_timeout   ok  ...  ";
    }
}
