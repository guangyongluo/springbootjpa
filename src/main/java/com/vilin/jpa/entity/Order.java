package com.vilin.jpa.entity;

import javax.persistence.*;


@Table(name = "JPA_ORDER")
@Entity
public class Order {


    private Integer id;

    private String name;

    private Student student;


    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    /**
     * N-1@ManyToOne
     * 映射外键字段名@JoinColumn()
     */
    @JoinColumn(name = "student_id")
    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    @Override
    public String toString() {
        return "Order [id=" + id + ", name=" + name + ", student=" + student + "]";
    }
	
/*	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + "]";
	}*/


}
