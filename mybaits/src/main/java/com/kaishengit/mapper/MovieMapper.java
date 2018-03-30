package com.kaishengit.mapper;

import com.kaishengit.entity.Movie;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MovieMapper {


    List<Movie> findList(@Param("title") String title);
    List<Movie> findByParams(Map<String,Object> params);
    List<Movie> findByIdList(@Param("idList") List<Integer> idList);

    //@Select("select * from movie where release_year = #{releaseYear}")
    //List<Movie> findByReleaseyear(@Param("releaseYear") String releaseYear,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize); @Select("select * from movie where release_year = #{releaseYear}")
    @Select("select * from movie where release_year = #{releaseYear}")
    List<Movie> findByRelyear(Map<String,Object> param);
    void insertBatch(List<Movie> movieList);



    Movie findById(int id);
}
