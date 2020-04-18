package com.kgc.solr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 2020/4/15   10:12
 * Author:W.铭
 */
@SpringBootApplication(scanBasePackages = "com.kgc.solr.*")
@EnableScheduling
@EnableEurekaClient
@MapperScan("com.kgc.solr.mapper")
public class StartSolr {
    public static void main(String[] args) {
        SpringApplication.run(StartSolr.class);
    }
}
