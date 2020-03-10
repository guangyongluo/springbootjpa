package com.vilin.jpa;

import com.vilin.jpa.entity.Employee;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class TestPerson {

    @Test
    public void test() {
        //1.获取EntityManagerFactory 对象
        EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		
/*		  HashMap<String, Object> hashMap = new HashMap<>();
		  hashMap.put("hibernate.format_sql", false);
	  hashMap.put("hibernate.show_sql", false);
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("jpa", hashMap);
	*/    //2.获取EntityManager 对象
        EntityManager createEntityManager = createEntityManagerFactory.createEntityManager();
        //3.开启事务
        EntityTransaction transaction = createEntityManager.getTransaction();
        transaction.begin();

        System.out.println(createEntityManager.isOpen());
        //4.进行持久化
        Employee employee = new Employee();
        employee.setName("猪八戒");
        employee.setBirth(new Date());
        employee.setDate(new Date());
        createEntityManager.persist(employee);
        //5.提交事务
        transaction.commit();
        //关闭资源
        createEntityManager.close();
        createEntityManagerFactory.close();

    }


}
