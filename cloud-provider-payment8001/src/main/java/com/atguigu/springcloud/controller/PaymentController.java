package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        log.info("结果" + res);
        if (res < 0){

            return new CommonResult(444,"数据添加失败 " + port,null);

        }else {
            return new CommonResult(200,"数据添加成功 " + port,res);

        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment res =  paymentService.getPaymentById(id);
        log.info("结果是" +  res );
        if (res == null ){
            return new CommonResult(444,"没有结果 " + port,null);

        }else {
            return new CommonResult(200,"查询成功 " + port,res);

        }
    }

    @GetMapping("/payment/discovery/get")
    public DiscoveryClient getDiscoveryClient(){
        List<String> services = discoveryClient.getServices();
        services.forEach(x-> System.out.println(" service... "+ x));

        List<ServiceInstance> ls = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ls.stream().forEach(x-> System.out.println(" host : " + x.getHost()
            + "  service :  " + x.getServiceId() + " port "  +  x.getPort() + " uri : " + x.getUri()));
        return this.discoveryClient;
    }


    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return port;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }


}
