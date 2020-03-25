package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        log.info("结果" + res);
        if (res < 0){

            return new CommonResult(444,"数据添加失败",null);

        }else {
            return new CommonResult(200,"数据添加成功",res);

        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment res =  paymentService.getPaymentById(id);
        log.info("结果是" +  res );
        if (res == null ){
            return new CommonResult(444,"没有击落",null);

        }else {
            return new CommonResult(200,"查询成功",res);

        }
    }

}
