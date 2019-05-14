package com.spring.data.jpa.example.jpademo.auth.service;

import com.spring.data.jpa.example.jpademo.auth.entityone.Employee;
import com.spring.data.jpa.example.jpademo.entity.AbstractEntity;
import com.spring.data.jpa.example.jpademo.repository.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class JpaSpecDemoITest extends AbstractTest{

    @Autowired private JpaSpecDemoI jpaSpecDemoI;

    @Test
    public void getEmployee() throws Exception {
        List<Employee> es =  jpaSpecDemoI.getEmployee("Marcus",35000L,null);
       assertTrue(!es.isEmpty());
    }

    @Test
    public void getEmployee1() throws Exception {
        List<Employee> es = jpaSpecDemoI.getEmployee("Marcus");
        assertTrue(!es.isEmpty());
    }

    @Test
    public void getEmployeeJoin() throws Exception {
        List<Employee> es = jpaSpecDemoI.getEmployeeJoin();
        assertTrue(!es.isEmpty());
    }



}