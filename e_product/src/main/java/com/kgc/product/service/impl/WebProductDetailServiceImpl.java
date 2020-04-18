package com.kgc.product.service.impl;

import com.kgc.model.WebProductDetailEntity;
import com.kgc.service.WebProductDetailService;
import freemarker.template.Configuration;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author djin
 *    WebProductDetail业务层实现类
 * @date 2020-03-09 10:05:59
 */
@Service
@Transactional
public class WebProductDetailServiceImpl extends BaseServiceImpl<WebProductDetailEntity> implements WebProductDetailService {
    //引入FreeMarker模板对象
    @Autowired
    private Configuration configuration;

    //每隔30s钟执行
    @Scheduled(cron = "0/30 * * * * ?")
    @Override
    public void makeProductDetail() throws Exception {
        //定义输入流对象
        FileWriter fw = null;
        //查询获取所有的商品详情及其详情图片数据
        List<WebProductDetailEntity> productDetails = baseMapper.queryAll();
        //定义生成文件的文件夹
        File file = new File("E:\\product");
        //3.判断文件夹是否存在
        if(!file.exists()){ //不存在
            file.mkdirs();  //则创建
        }
        //4.通过循环遍历商品详情数据生成商品静态页面
        for (WebProductDetailEntity productDetail:productDetails) {
            //定义生成静态页面的目标文件夹路径
            String filePath = "E:\\product\\"+productDetail.getId()+".html";
            //通过文件路径得到目标文件,此时的目标文件是空的
            File newFile = new File(filePath);
            //得到目标的输入流对象
            fw = new FileWriter(newFile);
            //根据ftl模板得到生成静态页面的模板对象
            Template template = configuration.getTemplate("product.ftl");
            //通过目标文件的输入流对象和数据生成静态文件
            template.process(productDetail,fw);
            fw.close();  //关闭资源
        }

    }
    /**
     *   每隔2s钟执行
     */
    // @Scheduled(cron = "0/2 * * * * ?")
    public void testTask01(){
        System.out.println(new Date()+"执行了testTask01方法.+++++++++++。");
    }

    /**
     *   每隔5s钟执行
     */
     // @Scheduled(cron = "0/5 * * * * ?")
    public void testTask02(){
        System.out.println(new Date()+"执行testTask02方法！-----！");
    }

    /**
     *   定时的每分钟第15s，30s，45s执行
     */
     //@Scheduled(cron = "15,30,45 * * * * ?")
    public void testTask03(){
        System.out.println(new Date()+"执行testTask03方法！！..........。");
    }
}
