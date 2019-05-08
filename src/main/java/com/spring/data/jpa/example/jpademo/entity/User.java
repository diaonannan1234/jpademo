package com.spring.data.jpa.example.jpademo.entity;

import lombok.Builder;
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
@Builder
public class User extends AbstractEntity{

    private String name;

    private Integer apg;

    private LocalDate createDate;

    private LocalDateTime createDateTime;

    private LocalTime createTime;

    private ZonedDateTime createZoneDateTime;


}
