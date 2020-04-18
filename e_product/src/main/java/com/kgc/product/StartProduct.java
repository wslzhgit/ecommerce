package com.kgc.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 2020/3/25   11:38
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.product.*")
@EnableEurekaClient
@EnableScheduling
@MapperScan("com.kgc.product.mapper")
@ServletComponentScan("com.kgc.product.filter")
public class StartProduct {
    public static void main(String[] args) {
        SpringApplication.run(StartProduct.class);
    }
}
