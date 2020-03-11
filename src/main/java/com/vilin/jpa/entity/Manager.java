package com.vilin.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="JPA_MANAGER")
@Entity
public class Manager {

	private  Integer id;
	
	private  String name;
	
	private  Dep  dep;
 
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
	 * 对于,不维护关联关系,没有外键的一方,使用@OneToOne(mappedBy="manager")
	 * 设置属性mappedBy="manager"
	 *  如果不设置,两边都维护,就会出现多余的update语句
	 * */
   @OneToOne(mappedBy="manager",fetch=FetchType.LAZY)
	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}
	
	
}
