package com.vilin.jpa.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_info")
public class UserInfoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "first_name", nullable = true, length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = true, length = 100)
    private String lastName;
    @Column(nullable = true, length = 100)
    private String telephone;
    @Column(name = "create_time", nullable = true)
    private Date createTime;
    @Column(nullable = true)
    private String version;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "address_id", nullable = true)
    @Fetch(FetchMode.JOIN)
    private UserReceivingAddressEntity addressEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public UserReceivingAddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(UserReceivingAddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }
}
