package com.spring.data.jpa.example.jpademo.auth.entityone;

import com.spring.data.jpa.example.jpademo.entity.AbstractEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends AbstractEntity{
    private String name;
    private long salary;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy="employee")
    private Collection<Phone> phones = new ArrayList<Phone>();


//    @OneToMany(mappedBy="employee")
//    //@MapKey(name="type")
//    @MapKey(name="employee")
//    //private Map<String,Phone> phones = new HashMap<String,Phone>();
//    private Map<Employee,Phone> phones = new HashMap<>();

    @ManyToOne
    private Department department;

    //自关联
    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<Employee>();

    @ManyToMany(mappedBy="employees")
    private Collection<Project> projects = new ArrayList<Project>();

    @Override
    public String toString() {
        return "";
    }


}
