package com.zt.cn;

/**
 * Created by apple on 2017/4/16.
 */

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.StandardBasicTypes;

import javax.persistence.Id;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.List;


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
//            testMain.queryEntity();//自定义sql语句查询

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

    private void queryEntity() {
        List<String> list= session.createSQLQuery(" select * from websites")
                .addScalar("name", StandardBasicTypes.STRING)//指定要查询的字段
                .list();
       for(String entity:list){
           System.out.println("结果："+entity);
       }
    }


}
