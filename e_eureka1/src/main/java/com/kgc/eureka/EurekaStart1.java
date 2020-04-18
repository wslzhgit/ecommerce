package com.kgc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 2020/3/9   16:45
 * Author:W.铭
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaStart1 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaStart1.class);
    }
}
