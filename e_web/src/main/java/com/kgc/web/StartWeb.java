package com.kgc.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 2020/3/12   11:17
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.web.*")
@EnableEurekaClient
@MapperScan("com.kgc.web.mapper")
@ServletComponentScan(basePackages = "com.kgc.web.filter")  //扫描到过滤器的包
public class StartWeb {
    public static void main(String[] args) {
        SpringApplication.run(StartWeb.class);
    }
}
