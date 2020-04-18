package com.kgc.web.provider.service.impl;

import com.kgc.service.WebProviderService;
import org.springframework.stereotype.Service;

/**
 * 2020/3/24   12:19
 * Author:W.铭
 */
@Service
public class WebProviderServiceImpl implements WebProviderService {
    @Override
    public String testRibbon(String userName) throws Exception {
        System.out.println("这是provider1执行的请求。。。");
        return userName+"--provider1";
    }
}
