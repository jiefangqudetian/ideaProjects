import com.kaishengit.entity.Student;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentMapperInterfaceTestCase {

    private SqlSession sqlSession;
    private StudentMapper studentMapper;

    @Before
    public void init(){
        //自动提交事务
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        //动态代理 动态生成
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void destory(){
        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void testFindById(){


        Student student = studentMapper.findById(5);
        System.out.println(student);

    }




    @Test
    public void testFindAll(){

       List<Student> studentList = studentMapper.findAll();
        for (Student student:studentList) {
            System.out.println(student);
        }

    }

    @Test
    public void testFindByIdWithTag(){

        Student student = studentMapper.findByIdWithTag(1);
        System.out.println(student);

    }

}
