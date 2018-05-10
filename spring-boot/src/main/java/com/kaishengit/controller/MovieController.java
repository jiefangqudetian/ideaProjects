package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.service.MovieServicce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieServicce movieServicce;

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Integer id){
        return movieServicce.findById(id);
    }

    @GetMapping
    public PageInfo<Movie> home(@RequestParam(required = false,defaultValue = "1",name = "p") Integer pageNo){

        PageInfo<Movie> pageInfo = movieServicce.findAllByPageNo(pageNo);
        logger.info("查询电影资料{}",pageInfo.getList().size());

        return pageInfo;
    }


}
