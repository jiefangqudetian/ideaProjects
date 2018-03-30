package com.kaishengit.mapper;

import com.kaishengit.entity.Tag;

import java.util.List;


public interface TagMapper {

    List<Tag> findByStudentId(int id);




}
