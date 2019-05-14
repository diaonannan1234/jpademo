package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeMapRepository extends JpaRepository<Employee,String> ,JpaSpecificationExecutor<Employee> {



}
