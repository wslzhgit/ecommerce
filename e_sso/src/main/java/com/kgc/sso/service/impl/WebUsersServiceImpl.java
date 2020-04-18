package com.kgc.sso.service.impl;

import com.kgc.model.WebUsersEntity;
import com.kgc.service.WebUsersService;
import com.kgc.sso.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author djin
 *    WebUsers业务层实现类
 * @date 2020-03-07 10:04:52
 */
@Service
@Transactional
public class WebUsersServiceImpl extends BaseServiceImpl<WebUsersEntity> implements WebUsersService {
    @Autowired
    private RedisTemplate redisTemplate;
	//登录
    @Override
    public Map<String, Object> loginUser(WebUsersEntity user) throws Exception {
        Map<String, Object> map = new HashMap<>();
        user.setPwd(MD5.md5crypt(user.getPwd()));//登陆前将密码加密
        WebUsersEntity loginUser = baseMapper.queryObjectByPramas(user);
        //判断登录是否成功
        if(user!=null){
            map.put("code",0);
            map.put("loginUser",loginUser);
            String token = UUID.randomUUID().toString();
            map.put("token",token);
            //往redis中装入用户数据
            ValueOperations vop = redisTemplate.opsForValue();
            //往redis中存放用户数据，设置20分钟有效
            vop.set("sessionId"+token,loginUser,20, TimeUnit.MINUTES);
        }else {
            map.put("code",200);
        }
        return map;
    }
}
