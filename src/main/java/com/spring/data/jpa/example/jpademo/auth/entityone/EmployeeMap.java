package com.spring.data.jpa.example.jpademo.auth.entityone;

import com.spring.data.jpa.example.jpademo.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "EMPLOYEE_MAP")
public class EmployeeMap extends AbstractEntity{

    @Id
    private String id;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy="employee")
    @MapKey(name="type")
    private Map<String,Phone> phones = new HashMap<String,Phone>();

    @ManyToOne
    private Department dept;

    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<Employee>();

    @ManyToMany(mappedBy="employees")
    private Collection<Project> projects = new ArrayList<Project>();
}
