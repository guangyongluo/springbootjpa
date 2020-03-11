package com.vilin.jpa;

import com.vilin.jpa.entity.Categories;
import com.vilin.jpa.entity.Items;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestManyToMany {

    private EntityManagerFactory createEntityManagerFactory;
    private EntityManager createEntityManager;
    private EntityTransaction transaction;

    @Before
    public void init() {

        createEntityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        createEntityManager = createEntityManagerFactory.createEntityManager();
        transaction = createEntityManager.getTransaction();
        transaction.begin();

    }

    @After
    public void close() {
        transaction.commit();
        createEntityManager.close();
        createEntityManagerFactory.close();

    }


    //无论是查询关联对象还是查询非关联对象,他们的sql语句都是一样的使用懒加载的方式
    @Test
    public void getManyToManyFind1() {
        Categories find = createEntityManager.find(Categories.class, 81);
        System.out.println("find》》》" + find.getName());
        System.out.println("getSetcat>>" + find.getSetitems().iterator().next().getName());
    }


    @Test
    public void getManyToManyFind() {
        Items find = createEntityManager.find(Items.class, 27);
        System.out.println("find》》》" + find.getName());
        System.out.println("getSetcat>>" + find.getSetcat().iterator().next().getName());
    }


    @Test
    public void test() {
        Items items = new Items();
        items.setName("I0");

        Items items1 = new Items();
        items1.setName("I1");

        Categories categories = new Categories();
        categories.setName("C0");

        Categories categories1 = new Categories();
        categories1.setName("C1");

        //设置关联关系
        items.getSetcat().add(categories);
        items.getSetcat().add(categories1);

        items1.getSetcat().add(categories);
        items1.getSetcat().add(categories1);


        categories.getSetitems().add(items);
        categories.getSetitems().add(items1);

        categories1.getSetitems().add(items);
        categories1.getSetitems().add(items1);


        //保存

        createEntityManager.persist(categories);
        createEntityManager.persist(categories1);

        createEntityManager.persist(items);
        createEntityManager.persist(items1);


    }

}
