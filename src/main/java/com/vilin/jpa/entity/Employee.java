package com.vilin.jpa.entity;

import javax.persistence.*;
import java.util.Date;

//@NamedQuery(name = "nameQuery", query = "SELECT P FROM Person P WHERE P.id=?")
//@Cacheable(true)
@Table(name = "JPA_EMPLOYEE")
@Entity
public class Employee {


    private Integer id;

    private String name;

    private Date date;

    private Date birth;

    public Employee() {
    }


    public Employee(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @TableGenerator(name = "ID_GENERATOR", table = "jpa_tables_id", pkColumnName = "jpa_id_name", pkColumnValue = "JPA_EMPLOYEE_ID", valueColumnName = "jpa_id_value", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @Column(name = "last_name", length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(TemporalType.DATE)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

//    @Basic
//    public void getXXXX() {
//
//
//    }


    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", date=" + date + ", birth=" + birth + "]";
    }


}
