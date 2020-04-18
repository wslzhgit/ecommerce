package com.kgc.mongodb.test;

import com.kgc.mongodb.utils.MongoDBUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *   mongoDB数据库操作的测试类
 */
public class MongoDBTest {

    //mongoDB集合的链接对象
    private MongoCollection<Document> collection = MongoDBUtils.getCollection();

    //测试mongdb数据库的链接
    @Test
    public void test01(){
        System.out.println(collection);
    }

    //查询所有的文档数据
    @Test
    public void test02(){
        //1.执行集合对象的查询所有文档数据操作
        FindIterable<Document> documents = collection.find();
        //2.进行mongoDB中库的文档数据遍历
        //temp -> System.out.println(temp) temp为遍历的对象名称（随意取名）jdk1.8以后的语法
        documents.iterator().forEachRemaining(temp -> System.out.println(temp));
    }

    //多条件查询(查询范围price小于180或者price大于229.8并且作者名字中存在a(不区分大小写)的书籍)条件外层有and
    @Test
    public void test03(){
        //1.创建查询的条件
        //1.1定义查询条件的BSON格式字符串，此字符串就是mongodb的指令
        String praStr = "{  \n" +
                "   $and:[\n" +
                "     {$or:[  \n" +
                "        {\"price\":{$lt:180}},\n" +
                "        {\"price\":{$gt:229.8}}\n" +
                "     ]},\n" +
                "     {\"author\":{$regex:/a/i}}  \n" +
                "   ]\n" +
                "}";
        //1.2构建查询的条件
        Document praDocument = Document.parse(praStr);
        //1.3.执行条件查询(条件为BSON类型，Document类为BSON类的子类，同样可以描述BSON类型)
        FindIterable<Document> documents = collection.find(praDocument);
        //2.进行mongoDB中库的文档数据遍历
        documents.iterator().forEachRemaining(temp -> System.out.println(temp));
    }

    //根据bname和author查询
    @Test
    public void test04(){
        //1.1新建查询的条件
        Document praDocument = new Document();
        //1.2往此条件对象中拼接加入条件
        praDocument.append("bname","西游记");
        praDocument.append("author","吴承恩");
        //1.3.执行条件查询(条件为BSON类型，Document类为BSON类的子类，同样可以描述BSON类型)
        FindIterable<Document> documents = collection.find(praDocument);
        //2.进行mongoDB中库的文档数据遍历
        documents.iterator().forEachRemaining(temp -> System.out.println(temp));
    }

    //分页查询
    @Test
    public void test05(){
        //1.执行第1页的分页查询（从第几条数据下标开始（第1条下标为0），每一页查询的数据条数）
        FindIterable<Document> documents = collection.find().skip((1 - 1) * 4).limit(4);
        //2.进行mongoDB中库的文档数据遍历
        documents.iterator().forEachRemaining(temp -> System.out.println(temp));
    }

    //根据bname和author查询,进行分页
    @Test
    public void test06(){
        //1.1新建查询的条件
        Document praDocument = new Document();
        //1.2往此条件对象中拼接加入条件
       // praDocument.append("bname","西游记");
        praDocument.append("author","路遥");
        //2.执行第1页的分页查询（从第几条数据下标开始（第1条下标为0），每一页查询的数据条数）
        FindIterable<Document> documents = collection.find(praDocument).skip((1 - 1) * 5).limit(5);
        //3.进行mongoDB中库的文档数据遍历
        documents.iterator().forEachRemaining(temp -> System.out.println(temp));
    }

    //多条件查询(查询范围price小于180或者price大于229.8并且作者名字中存在a(不区分大小写)的书籍)条件外层有and
    @Test  //推荐使用此种方式进行条件查询
    public void test07(){
        //1.构建左边的价格条件
        //1.1.构建price小于180的条件
        Bson ltPrice = Filters.lt("price", 180);
        //1.2.构建price大于229.8的条件
        Bson gtPrice = Filters.gt("price", 229.8);
        //1.3.构建price小于180或者大于229.8的条件
        Bson price = Filters.or(ltPrice, gtPrice);
        //2.构建右边的作者模糊查询条件
        //2.1.创建模糊查询条件Pattern.CASE_INSENSITIVE:表示查询的字符在任意位置，且不区分大小写
        Pattern pattern = Pattern.compile("a", Pattern.CASE_INSENSITIVE);
        //2.2.构建根据字符‘a’模糊查询查询作者条件，在任意位置且不区分大小写
        Bson author = Filters.regex("author", pattern);
        //3.将这左右两边的条件整到一块
        //查询范围price小于180或者price大于229.8并且作者名字中存在a(不区分大小写)的书籍
        Bson praBson = Filters.and(price, author);
        //4.执行上述条件查询
        FindIterable<Document> documents = collection.find(praBson);
        //5.进行mongoDB中库的文档数据遍历
        documents.iterator().forEachRemaining(temp -> System.out.println(temp));
    }
    //添加单个书籍
    @Test
    public void test08() {
        //构建要被添加的文档对象
        Document document = Document.parse("{'bname':'平凡的世界','author':'路遥','price':199.0,'count':900}");
        //执行添加
        collection.insertOne(document);
    }

    //添加单个书籍数据，拼接添加的文档对象数据
    @Test
    public void test09(){
        //新建一个添加的文档对象
        Document document = new Document();
        //拼接要添加的文档数据
        document.append("bname","天龙八部");
        document.append("author","金庸");
        document.append("price",209.90);
        document.append("counts",899);
        //执行添加
        collection.insertOne(document);
    }

    //批量添加数据
    @Test
    public void test10(){
        //构建要被添加的文档对象1
        Document document1 = Document.parse("{'bname':'平凡的世界1','author':'路遥','price':299.0,'count':900}");
        //构建要被添加的文档对象1
        Document document2 = Document.parse("{'bname':'平凡的世界2','author':'路遥','price':399.0,'count':900}");
        //构建要被添加的文档对象1
        Document document3 = Document.parse("{'bname':'平凡的世界3','author':'路遥','price':499.0,'count':900}");
        //执行批量添加
        collection.insertMany(Arrays.asList(document1,document2,document3));
    }

    //循环往mongoDB中添加100000条文档数据
    @Test
    public void test11(){
        //得到循环开始时间
        long startTime = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            //新建添加的文档对象
            Document document = new Document();
            document.append("bname","平凡的世界123"+i);
            document.append("author","路遥");
            document.append("price",123.99);
            document.append("counts",100);
            document.append("type","文学类");
            document.append("createDate",new Date());
            document.append("sales",190);
            document.append("num",1800);
            collection.insertOne(document);
        }
        //得到循环结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("共消耗"+(endTime-startTime)/1000+"s");//共消耗23s
    }

    //根据库存量为100的条件进行数据文档删除（删除100000条文档数据）
    @Test
    public void test12(){
        //得到循环开始时间
        long startTime = System.currentTimeMillis();
        //新建删除的条件
        Document praDoument = new Document();
        //拼接删除的条件
        praDoument.append("counts",100);
        //执行删除
        DeleteResult deleteResult = collection.deleteMany(praDoument);
        System.out.println("共删除了"+deleteResult.getDeletedCount()+"数据");  //100000条
        //得到结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("共消耗"+(endTime-startTime)/1000+"s");//共消耗3s
    }

    //用Bson构建删除的条件完成多条件删除（根据书名和作者）
    @Test
    public void test13(){
        //构建书名的条件
       // Bson bname = Filters.in("bname", "西游记");
        //构建作者条件
        Bson author = Filters.in("author", "路遥");
        //将两个条件结合
        Bson praBson = Filters.and( author);
        //执行多条件的单个删除
        //DeleteResult deleteResultOne = collection.deleteOne(praBson);  //最多只删除符合条件的第1条数据
         //System.out.println("删除的数据条数"+deleteResultOne.getDeletedCount()+"条");
        //执行多条数据的删除
        DeleteResult deleteResultMany = collection.deleteMany(praBson);  //将所有符合条件的数据均删除
        System.out.println("删除的数据条数"+deleteResultMany.getDeletedCount()+"条");
    }

    //测试修改100000条数据，单条件修改
    @Test
    public void test14(){
        //得到循环开始时间
        long startTime = System.currentTimeMillis();
        //新建修改的条件
        Bson praBson = Filters.in("counts",100);
        //新建修改的内容
        Document updDocument = Document.parse("{$set:{\"sales\":180}}");
        //执行修改多条数据  修改的条件  修改的内容
        UpdateResult updateResultMany = collection.updateMany(praBson, updDocument);
        System.out.println("修改的数据条数为"+updateResultMany.getMatchedCount()+"条");//100000条
        //得到结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("共消耗"+(endTime-startTime)/1000+"s");//共消耗5s
    }

    //多条件修改
    @Test
    public void test15(){
        //新建根据书名的修改的条件
        Bson bname = Filters.in("bname","三国演义");
        //新建根据作者的修改条件
        Bson author = Filters.in("author", "罗贯中");
        //将修改的条件拼接一起
        Bson praBson = Filters.and(bname, author);
        //新建修改的内容
        Document updDocument = Document.parse("{$set:{\"price\":276.90}}");
        //执行单个修改
        //  UpdateResult updateResultOne = collection.updateOne(praBson, updDocument);  //最多修改符合条件的第1条数据
        //  System.out.println("修改了"+updateResultOne.getMatchedCount()+"条数据");  //修改了1数据
        UpdateResult updateResultMany = collection.updateMany(praBson, updDocument);  //可以将符合条件的数据全部修改
        System.out.println("修改了"+updateResultMany.getMatchedCount()+"数据"); //修改了5数据
    }





}
