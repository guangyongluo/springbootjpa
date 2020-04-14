package com.vilin.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "JPA_INSPECTION")
@Entity
public class Inspection {

    private Long id;

    private String username;

    private String location;

    private Date checkTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", checkTime=" + checkTime +
                '}';
    }
}
