package com.kaishengit.service;

import com.kaishengit.BaseTestCase;
import com.kaishengit.entity.Student;
import com.kaishengit.service.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class StudentServiceTest extends BaseTestCase{

    @Autowired
    private StudentService studentService;

    @Test
    public void saveStudent(){
        List<Student> studentList = Arrays.asList(
                new Student("小明","长安路","117"),
                new Student("小红","红旗路",null)
        );
        System.out.println(studentService.getClass().getName());
        studentService.saveStudent(studentList);
    }
}
