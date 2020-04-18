package com.kgc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 2020/3/9   17:03
 * Author:W.铭
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaStart2 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaStart2.class);
    }
}
