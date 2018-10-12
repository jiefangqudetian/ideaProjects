package com.kaishengit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.service.MovieService;

@Service(version = "1.0",timeout = 5000)
public class MovieServiceImpl implements MovieService {
    @Override
    public String findMovieNameById(Integer id) {

        if (id==1){
            return "阿甘正传";
        } else {
            return "钢铁侠";
        }
    }
}
