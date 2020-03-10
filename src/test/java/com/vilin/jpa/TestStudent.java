package com.vilin.jpa;

import com.vilin.jpa.entity.Employee;
import com.vilin.jpa.entity.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.Date;

public class TestStudent {

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

    @Test
    public void getTestPersist() {
        Student student = new Student();
        student.setName("猪八戒?:");
        student.setMail("zhubj@.COM");
        student.setBirth(new Date());
        createEntityManager.persist(student);
        System.out.println("isOpen" + createEntityManager.isOpen());
        System.out.println(student.getId());
    }

    /**
     * 游离对象 第三种
     * 如果在EntityManager 的缓存中有该对象
     * JPA 会把游离对象复制到缓存对象中
     * 在对缓存中的对象执行update
     * 注意:hibernate Session中的saveOrUpdate的方法不同,不能两个ID同时关联
     */
    @Test
    public void getMerage4() {
        Student student = new Student();
        student.setBirth(new Date());
        student.setMail("qwe@QQ.com");
        student.setName("太白");
        //id 6数据表有该id
        student.setId(6);

        Student find = createEntityManager.find(Student.class, 6);

        Student merge = createEntityManager.merge(student);
        System.out.println("ID》》" + merge.getId());
        System.out.println(find == merge);//true
    }

    @Test
    public void getContains() {
        Student find = createEntityManager.find(Student.class, 6);
        boolean contains = createEntityManager.contains(new Employee());
        System.out.println("contains>" + contains);

    }

    @Test
    public void getRefresh() {
        Student find = createEntityManager.find(Student.class, 6);
        find = createEntityManager.find(Student.class, 6);
        //createEntityManager.refresh(find);
    }


    //将持久上下文所有未保存实体的状态信息保存到数据表中
    @Test
    public void getFlush() {
        createEntityManager.setFlushMode(FlushModeType.COMMIT);
        Student find = createEntityManager.find(Student.class, 6);
        System.out.println("find》》" + find);
        find.setName("猴子");
        Student find2 = createEntityManager.find(Student.class, 6);
        System.out.println("find2》》" + find2);
        createEntityManager.flush();
        FlushModeType flushMode = createEntityManager.getFlushMode();
        //FlushModeType.COMMIT 提交事务时才更新数据
        //FlushModeType.AUTO  自动更新

        System.out.println("flushMode》》" + flushMode);

    }


    /**
     * 游离对象的  第二种
     * 如在EntityManager的缓存中没有该对象
     * 但是在数据表中有对应ID的数据
     * JPA会查询对应ID的数据,然后把游离的对象复制到查询的对象中
     * 在对查询的对象进行update操作
     */
    @Test
    public void getMerage3() {
        Student student = new Student();
        student.setBirth(new Date());
        student.setMail("SD@QQ.com");
        student.setName("李天王");
        //id 6数据表有该id
        student.setId(6);

        Student merge = createEntityManager.merge(student);
        System.out.println("ID》》" + merge.getId());
        System.out.println(student == merge);//false
    }


    /**
     * 游离对象(有ID) 第一种
     * 如果EntityManager 的缓存中没有该对象
     * 如数据表中也没有对应的数据
     * JPA会创建一个新对象,然后把游离对象中的属性复制到新对象中(处理ID以外的属性，ID按照声明中的方式生成)
     * ,再对新对象执行insert操作
     */
    @Test
    public void getMerage2() {
        Student student = new Student();

        student.setBirth(new Date());
        student.setMail("SD@QQ.com");
        student.setName("哪吒");
        //id 60数据表没有该id
        student.setId(60);

        Student merge = createEntityManager.merge(student);
        System.out.println("ID》》" + student.getId());
        System.out.println("ID》》" + merge.getId());
    }


    /**
     * 临时对象(没有ID)
     * 会创建一个新对象.把临时对象中的属性复制到新对象中,再对新对象进行持久化操作
     */
    @Test
    public void getMerge1() {
        Student student = new Student();
        student.setBirth(new Date());
        student.setName("唐僧");
        student.setMail("qa@qq.com");
        Student merge = createEntityManager.merge(student);
        System.out.println(student.getId());
        System.out.println(merge.getId());

    }


    @Test
    public void getFind() {
        Student find = createEntityManager.find(Student.class, 2);
        System.out.println("=====================");
        System.out.println("find>>" + find);

    }

    @Test
    public void getReference() {
        Student reference = createEntityManager.getReference(Student.class, 3);
        /**
         *  Hibernate session 中的load 方法
         *  这个方法是返回的代理对象
         *  当你需要的时候,才返回数据
         * */
        System.out.println("=====================");
        System.out.println("find>>" + reference);
    }

    @Test
    public void getRemove() {

        Student find = createEntityManager.find(Student.class, 2);
        createEntityManager.remove(find);

    }

}
