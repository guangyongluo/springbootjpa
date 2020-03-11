package com.vilin.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;

import com.vilin.jpa.entity.Dep;
import com.vilin.jpa.entity.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDepAndManager {

	 private EntityManagerFactory createEntityManagerFactory;
	private EntityManager createEntityManager;
	private EntityTransaction transaction;

	@Before
 public  void init(){
	  
	  createEntityManagerFactory = Persistence.createEntityManagerFactory("jpa");
	  createEntityManager = createEntityManagerFactory.createEntityManager();
	  transaction = createEntityManager.getTransaction();
	  transaction.begin();
	  
 }
	
	 
	 @After
	public  void close(){
		 transaction.commit();
		 createEntityManager.close();
		 createEntityManagerFactory.close();
		
	} 
	 
/***
 * 获取维护关联关系的一方
 * 默认的情况下,使用的左外链接
 * 可以通过设置@OneToOne(fetch=FetchType.LAZY)加载策略,但是会发送两天SQL语句,不建议使用,还不如默认的一条语句
 * 
 * 
 * */

	 @Test
	 public  void  getOneToOneFind1(){
		 Manager find = createEntityManager.find(Manager.class, 64);
		 System.out.println("find>>"+find.getName());
		 System.out.println("find>>"+find.getDep().getClass().getName());
		 
	 }
	 
	 
	 
	 
	 /**
	  * 在默认情况下,
	  * 如获取不维护关联关系的一方,会使用左外链接
	  * 可以通过设置@OneToOne(fetch=FetchType.LAZY)设置懒加载，在维护关联关系的一方设置
	  * */
	 @Test
	 public  void  getOneToOneFind(){
		 Dep find = createEntityManager.find(Dep.class, 24);
		 System.out.println("find>>"+find.getName());
		 System.out.println("find>>"+find.getManager().getName());
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 /**
	  * 双向1-1 ,在保存的时候,建议先保存不维护关联关系的一方（没有外键）,不然多出UPDATE语句
	  * */
	 
	 @Test
	 public  void  getOneToOne(){
		 Manager manager = new Manager();
		 manager.setName("小张");
		 
		 Dep dep = new  Dep();
		 dep.setName("销售");
		 
		 
		 //设置关联关系
		 manager.setDep(dep);
		 dep.setManager(manager);
		 
		 //保存
		 createEntityManager.persist(manager);
		 createEntityManager.persist(dep);
		 
		 
	 }
	 
	 
	 
	
	@Test
	public  void getTest(){
		
		
	}
}
