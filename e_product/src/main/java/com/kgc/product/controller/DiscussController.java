package com.kgc.product.controller;

import com.kgc.service.DiscussService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 *   商品详情评论的控制器
 */
@Controller
@RequestMapping("/discuss")
public class DiscussController {

    //评论的业务层对象
    @Autowired
    private DiscussService discussService;

    //分页加载商品详情数据
    @RequestMapping("/loadPageDiscuss")
    public @ResponseBody List<Document> loadPageDiscuss(Integer page, Integer limit){
        try {
            return discussService.findPageDiscuss(page,limit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
