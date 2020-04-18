package com.kgc.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 2020/3/22   10:15
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.sso.*")
@EnableEurekaClient
@MapperScan("com.kgc.sso.mapper")
@ServletComponentScan(basePackages = "com.kgc.sso.filter")
public class StartSso {
    public static void main(String[] args) {
        SpringApplication.run(StartSso.class);
    }
}
