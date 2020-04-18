package com.kgc.service;

import com.kgc.model.WebProductDetailEntity;

/**
 * 
 * @author
 *    WebProductDetail业务层接口
 * @date
 */
public interface WebProductDetailService extends BaseService<WebProductDetailEntity>{
    //根据freemarker模板生成商品详情的静态页面
    void makeProductDetail() throws Exception;
	
	
}
