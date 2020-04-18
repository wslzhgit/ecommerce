package com.kgc.buycar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 2020/3/30   10:44
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.buycar.*")
@EnableEurekaClient
@ServletComponentScan(basePackages = "com.kgc.buycar.filter")
@MapperScan("com.kgc.buycar.mapper")
public class StartBuyCar {
    public static void main(String[] args) {
        SpringApplication.run(StartBuyCar.class);
    }
}
