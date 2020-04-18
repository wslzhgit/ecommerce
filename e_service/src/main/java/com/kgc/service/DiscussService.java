package com.kgc.service;




import org.bson.Document;

import java.util.List;

/**
 * 2020/3/29   9:44
 * Author:W.铭
 */
public interface DiscussService {
    //评论的分页查询
    List<Document> findPageDiscuss(Integer page, Integer limit) throws Exception;
}
