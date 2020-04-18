package com.kgc.register.service.impl;

import com.kgc.model.WebUsersEntity;
import com.kgc.service.WebUsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 
 * @author djin
 *    WebUsers业务层实现类
 * @date 2020-03-07 10:04:52
 */
@Service
@Transactional
public class WebUsersServiceImpl extends BaseServiceImpl<WebUsersEntity> implements WebUsersService {

    @Override
    public Map<String, Object> loginUser(WebUsersEntity user) throws Exception {
        return null;
    }
}
