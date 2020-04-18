package com.kgc.solr.test;

import com.kgc.solr.model.Student;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 2020/4/14   17:22
 * Author:W.铭
 */
public class SolrTest {
    /*添加索引*/
    //定义solr的链接对象
    private HttpSolrClient solr = null;

    @Before
    public void before(){
        //1.定义solr的服务器链接路径
        String solrUrl="http://localhost:8888/solr/new_core";
        //2.创建solr的链接对象
        solr = new HttpSolrClient.Builder(solrUrl).
                withConnectionTimeout(10000).
                withSocketTimeout(60000).build();
    }

    //测试solr的添加
    @Test
    public void test01(){
        //1.使用httpSolrlient对象操作索引库
        //SolrInputDocument对应一行
        SolrInputDocument document=new SolrInputDocument();
        document.addField("xh",103);
        document.addField("age",25);
        document.addField("name","张三");
        document.addField("sex","男");
        try {
            //执行添加
            UpdateResponse updateResponse = solr.add(document);
            //提交
            solr.commit();
            System.out.println("被添加的状态："+updateResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败！！");
        }
    }
    //测试根据id进行删除
    @Test
    public void test02(){
        try {
            //执行删除操作
            UpdateResponse updateResponse = solr.deleteById("30d71fb9-0a3f-4496-8f2c-e198b76620d3");
            //提交
            solr.commit();
            System.out.println("被删除的状态："+updateResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败！！");
        }
    }

    //根据条件模糊删除
    @Test
    public void test03(){
        try {
            //执行条件删除
            UpdateResponse updateResponse = solr.deleteByQuery("name:张");
            //提交
            solr.commit();
            System.out.println("被删除的结果："+updateResponse.toString());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败！！");
        }
    }

    //修改，根据索引id来进行修改,索引值id不变
    @Test
    public void test04(){
        //SolrInputDocument对应一行
        SolrInputDocument document=new SolrInputDocument();
        document.addField("xh",107);
        document.addField("age",15);
        document.addField("name","张三");
        document.addField("sex","女");
        document.addField("id","3d4fd8af-4a63-4b13-8e47-8f1d985d6497");
        try {
            //执行添加
            UpdateResponse updateResponse = solr.add(document);
            //提交
            solr.commit();
            System.out.println("执行修改的结果："+updateResponse);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("修改失败！！");
        }
    }

    //修改：先删除，再添加，索引id会变化
    @Test
    public void test05(){
        try {
            //1.进行删除
            solr.deleteByQuery("xh:107");
            //2.进行添加
            //SolrInputDocument对应一行
            SolrInputDocument document=new SolrInputDocument();
            document.addField("xh",107);
            document.addField("age",18);
            document.addField("name","张三");
            document.addField("sex","男");
            UpdateResponse updateResponse = solr.add(document);
            solr.commit();
            System.out.println("修改的结果："+updateResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("修改失败！！");
        }
    }

    //通过实体封装类的方式添加solr中的数据
   @Test
    public void test06(){
        //新建实体封装类对象
        Student student = new Student(103,"王灵",24,"女");
        try {
            //执行实体封装类对象的添加
            UpdateResponse updateResponse = solr.addBean(student);
            //提交
            solr.commit();
            System.out.println("添加的结果："+updateResponse.toString());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败！！");
        }
    }

    //实体封装类方式完成solr引擎的批量添加
    @Test
    public void test07(){
        //新建要添加的4个对象
        Student student1 = new Student(104,"王建林",64,"男");
        Student student2 = new Student(105,"王五",23,"女");
        Student student3 = new Student(106,"刘林",35,"男");
        Student student4 = new Student(109,"李建",27,"男");
        //创建list集合对象
        List<Student> students = Arrays.asList(student1,student2,student3,student4);
        try {
            //执行批量添加
            UpdateResponse updateResponse = solr.addBeans(students);
            //提交
            solr.commit();
            System.out.println("批量添加数据的结果："+updateResponse.toString());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("批量添加失败！！");
        }
    }

    //查询
    @Test
    public void test08(){
        //1.新建查询的条件对象
        SolrQuery solrQuery = new SolrQuery();
        //2.设置查询的条件
        solrQuery.set("q","*:*");
        //3.设置分页
        solrQuery.setStart(0);
        solrQuery.setRows(3);
        try {
            //执行查询
            QueryResponse queryResponse = solr.query(solrQuery);
            System.out.println(queryResponse);
            //将查询结果转为list集合
            List<Student> students = queryResponse.getBeans(Student.class);
            //循环遍历
            for (Student student:students) {
                System.out.println(student);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
