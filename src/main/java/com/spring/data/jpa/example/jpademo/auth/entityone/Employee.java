package com.spring.data.jpa.example.jpademo.auth.entityone;

import com.spring.data.jpa.example.jpademo.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

    @ManyToOne
    private Department department;

    //自关联
    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<Employee>();

    @ManyToMany(mappedBy="employees")
    private Collection<Project> projects = new ArrayList<Project>();

    public void addPhone(Phone phone) {
        if (!getPhones().contains(phone)) {
            getPhones().add(phone);
            if (phone.getEmployee() != null) {
                phone.getEmployee().getPhones().remove(phone);
            }
            phone.setEmployee(this);
        }
    }

    public void addDirect(Employee employee) {
        if (!getDirects().contains(employee)) {
            getDirects().add(employee);
            if (employee.getManager() != null) {
                employee.getManager().getDirects().remove(employee);
            }
            employee.setManager(this);
        }
    }

    public void addProject(Project project) {
        if (!getProjects().contains(project)) {
            getProjects().add(project);
        }
        if (!project.getEmployees().contains(this)) {
            project.getEmployees().add(this);
        }
    }

}
