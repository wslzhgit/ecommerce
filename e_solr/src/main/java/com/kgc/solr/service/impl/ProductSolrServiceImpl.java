package com.kgc.solr.service.impl;


import com.kgc.service.ProductSolrService;
import com.kgc.solr.mapper.ProductMapper;
import com.kgc.model.ProductSolr;
import com.kgc.solr.utils.SolrUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class ProductSolrServiceImpl implements ProductSolrService {

    //依赖Mapper代理对象
    @Resource
    private ProductMapper productMapper;

    //获取solr链接对象
    private HttpSolrClient solr = SolrUtil.getSolr();

    //将mysql中的商品数据添加到solr中
    @Scheduled(cron = "0/10 * * * * ? ") // 间隔10秒执行
    @Override
    public void addDateFromMysqlToSolr() throws Exception {
        //1.先将之前的都删除掉
        solr.deleteByQuery("*:*");
        //2.查询mysql中的商品数据
        List<ProductSolr> productSolrs = productMapper.queryAll();
        //3.完成向solr中的批量添加
        UpdateResponse updateResponse = solr.addBeans(productSolrs);
        //4.提交
        solr.commit();
    }

    //根据条件加载solr引擎中的商品数据
    @Override
    public List<ProductSolr> findProductBySolr(String solrPra) throws Exception {
        System.out.println(solrPra);
        //1.新建查询的条件对象
        SolrQuery solrQuery = new SolrQuery();
        //2.设置查询的条件
        solrQuery.set("q","title:"+solrPra);
        //3.设置分页
        solrQuery.setStart(0);
        solrQuery.setRows(3);

        //执行查询
        QueryResponse queryResponse = solr.query(solrQuery);
        System.out.println(queryResponse);
        //将查询结果转为list集合
        List<ProductSolr> productSolrList = queryResponse.getBeans(ProductSolr.class);
        return productSolrList;
    }
}
