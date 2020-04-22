package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

//  自定义 LoadBalancer接口
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);


}
