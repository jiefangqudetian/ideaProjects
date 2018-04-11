package com.kaishengit.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.inject.Named;

@Repository
@Lazy
public class UserDao {

    public void save(){
        System.out.println("userDao save");
    }
}
