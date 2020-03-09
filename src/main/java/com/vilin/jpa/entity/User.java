package com.vilin.jpa.entity;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "User.plus1",
        procedureName = "pluslinout",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "arg", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "res", type = Integer.class)
        }
)
@NamedQuery(name = "User.findByNameForNamedQuery", query = "select u from User u where u.name = ?1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
