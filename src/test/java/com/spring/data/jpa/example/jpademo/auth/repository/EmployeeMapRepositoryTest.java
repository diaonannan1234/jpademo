package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Employee;
import com.spring.data.jpa.example.jpademo.auth.entityone.Phone;
import com.spring.data.jpa.example.jpademo.repository.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class EmployeeMapRepositoryTest extends AbstractTest{

    @Autowired EmployeeRepository employeeRepository;

    @Test
    public void testEmployeeMap(){
        List<Employee> em = employeeRepository.findAll();
      //  Map<Employee,Phone> map = em.get(0).getPhones();
        assertTrue(!em.isEmpty());
    }
//
//    @Test
//    public void testMap(){
//        List<Object[]> ems = employeeRepository.getMap();
//        assertTrue(!ems.isEmpty());
//    }
//
//    @Test
//    public void testMap1(){
//        List<Object[]> ems = employeeRepository.getMap1();
//        assertTrue(!ems.isEmpty());
//    }
}