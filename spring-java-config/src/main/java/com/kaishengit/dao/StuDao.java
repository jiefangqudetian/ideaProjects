package com.kaishengit.dao;

import com.kaishengit.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StuDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public void save(Student student){
        String sql = "insert into t_stu (name,address,tel) values (?,?,?)";
        jdbcTemplate.update(sql,student.getName(),student.getAddress(),student.getTel());
    }

    public void batchSave(List<Student> students){
        String sql = "insert into t_stu (name,address,tel) values (?,?,?)";
        List<Object[]> params = new ArrayList<Object[]>();
        for (Student student: students){
            Object[] objects = new Object[3];
            objects[0] = student.getName();
            objects[1] = student.getAddress();
            objects[2] = student.getTel();
            params.add(objects);
        }
        jdbcTemplate.batchUpdate(sql,params);

    }

    public void update(Student student){
        String sql = "update t_stu set name = ?,address=?,tel=? where id = ?";
        jdbcTemplate.update(sql,student.getName(),student.getAddress(),student.getTel(),student.getId());
    }

    public void updates(Student student){
        String sql = "update t_stu set name =:name,address =:address,tel = :tel where id = :id";
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("name",student.getName());
        param.put("address",student.getAddress());
        param.put("tel",student.getTel());
        param.put("id",student.getId());
        namedParameterJdbcTemplate.update(sql,param);
    }

    public Student find(int id){
        String sql = "select * from t_stu where id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Student>(Student.class),id);
    }

    public List<Student> findAll(){
        String sql = "select * from t_stu";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Student>(Student.class));
    }

    public int findCount(){
        String sql = "select count(*) from t_stu";
        return jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<Integer>(Integer.class));
    }

    public List<Map<String,Object>> findAllMap(){
        String sql = "select * from t_stu";
        return jdbcTemplate.query(sql,new ColumnMapRowMapper());
    }
}
