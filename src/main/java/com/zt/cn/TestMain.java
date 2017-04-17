package com.zt.cn;

/**
 * Created by apple on 2017/4/16.
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import net.sf.json.JSONArray;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import javax.persistence.Id;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestMain {
    private static Session session;
    private static Transaction transaction;
    private static SessionFactory sessionFactory;
    private static TestMain testMain = new TestMain();

    public static void main(String[] arg) {
        try {

            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            MyEntity entity = new MyEntity();

//            entity.setId(1);
            entity.setName("谷歌");
            entity.setUrl("http://www.google.com.hk");
            entity.setAlexa(3);
            entity.setCountry("HK");

//            testMain.addEntity(entity);//增加一条数据
//            testMain.deleteEntity(entity,13);//删除id为13的数据
//            testMain.saveOrUpdate(entity);//保存或者增加一条数据
            testMain.queryEntity();//自定义sql语句查询
            testMain.hqlQuery();
            testMain.singalQuery();
            testMain.customQuery();


            transaction.commit();
            session.close();
            sessionFactory.close();

        } catch (Throwable th) {
            System.err.println("初始化 SessionFactory 失败！！");
            System.err.println(th);
            throw new ExceptionInInitializerError(th);
        }
    }

    /**
     * 增加一条数据
     *
     * @param entity
     */
    private void addEntity(MyEntity entity) {
        session.save(entity);
    }

    /**
     * 根据id删除一条数据
     *
     * @param entity
     * @param id
     */
    private void deleteEntity(MyEntity entity, int id) {
        entity.setId(id);
        session.delete(entity);
    }

    /**
     * 保存或者修改一条数据
     *
     * @param entity
     */
    private void saveOrUpdate(MyEntity entity) {
        session.saveOrUpdate(entity);
    }

    /**
     * 从数据库中查询出数据并转成json字符串
     */
    private void queryEntity() {
        List<MyEntity> entities = session.createCriteria(MyEntity.class).list();
        Gson gson = new Gson();
        String str = gson.toJson(entities);
        System.out.println("封装后的json数据为:" + str);
    }

    /**
     * 通过hql语句查询所有数据，并转换为json字符串
     */
    private void hqlQuery(){
        String hql="from MyEntity";
        List<MyEntity> myEntities= session.createQuery(hql).list();
        for(MyEntity entity:myEntities){
            System.out.println("name:"+entity.getName()+" url:"+entity.getUrl());
        }
        Gson gson=new Gson();
        String json=gson.toJson(myEntities);
        System.out.println("转换后的json数据为:"+json);
    }

    /**
     * 查询单个字段
     */
    private void singalQuery(){
        String hql="select url from MyEntity";
        List<String> list= session.createQuery(hql).list();
        for(String s:list){
            System.out.println(s);
        }
    }

    private void customQuery(){
        String hql="from MyEntity where name=? and url=?";
        Query query=session.createQuery(hql);
        query.setParameter(0,"百度");
        query.setParameter(1,"http://www.baidu.com");
        List<MyEntity> list=query.list();
        for (MyEntity entity:list){
            System.out.println("name:"+entity.getName()+"url:"+entity.getUrl());
        }
    }
}
