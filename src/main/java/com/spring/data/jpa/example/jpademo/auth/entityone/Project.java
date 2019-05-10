package com.spring.data.jpa.example.jpademo.auth.entityone;

import com.spring.data.jpa.example.jpademo.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Inheritance
public class Project extends AbstractEntity{
    protected String name;
    @ManyToMany
    protected Collection<Employee> employees = new ArrayList<Employee>();

    public void addEmployee(Employee employee) {
        if (!getEmployees().contains(employee)) {
            getEmployees().add(employee);
        }
        if (!employee.getProjects().contains(this)) {
            employee.getProjects().add(this);
        }
    }
}
