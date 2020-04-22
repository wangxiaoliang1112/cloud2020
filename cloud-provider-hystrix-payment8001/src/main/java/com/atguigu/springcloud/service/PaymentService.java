package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String payment_info(Integer id){
        return "线程池 == " + Thread.currentThread().getName() + " ok haha " ;
    }


    // 服务降级 当代码执行超时或者抛出异常直接进入备用方法
    @HystrixCommand(fallbackMethod = "payment_timeoutHandler",commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,value = "3000")
    })
    public String payment_timeout(Integer id){

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池 == " + Thread.currentThread().getName() + " timeout  haha hahha  " ;
    }

    public String payment_timeoutHandler(Integer id){
        return "服务降级  线程池 == " + Thread.currentThread().getName() + " timeout  wu wu  wu "  + id  ;
    }



    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 失败率多少跳匝
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0 ){
            throw  new RuntimeException(" id not ----");
        }
        String str = IdUtil.simpleUUID();
        return id + "    "  + Thread.currentThread().getName() + " 调用成功  " + str ;

    }

    public String paymentCircuitBreakerFallback(Integer id){

        return id +  " ID  不能是负数 " ;

    }
}
