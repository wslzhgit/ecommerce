package com.kgc.register;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 2020/3/9   18:06
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.register.*")
@EnableEurekaClient
@MapperScan("com.kgc.register.mapper")
public class RegisterStart {
    public static void main(String[] args) {
        SpringApplication.run(RegisterStart.class);
    }
}
