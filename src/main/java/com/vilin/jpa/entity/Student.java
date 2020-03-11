package com.vilin.jpa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder.In;

@Table(name="JPA_STUDENT")
@Entity
public class Student {

	private  Integer  id;
	
	private  String  name;
	
	private  Date  birth;
	
	private  String mail;

	
	public  List<Order> order=new  ArrayList<>();
	
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
    
	
	/**
	 * 关联关系是1-N使用注解@OneToMany
	 * 映射关联外键的名称@JoinColumn(name="order_id")
	 * 
	 * */
//	@JoinColumn(name="student_id")
	@OneToMany(mappedBy = "student")
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	@Temporal(TemporalType.DATE)
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

//	@Override
//	public String toString() {
//		return "Student [id=" + id + ", name=" + name + ", birth=" + birth + ", mail=" + mail + "]";
//	}


	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", birth=" + birth +
				", mail='" + mail + '\'' +
				", order=" + order +
				'}';
	}
}
