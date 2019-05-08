package com.spring.data.jpa.example.jpademo.repository;

import com.spring.data.jpa.example.jpademo.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class UserRepositoryTest extends AbstractTest{

    @Autowired UserRepository userRepository;

    @Test
    public void testSaveUser(){
        User user = User.builder().name("duwei")
                 .createDate(LocalDate.now())
                .createDateTime(LocalDateTime.now())
                .createTime(LocalTime.now())
                .build();
        User userR = userRepository.save(user);

        User userF = userRepository.findOne(Example.of(userR)).orElse(null);

        assertNotNull(userF);
    }
}