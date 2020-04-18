package com.kgc.service;

import com.kgc.model.WebUsersEntity;

import java.util.Map;

/**
 * 
 * @author djin
 *    WebUsers业务层接口
 * @date 2020-03-07 10:04:52
 */
public interface WebUsersService extends BaseService<WebUsersEntity>{

    //用户登录
    Map <String,Object> loginUser(WebUsersEntity user)throws Exception;
}
