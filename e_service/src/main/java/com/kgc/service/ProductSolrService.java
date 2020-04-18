package com.kgc.service;

import com.kgc.model.ProductSolr;

import java.util.List;

/**
 *   商品详情solr的业务层接口
 */
public interface ProductSolrService {

    //将mysql中的商品数据添加到solr中
    void addDateFromMysqlToSolr() throws Exception;

    //根据条件加载solr引擎中的商品数据
    List<ProductSolr> findProductBySolr(String solrPra)throws Exception;
}
