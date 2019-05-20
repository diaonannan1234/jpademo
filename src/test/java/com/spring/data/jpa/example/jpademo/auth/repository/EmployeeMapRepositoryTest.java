package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Employee;
import com.spring.data.jpa.example.jpademo.auth.entityone.Phone;
import com.spring.data.jpa.example.jpademo.repository.AbstractTest;
import com.spring.data.jpa.example.jpademo.repository.RoleRepository;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class EmployeeMapRepositoryTest extends AbstractTest{

    @Autowired EmployeeRepository employeeRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testBeanFactory(){
        Map<String,BeanFactory> name = applicationContext.getBeansOfType(BeanFactory.class);
        for(String n : name.keySet()){
            System.out.println("BeanName===="+n);

        }
    }

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