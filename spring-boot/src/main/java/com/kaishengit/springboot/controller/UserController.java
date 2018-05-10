package com.kaishengit.springboot.controller;


import com.kaishengit.springboot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public String home(Model model){
        List<String> names = Arrays.asList("tom","jack","张三");

        model.addAttribute("message","你好");
        model.addAttribute("age",11);
        model.addAttribute("names",names);
        model.addAttribute("id",1001);

        return "user/home";
    }

    @GetMapping("/index")
    public String index(){
        userDao.save("擎天柱","123456");
        return "user/index";
    }
}
