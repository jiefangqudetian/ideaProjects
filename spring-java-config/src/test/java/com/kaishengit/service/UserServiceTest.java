package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.kaishengit.BaseTestCase;


public class UserServiceTest extends BaseTestCase{

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Test
    public void testSave(){
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        UserService userService = (UserService) applicationContext.getBean("userService");*/
        userService.save();

        userDao.save();
    }
}
