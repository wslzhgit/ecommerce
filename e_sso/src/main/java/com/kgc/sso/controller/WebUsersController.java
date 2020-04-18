package com.kgc.sso.controller;

import com.kgc.model.WebUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author
 *   WebUsers控制器
 * @date
 */
@Controller
@RequestMapping("/webusers")
public class  WebUsersController extends BaseController<WebUsersEntity>{
    @Autowired
    private RedisTemplate redisTemplate;
    //登录
    @RequestMapping("/loginUser")
    public @ResponseBody Map<String, Object> loginUser(WebUsersEntity user, HttpServletResponse response) {
        Map<String, Object>  map = new HashMap<String, Object>();
        try {
          map = webUsersService.loginUser(user);
           Integer code = (Integer) map.get("code");
            if (code==0) {
                String token = (String) map.get("token");
                Cookie cookie = new Cookie("token",token);
                //设置cookie的路径
                cookie.setPath("/webusers");
                //设置cookie的有效时间
                cookie.setMaxAge(20*60);
                //将cookie存放在客户端
                response.addCookie(cookie);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    //取到登陆模块的令牌
    @RequestMapping("/getToken")
    public @ResponseBody String getToken(HttpServletRequest request){
        //定义令牌
        String token = null;
        //得到客户端请求中的所有的cookie数组
        Cookie[] cookies = request.getCookies();
        //判断数组是否为空
        if(cookies!=null){
            //定义token的cookie
            Cookie cookieToken = null;
            //通过循环找token的cookie
            for (Cookie cookie:cookies) {
                if("token".equals(cookie.getName())){
                    //找到存在名字为token的cookie
                    cookieToken = cookie;
                    break;
                }
            }
            //判断token的cookie存在
            if(cookieToken!=null){
                //将值token取出
                token = cookieToken.getValue();
            }
        }
        return token;
    }

    //根据登陆的令牌取到redis中的数据
    @RequestMapping("/getRedisLoginUser")
    public @ResponseBody WebUsersEntity getRedisLoginUser(String token) {
        ValueOperations vop = redisTemplate.opsForValue();
        WebUsersEntity loginUser = null;
        loginUser = (WebUsersEntity) vop.get("sessionId" + token);
        return loginUser;
    }

    //注销
    @RequestMapping("exitUser")
    public @ResponseBody
    Boolean exitUser(HttpServletResponse response, HttpServletRequest request) {
        Boolean delRedis = false;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie cookieToken = null;
            for (Cookie cookie:cookies) {
                if("token".equals(cookie.getName())){
                    //找到存在名字为token的cookie
                    cookieToken = cookie;
                    break;
                }
        }
            //判断token的cookie存在
            if(cookieToken!=null){
                //将值token取出
                token = cookieToken.getValue();
                //删除redis中的用户数据
                //获取redis中操作字符串的模板
                delRedis = redisTemplate.delete("sessionId" + token);

                // 清除有token的cookie
                Cookie cookie = new Cookie("token","");
                cookie.setPath("/webusers");
                cookie.setMaxAge(0);  //有效时间设置为0
                response.addCookie(cookie);
            }
        }
        return delRedis;
    }
}
