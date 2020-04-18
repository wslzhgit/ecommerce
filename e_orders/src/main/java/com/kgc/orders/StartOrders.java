package com.kgc.orders;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 2020/4/8   12:15
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.orders.*")
@EnableEurekaClient  //启用注册中心客户端
@EnableScheduling  //开启任务调度
@MapperScan("com.kgc.orders.mapper")
public class StartOrders {
    public static void main(String[] args) {
        SpringApplication.run(StartOrders.class);
    }
}
