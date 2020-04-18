package com.kgc.solr.mapper;

import com.kgc.model.ProductSolr;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 *   商品详情Mapper代理对象
 */
public interface ProductMapper {

    //查询所有的商品详情数据
    @Select("SELECT id as pid ,title,price,avatorImg from web_product_detail")
    List<ProductSolr> queryAll() throws Exception;
}
