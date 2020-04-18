package com.kgc.admin.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *   页面跳转的控制器
 */
@Controller
@RequestMapping("/model")
public class ModelController {

    //后台管理平台页面
    @RequestMapping("/toIndex")
    public String toIndex(){
        System.out.println("toIndex..");
        return "index";
    }
    //后台用户管理页面
    @RequestMapping("/toAdminUsers")
    public String toAdminUsers(){
        return "adminusers";
    }
    //后台菜单管理页面
    @RequestMapping("/toAdminMenus")
    public String toAdminMenus(){
        return "adminmenus";
    }
    //前台菜单导航管理页面
    @RequestMapping("/toWebMenu")
    public String toWebMenu(){
        return "webmenu";
    }
}
