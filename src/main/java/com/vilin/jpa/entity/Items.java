package com.vilin.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="JPA_ITMES")
@Entity
public class Items {

	private  Integer  id;
	
	private String  name;
	
	private  Set<Categories> setcat=new HashSet<>();
 
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
	 * 多对多使用 : @ManyToMany
	 *.@JoinTable name 是中间表的表名  
	 * joinColumns执向外键
	 *    .@JoinColumn(name="items_id",referencedColumnName="id")   name 外键名称  referencedColumnName 当前主键对象的id
	 *    inverseJoinColumns={
		.@JoinColumn(name="categorys_id",referencedColumnName="id")}  name 是关联表外键的名称 referencedColumnName关联对象的id
	 * 
	 * */
	@JoinTable(name="itmes_categorys",joinColumns={@JoinColumn(name="items_id",referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="categorys_id",referencedColumnName="id")})
	@ManyToMany
	public Set<Categories> getSetcat() {
		return setcat;
	}

	public void setSetcat(Set<Categories> setcat) {
		this.setcat = setcat;
	}
	
	
}
