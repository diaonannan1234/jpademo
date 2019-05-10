package com.spring.data.jpa.example.jpademo.auth.entityone;

import com.spring.data.jpa.example.jpademo.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "DEPARTMENT")
public class Department extends AbstractEntity{
    private String name;
    @OneToMany(mappedBy="department")
    private Set<Employee> employees = new HashSet<Employee>();
}
