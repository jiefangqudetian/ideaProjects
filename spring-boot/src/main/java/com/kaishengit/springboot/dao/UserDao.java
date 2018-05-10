package com.kaishengit.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(String name,String password){

        String sql = "insert into kaisheng (name,password) values (?,?)";
        jdbcTemplate.update(sql,name,password);
    }
}
