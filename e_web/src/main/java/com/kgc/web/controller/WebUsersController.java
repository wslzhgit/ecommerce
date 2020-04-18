package com.kgc.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.kgc.web.model.WebUsersEntity;
 
/**
 * 
 * @author djin
 *   WebUsers控制器
 * @date 2020-03-09 10:05:55
 */
@Controller
@RequestMapping("/webusers")
public class WebUsersController extends BaseController<WebUsersEntity>{

}
