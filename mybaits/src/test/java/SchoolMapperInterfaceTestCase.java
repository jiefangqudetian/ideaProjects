import com.kaishengit.entity.School;
import com.kaishengit.entity.Student;
import com.kaishengit.mapper.SchoolMapper;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SchoolMapperInterfaceTestCase {

    private SqlSession sqlSession;
    private SchoolMapper schoolMapper;

    @Before
    public void init(){
        //自动提交事务
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        //动态代理 动态生成
        schoolMapper = sqlSession.getMapper(SchoolMapper.class);
    }

    @After
    public void destory(){
        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void testFindById(){


        School school = schoolMapper.findById(1);
        System.out.println(school);

    }








}
