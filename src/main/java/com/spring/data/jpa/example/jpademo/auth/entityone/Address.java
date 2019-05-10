package com.spring.data.jpa.example.jpademo.auth.entityone;

import com.spring.data.jpa.example.jpademo.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address extends AbstractEntity{
    private String street;
    private String city;
    private String state;
    private String zip;
}
