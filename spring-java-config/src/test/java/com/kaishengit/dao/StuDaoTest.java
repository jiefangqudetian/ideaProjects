package com.kaishengit.dao;

import com.kaishengit.BaseTestCase;
import com.kaishengit.dao.StuDao;
import com.kaishengit.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StuDaoTest extends BaseTestCase{

    @Autowired
    private StuDao stuDao;


    @Test
    public void testSave(){
        Student student = new Student("小王","塔南路","177");
        stuDao.save(student);
    }
    @Test
    public void testBatchSave(){
        List<Student> students = Arrays.asList(
                new Student("大娃","莲花洞","123"),
                new Student("二娃","莲花洞","456")
        );
        stuDao.batchSave(students);
    }
    @Test
    public void testUpdate(){
        Student student = stuDao.find(1020);
        student.setAddress("人民路");
        student.setName("老王");
        student.setTel("119");
        stuDao.update(student);
    }
    @Test
    public void testUpdates(){
        Student student = stuDao.find(1022);
        student.setName("老李");
        student.setAddress("普吉路");
        student.setTel("154");
        stuDao.updates(student);
    }
    @Test
    public void findById(){
        Student student = stuDao.find(1019);
        Assert.assertNotNull(student);
    }

    @Test
    public void testFindAll(){
        List<Student> studentList = stuDao.findAll();
        Assert.assertEquals(studentList.size(),6);

    }

    @Test
    public void testFindCount(){
        int num = stuDao.findCount();
        System.out.println(num);
        Assert.assertEquals(num,6);
    }
    @Test
    public void testFindAllMap(){
        List<Map<String,Object>> mapList = stuDao.findAllMap();
        for (Map<String,Object> map:mapList){
            for (Map.Entry entry:map.entrySet()){
                System.out.println(entry.getKey()+"-->"+entry.getValue());
            }
        }
    }

}
