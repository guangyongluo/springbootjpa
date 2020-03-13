package com.vilin.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_receiving_address")
public class UserReceivingAddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "user_id", nullable = false)
    @JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "address_city", nullable = true, length = 500)
    private String addressCity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }
}
