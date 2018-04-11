package com.kaishengit.service;

import com.kaishengit.dao.StuDao;
import com.kaishengit.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StuDao stuDao;

    @Transactional(rollbackFor = Exception.class,isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public void saveStudent(List<Student> studentList){
        for (Student student:studentList){
            stuDao.save(student);
        }
    }
}
