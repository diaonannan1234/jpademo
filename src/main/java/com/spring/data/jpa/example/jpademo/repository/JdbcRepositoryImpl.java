package com.spring.data.jpa.example.jpademo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepositoryImpl implements JdbcRepository {

    @Autowired  private JdbcTemplate jdbcTemplate;

    public int findJdbc(){

        String sql ="select count(*) from t_user";

        Integer num =jdbcTemplate.queryForObject(sql, Integer.class);
        return num;
    }
}
