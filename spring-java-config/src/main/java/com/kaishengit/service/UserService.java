package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@Named
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }


    public void save(){
        userDao.save();
    }
}
