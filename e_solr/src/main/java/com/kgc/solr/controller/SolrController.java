package com.kgc.solr.controller;

import com.kgc.service.ProductSolrService;
import com.kgc.model.ProductSolr;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 2020/4/16   9:32
 * Author:W.铭
 */
@Controller
@RequestMapping("/solr")
public class SolrController {
    //依赖业务层对象
    @Autowired
    private ProductSolrService productSolrService;
    //将mysql商品详情数据导入到solr中
   /* @RequestMapping("/improt")
    public @ResponseBody String improt(){
        try {
            productSolrService.addDateFromMysqlToSolr();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }*/

   //根据条件加载solr引擎中的商品数据
   @RequestMapping("/getProductBySolr")
   public @ResponseBody List<ProductSolr> getProductBySolr(String solrPra){

       try {
           return productSolrService.findProductBySolr(solrPra);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   };
}
