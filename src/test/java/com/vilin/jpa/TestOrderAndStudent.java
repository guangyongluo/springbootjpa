package com.vilin.jpa;

import com.vilin.jpa.entity.Order;
import com.vilin.jpa.entity.Person;
import com.vilin.jpa.entity.Student;
import org.hibernate.jpa.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class TestOrderAndStudent {
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
    public void getUpAndDel1() {
        //String  Jpql="update Order O SET O.name=? where O.id=?";
        String jpql = "DELETE Order O WHERE O.id=?";
        createEntityManager.createQuery(jpql).setParameter(1, 37).executeUpdate();
    }

    @Test
    public void getUpAndDel() {
        String Jpql = "update Order O SET O.name=? where O.id=?";
        createEntityManager.createQuery(Jpql).setParameter(1, "孙悟空").setParameter(2, 36).executeUpdate();
    }

    @Test
    public void getSubOrder1() {
        String jpql = "SELECT substring(O.name,2,3) FROM Order O ";
        List resultList = createEntityManager.createQuery(jpql).getResultList();
        System.out.println("resultList>>" + resultList.size());
        System.out.println("resultList>>" + resultList);

    }


    @Test
    public void getSubOrder() {
        String jpql = "FROM Order O where O.student =(SELECT S FROM Student S WHERE S.id=?)";
        List resultList = createEntityManager.createQuery(jpql).setParameter(1, 44).getResultList();
        System.out.println("resultList>>" + resultList.size());
        System.out.println("resultList>>" + resultList);

    }

    @Test
    public void getJpqlJoinFetch() {
        String jpql = "FROM Order O Left OUTER Join FETCH  O.student WHERE O.id=?";
        List resultList = createEntityManager.createQuery(jpql).setParameter(1, 46).getResultList();
        System.out.println("resultList>" + resultList);
    }


    @Test
    public void getJpqlJoin() {
        String jpql = "FROM Order O Left OUTER Join O.student WHERE O.id=?";
        List resultList = createEntityManager.createQuery(jpql).setParameter(1, 46).getResultList();
        System.out.println("resultList>" + resultList);
    }


    @Test
    public void getgRoupByAndHaving() {
        String jpql = "SELECT O.name ,O.id FROM Order O GROUP BY O.name HAVING O.id>?";
        List resultList = createEntityManager.createQuery(jpql).setParameter(1, 1).getResultList();
        System.out.println("resultList>>" + resultList.size());
        System.out.println("resultList>>" + resultList);
    }

    @Test
    public void getOrderBy() {
        String jpql = "FROM Order O where O.id> ? Order By O.id DESC";

        List resultList = createEntityManager.createQuery(jpql).setParameter(1, 41).getResultList();
        System.out.println("resultList>>" + resultList.size());
        System.out.println("resultList>>" + resultList);
    }


    @Test
    public void getJPQLSetHint() {
        String JPQL = "FROM Order O WHERE O.id>?";
        //
        Query createQuery = createEntityManager.createQuery(JPQL).setHint(QueryHints.HINT_CACHEABLE, true);
        //参数1 :?的序号 参数2：标示的？的值
        createQuery.setParameter(1, 40);
        List resultList = createQuery.getResultList();
        System.out.println(">>" + resultList.size());

        System.out.println("--------------------------------------");
        //
        createQuery = createEntityManager.createQuery(JPQL).setHint(QueryHints.HINT_CACHEABLE, true);
        //参数1 :?的序号 参数2：标示的？的值
        createQuery.setParameter(1, 40);
        resultList = createQuery.getResultList();
        System.out.println(">" + resultList.size());
    }


    @Test
    public void getNativeQuery() {
        String sql = "SELECT P.lsat_name FROM JPA_PERSON P WHERE P.id=?";
        Object singleResult = createEntityManager.createNativeQuery(sql).setParameter(1, 5).getSingleResult();
        System.out.println("singleResult>>" + singleResult);
    }


    @Test
    public void getNamedQuery() {
        List resultList = createEntityManager.createNamedQuery("nameQuery").setParameter(1, 6).getResultList();
        System.out.println("resultList>>" + resultList);
    }


    @Test
    public void getJPQL2() {
        String jpql = "SELECT new Person(P.id,P.name) FROM Person P where P.id=?";
        Query createQuery = createEntityManager.createQuery(jpql);
        //参数1 :?的序号 参数2：标示的？的值
        createQuery.setParameter(1, 2);
        List resultList = createQuery.getResultList();
        System.out.println(resultList.size());
        System.out.println(resultList);

    }


    @Test
    public void getJPQL1() {
        String JPQL = "SELECT O.id ,O.name FROM Order O WHERE O.id=?";
        //
        Query createQuery = createEntityManager.createQuery(JPQL);
        //参数1 :?的序号 参数2：标示的？的值
        createQuery.setParameter(1, 46);
        List resultList = createQuery.getResultList();
        System.out.println(resultList.size());
        System.out.println(resultList);
    }


    @Test
    public void getJPQL() {
        String JPQL = "FROM Order O WHERE O.id>?";
        //
        Query createQuery = createEntityManager.createQuery(JPQL);
        //参数1 :?的序号 参数2：标示的？的值
        createQuery.setParameter(1, 40);
        List resultList = createQuery.getResultList();
        System.out.println(resultList.size());
    }


    //使用二级缓存
    @Test
    public void getSession() {

        Person find = createEntityManager.find(Person.class, 1);
        transaction.commit();
        createEntityManager.close();

        createEntityManager = createEntityManagerFactory.createEntityManager();
        transaction = createEntityManager.getTransaction();
        transaction.begin();
        Person find1 = createEntityManager.find(Person.class, 1);
        System.out.println("find>>" + find);
        System.out.println("find1>>" + find1);


    }


    /**
     * 在保存的时候建议先保存一的一方,减少Update语句
     * 维护关联关系的时候,建议让多的一方来维护关联关系 减少Update语句
     * 在一的一方,设置@OneToMany(mappedBy="student").就不要使用@JoinColumn(name="student_id")
     */

    @Test
    public void getOneToMany1() {
        Student student = new Student();

        student.setName("小小");
        student.setMail("qq@.com");
        student.setBirth(new Date());

        Order order = new Order();
        order.setName("1-ss-n");

        Order order1 = new Order();
        order1.setName("2-ss-n");


        //设置关联关系
        student.getOrder().add(order1);
        student.getOrder().add(order);

        order.setStudent(student);
        order1.setStudent(student);
        //执行保存
        createEntityManager.persist(student);
        createEntityManager.persist(order);
        createEntityManager.persist(order1);

    }
    @Test
    public void getTables() {


    }

    @Test
    public void getOneToManyUpdate() {
        Student find = createEntityManager.find(Student.class, 13);
        find.setName("小马");

        find.getOrder().iterator().next().setName("小马的Order");
    }


    /**
     * 默认的情况下,是把外键关联多的一方的外键修改为空,再进行删除1的一方的数据
     * 可以,使用级联删除,但是的配置@OneToMany(cascade={CascadeType.REMOVE})
     */

    @Test
    public void getOneToManyDel() {
        Student find = createEntityManager.find(Student.class, 10);

        createEntityManager.remove(find);

    }


    /**
     * 查询默认是懒加载
     * 可以修改加载策略@OneToMany(fetch=FetchType.EAGER)
     */
    @Test
    public void getOneToManyFind() {
        Student find = createEntityManager.find(Student.class, 10);
        System.out.println(find.getName());
        System.out.println(find.getOrder().size());

    }


    @Test
    public void getOneToManyAdd() {
        Student student = new Student();
        student.setBirth(new Date());
        student.setName("小六");
        student.setMail("QEQWE@QQ.COM");

        Order order = new Order();
        order.setName("1-s-n");


        Order order1 = new Order();
        order1.setName("2-s-n");

        //设置关联关系
        student.getOrder().add(order);
        student.getOrder().add(order1);

        //执行
        createEntityManager.persist(student);
        createEntityManager.persist(order);
        createEntityManager.persist(order1);
    }


    //只要关联表中的数据进行关联了,都可以进行修改
    @Test
    public void getManyToOneUpdate() {
        Order find = createEntityManager.find(Order.class, 12);
        find.setName("小李");
        //	  find.getStudent().setName("小李");
    }


    //不能删除1的一端 ,有外键关联
    @Test
    public void getManyToOneDel() {
        Order find = createEntityManager.find(Order.class, 11);
        createEntityManager.remove(find);

    }


    /**
     * 在默认的情况下,使用的左外链接 的方式获取数据
     * 可以使用懒加载@ManyToOne(fetch=FetchType.LAZY)
     */
    @Test
    public void getManToOneFind() {
        Order find = createEntityManager.find(Order.class, 8);
        System.out.println("find》》" + find);
        //	 System.out.println("getStudent》》"+find.getStudent());
    }


    // 添加; 在保存数据时,建议先保存一的一端,在保存多的一端,不会多出update语句
//    @Test
//    public void test() {
//
//        Student student = new Student();
//        student.setMail("asd@qq.com");
//        student.setName("小张");
//        student.setBirth(new Date());
//
//        Order order = new Order();
//        order.setName("N-S-1");
//
//        Order order1 = new Order();
//        order1.setName("N-S-2");
//        //设置关联关系
//        order.setStudent(student);
//        order1.setStudent(student);
//
//        //进行保存
//        createEntityManager.persist(student);
//        createEntityManager.persist(order1);
//        createEntityManager.persist(order);
//    }

}
