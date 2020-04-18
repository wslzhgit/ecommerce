package com.kgc.web.provider.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *   web模块的提供者2启动类
 */
@SpringBootApplication(scanBasePackages = "com.kgc.web.provider.*")
@EnableEurekaClient
@EnableDiscoveryClient   //开启提供者的客户端
public class WebProvider2Start {

    public static void main(String[] args) {
        SpringApplication.run(WebProvider2Start.class);
    }

}
