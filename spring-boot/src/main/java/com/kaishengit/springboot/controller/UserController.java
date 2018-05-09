package com.kaishengit.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String home(Model model){
        List<String> names = Arrays.asList("tom","jack","张三");

        model.addAttribute("message","你好");
        model.addAttribute("age",11);
        model.addAttribute("names",names);
        model.addAttribute("id",1001);

        return "user/home";
    }
}
