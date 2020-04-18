package com.kgc.product.service.impl;

import com.kgc.product.utils.MongoDBUtils;
import com.kgc.service.DiscussService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * 2020/3/29   9:54
 * Author:W.铭
 */
@Service
@Transactional(readOnly = false)
public class DiscussServiceImpl implements DiscussService {
    //mongoDB集合的链接对象
    private MongoCollection<Document> collection = MongoDBUtils.getCollection();
    @Override
    public List<Document> findPageDiscuss(Integer page, Integer limit) throws Exception {
        //1.执行第1页的分页查询（从第几条数据下标开始（第1条下标为0），每一页查询的数据条数）
        FindIterable<Document> documents = collection.find().skip((page - 1) * limit).limit(limit);
        //2.新建评论的list集合（因为springMVC框架不能直接将 FindIterable<Document> 转为JSON数据）
        List<Document> list = new ArrayList<Document>();
        //2.通过将mongoDB中库的文档数据循环加入到评论的list集合中
        documents.iterator().forEachRemaining(temp -> list.add(temp));
        return list;
    }
}
