package com.kgc.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 2020/4/3   10:59
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.seckill.*")
@EnableEurekaClient  //启用注册中心客户端
@EnableCaching //开启缓存
@EnableScheduling  //开启任务调度
@MapperScan("com.kgc.seckill.mapper")
public class StartSeckill {
    public static void main(String[] args) {
        SpringApplication.run(StartSeckill.class);
    }
}
