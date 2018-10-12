package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.controller.result.ResponseBean;
import com.kaishengit.entity.Movie;
import com.kaishengit.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping
    public ResponseBean list(@RequestParam(required = false,name = "p",defaultValue = "1") Integer pageNum){

        PageInfo<Movie> pageInfo = movieService.findByPageNum(pageNum);
        return ResponseBean.success(pageInfo);
    }


    @PostMapping("/new")
    public ResponseBean saveNewMovie(@RequestBody Movie movie){

        movieService.saveMovie(movie);
        return ResponseBean.success();
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteMovieById(@PathVariable Integer id){
        movieService.deleteById(id);
        return ResponseBean.success();
    }

    @GetMapping("/{id}")
    public ResponseBean getMovie(@PathVariable Integer id){

        Movie movie = movieService.findById(id);
        return ResponseBean.success(movie);
    }

    @PutMapping("/{id}")
    public ResponseBean editMovie(@RequestBody Movie movie){
        movieService.updateMovie(movie);
        return ResponseBean.success();
    }

}
