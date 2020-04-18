package com.kgc.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 2020/3/9   18:06
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.admin.*")
@EnableEurekaClient
@MapperScan("com.kgc.admin.mapper")
@ServletComponentScan(basePackages = "com.kgc.admin.filter")  //扫描到过滤器的包
public class AdminStart {
    public static void main(String[] args) {
        SpringApplication.run(AdminStart.class);
    }
}
