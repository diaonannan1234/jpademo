package com.spring.data.jpa.example.jpademo.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

/**
 * 抽象测试类，添加相关测试类注解
 *
 * @author chen_yunpeng
 * Created by chen_yunpeng on 2019-04-23 16:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@MappedSuperclass
public abstract class AbstractTest {
}
