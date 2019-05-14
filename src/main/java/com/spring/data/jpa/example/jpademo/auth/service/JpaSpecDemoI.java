package com.spring.data.jpa.example.jpademo.auth.service;

import com.spring.data.jpa.example.jpademo.auth.entityone.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface JpaSpecDemoI {

    //多个Specification联接。
    List<Employee> getEmployee(String name, long salary, Specification<Employee> se);

    //演示子查询。
    List<Employee> getEmployee(String name);

    //Join
    List<Employee>  getEmployeeJoin();
}
