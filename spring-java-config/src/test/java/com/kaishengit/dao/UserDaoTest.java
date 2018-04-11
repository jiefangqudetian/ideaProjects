package com.kaishengit.dao;

import com.kaishengit.BaseTestCase;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends BaseTestCase{

    @Autowired
    UserService service;

    @Test
    public void testSave(){
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        //System.out.println("123");

        //UserService userService = (UserService) applicationContext.getBean("userService");
        //UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        service.save();
        System.out.println(service.getClass().getName());
        System.out.println(service.getClass().getName());
    }
}
