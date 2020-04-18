package com.kgc.product.mapper;

import com.kgc.model.WebProductImgEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author djin
 *    WebProductImgMapper层
 * @date 2020-03-09 10:05:59
 */
@Repository
public interface WebProductImgMapper extends BaseMapper<WebProductImgEntity> {
    //根据商品id查询多个商品图片数据
    List<WebProductImgEntity> queryWebProductImgByProId(Integer productId) throws Exception;
	
}
