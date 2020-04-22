package com.atguigu.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @Autowired
    private SentinelService sentinelService;


    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        Thread.sleep(1000);
        log.info( Thread.currentThread().getName() + "....");
        return "----- testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----- testB";
    }
    @RequestMapping("/test3")
    public String test3() {
        int i= 10/0;
//        sentinelService.getTest();
        return "----- test3";
    }
    @RequestMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String test4(@RequestParam(value = "p1",required = false) String p1,
                        @RequestParam(value = "p2",required = false) String p2) {
//        sentinelService.getTest();
        return "----- testHotKey";
    }

    public String deal_testHotKey() {
        return "----- deal_testHotKey";
    }
}