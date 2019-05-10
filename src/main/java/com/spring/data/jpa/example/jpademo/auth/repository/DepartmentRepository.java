package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,String> {

    //检索出有两个员工的部门
    @Query("select d from Department d where size(d.employees) = 2 ")
    List<Department> getDepartmentSize();

    @Query("select d.name,avg(e.salary) from Department d join d.employees e group by d.name")
    List<Object[]> getDepartmentGroupbyEmpSalary();
}
