package com.kgc.service;

import com.kgc.model.WebOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author djin
 *    WebOrder业务层接口
 * @date 2020-03-09 10:05:59
 */
public interface WebOrderService extends BaseService<WebOrderEntity>{

	//监听mysql订单表
    void listenerOrder() throws Exception;
    //加载登陆用户的订单数据
  //  List<WebOrderEntity> findOrdersByUid (String token) throws Exception;
}
