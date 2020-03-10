package com.vilin.jpa.basic;

import com.vilin.jpa.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashMap;

public class JpaAPI {

    public static void main(String[] args) {
        //1.获取EntityManagerFactory对象
        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("hibernate.format_sql", false);
        hashMap.put("hibernate.show_sql", false);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa", hashMap);

        //2.获取EntityManager对象
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //4.进行持久化
        Employee employee = new Employee();
        employee.setName("猪八戒");
        employee.setBirth(new Date());
        employee.setDate(new Date());
        entityManager.persist(employee);
        //5.提交事务
        transaction.commit();
        //关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }
}
