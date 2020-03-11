package com.vilin.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="JPA_DEP")
@Entity
public class Dep {

	private  Integer  id;
	
	private  String  name;
	
	private  Manager  manager;

	 @GeneratedValue
	 @Id
	public Integer getId() {
		return id;
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

	/**
	 * 双向一对一，@OneToOne
	 * 添加外键使用@JoinColumn(name="manager_id",unique=true)
	 *   因为是1-1 那么就的使用unique=true
	 * */
	@JoinColumn(name="manager_id",unique=true)
	@OneToOne(fetch=FetchType.LAZY)
//    @OneToOne
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
	
	
}
