package com.kgc.web.controller;

import com.kgc.web.model.WebSortEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 2020/3/14   17:23
 * Author:W.铭
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @RequestMapping("/show1")
    public ModelAndView show1(ModelAndView modelAndView){
        //设置跳转的视图
        modelAndView.setViewName("show");
        //设置数据
        modelAndView.addObject("welcomewords","hello world!!!");
        return modelAndView;
    }

    @RequestMapping("/show2")
    public String show2(Model model){
        //设置数据
        model.addAttribute("welcomewords","你好啊Model");
        return "show";
    }
    @RequestMapping("/show3")
    public String show3(Map<String,Object> map){
        //设置数据
        map.put("welcomewords","你好啊Map");
        return "show";
    }
    @RequestMapping("/show4")
    public String show4(HttpServletRequest request){
        //设置数据
        request.setAttribute("welcomewords","你好啊request\\n大家好！");
        request.setAttribute("welcomeword","你好啊request\n大家好！");
        request.setAttribute("url","https://www.baidu.com");
        return "show";
    }

    @RequestMapping("show5")
    public String show5(HttpServletRequest request){
        WebSortEntity sortEntity = new WebSortEntity();
        sortEntity.setId(1001l);
        sortEntity.setSortname("汽车");
        sortEntity.setUpdatetime(new Date());
        request.setAttribute("sort",sortEntity);
        return "show1";
    }

    @RequestMapping("show6")
    public String show6(HttpServletRequest request){
        /************解析set和list集合************/
        Set<String> allNames = new HashSet<String>() ;
        List<Integer> allIds = new ArrayList<Integer>() ;
        for (int x = 0 ; x < 5 ; x ++) {
            allNames.add("boot-" + x) ;
            allIds.add(x) ;
        }
        request.setAttribute("names", allNames); ;
        request.setAttribute("ids", allIds); ;
        return "show1";
    }

    @RequestMapping("/show7")
    public String show7(HttpServletRequest request){
        request.setAttribute("requestMessage", "springboot-request");
        request.getSession().setAttribute("sessionMessage", "springboot-session");
        request.getServletContext().setAttribute("applicationMessage",
                "springboot-application");
        request.setAttribute("url", "www.baidu.cn");
        request.setAttribute("url2",
                "<span style='color:red'>www.baidu.cn</span>");
        return "show1";
    }


}
