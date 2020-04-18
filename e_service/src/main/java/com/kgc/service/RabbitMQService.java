package com.kgc.service;

/**
 * 2020/4/7   18:50
 * Author:W.铭
 */
public interface RabbitMQService {
    //将秒杀数据装入到消息队列中
    void addRabbitMQToExCFormSeckill(Long secId, Long proId,Float secPrice, Integer uid) throws Exception;
    //将秒杀数据装入到消息队列中
    void addRabbitMQToExCFormBuyCar(String proIds,Float zPrice, Integer uid) throws Exception;
}
