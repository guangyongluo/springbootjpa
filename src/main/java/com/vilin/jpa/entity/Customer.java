package com.vilin.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "JPA_CUSTOMER")
public class Customer {

    private Integer id;

    private String name;

    private String address;

    private boolean focus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Transient
    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }
}
