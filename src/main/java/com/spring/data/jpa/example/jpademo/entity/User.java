package com.spring.data.jpa.example.jpademo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "t_user")
public class User extends AbstractEntity{

    private String name;

    private Integer age;

    private LocalDate createDate;

    private LocalDateTime createDateTime;

    private LocalTime createTime;

    private ZonedDateTime createZoneDateTime;

    private Boolean enable = false;

    private EnableEnum enableEnum = EnableEnum.ENABLE;


}
