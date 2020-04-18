package com.kgc.service;

/**
 * 2020/3/24   12:08
 * Author:W.铭
 */
public interface WebProviderService {
    //测试ribbon的负载均衡的搭建
     String testRibbon(String userName) throws Exception;
}
