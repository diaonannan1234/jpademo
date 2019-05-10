package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Department;
import com.spring.data.jpa.example.jpademo.auth.entityone.Employee;
import com.spring.data.jpa.example.jpademo.auth.vo.EmployeeVO;
import com.spring.data.jpa.example.jpademo.repository.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class EmployeeRepositoryTest extends AbstractTest{

    @Autowired private EmployeeRepository employeeRepository;

    @Autowired private DepartmentRepository departmentRepository;

    @Test
    public void testGetEmployeeName(){
        List<String> eNames = employeeRepository.getEmployeeName();
        assertTrue(!eNames.isEmpty());
    }

    @Test
    public void testGetEmployeeInDepartment(){
        List<Department> departments = employeeRepository.getEmployeeInDepartment();
        assertTrue(!departments.isEmpty());
    }

    @Test
    public void testGetEmployeeNameSalary(){
        List<Object[]> names = employeeRepository.getEmployeeNameSalary();
        assertTrue(!names.isEmpty());
    }

    @Test
    public void testGetEmployeeNameSalaryVO(){
        List<EmployeeVO> employeeVOS = employeeRepository.getEmployeeNameSalaryVO();
        assertTrue(!employeeVOS.isEmpty());
    }

    @Test
    public void testGetManagerAll(){
        List<Employee> employees = employeeRepository.getManagerAll();
        assertTrue(!employees.isEmpty());
    }

//    @Test
//    public void testgetE(){
//        Map maps = employeeRepository.getE();
//        assertTrue(maps != null);
//    }

    @Test
    public void testgetEmployFetch(){
        List<Employee> employees = employeeRepository.getEmployFetch();
        assertTrue(!employees.isEmpty());
    }

    @Test
    public void testGetEmployeeBetween(){
       List<Object[]> departments = departmentRepository.getDepartmentGroupbyEmpSalary();
        assertTrue(!departments.isEmpty());
    }
}