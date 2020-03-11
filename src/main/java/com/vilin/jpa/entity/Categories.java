package com.vilin.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="JPA_CATEGORYS")
@Entity
public class Categories {

	private Integer  id;
	
	private  String name;
	
	private Set<Items> setitems=new HashSet<>();

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
	
     @ManyToMany(mappedBy="setcat")
	public Set<Items> getSetitems() {
		return setitems;
	}

	public void setSetitems(Set<Items> setitems) {
		this.setitems = setitems;
	}
	
	
	
}
