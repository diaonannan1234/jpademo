package com.spring.data.jpa.example.jpademo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class JdbcRepositoryTest extends AbstractTest{

    @Autowired JdbcRepository jdbcRepository;

    @Test
    public void testFindJdbc(){
        int count = jdbcRepository.findJdbc();
        assertTrue(count > 0 );
    }

}