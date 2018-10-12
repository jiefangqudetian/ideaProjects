package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String Hello(){
        System.out.println("---->hello,springmvc");
        System.out.println("你好");
        return "hello";
    }

    @GetMapping("/hi")
    public @ResponseBody void Hi(){
        System.out.println("hi");
    }

    @GetMapping("/login")
    public String Login(){
        return "login";
    }
}
