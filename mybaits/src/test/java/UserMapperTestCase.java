import com.kaishengit.entity.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

public class UserMapperTestCase {
    private SqlSession sqlSession;

    @Before
    public void init(){
        //自动提交事务
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
    }
    @Test
    public void testFindById() throws Exception{
        //1.加载配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //2.创建SqlSessionFactory
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        //3.创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.操作数据库
        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",1);
        System.out.println(user);
        //5.关闭sqlSession
        sqlSession.close();

    }

    @Test
    public void testFindAll(){

        List<User> userList = sqlSession.selectList("com.kaishengit.mapper.UserMapper.findAll");
        for (User user:userList) {
            System.out.println(user);
        }

    }

    @Test
    public void testSave(){
        User user = new User();
        user.setName("唐浩");
        user.setAddress("新华街");
        user.setTel("451");
        sqlSession.insert("com.kaishengit.mapper.UserMapper.save",user);


    }

    @Test
    public void update(){
        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",2);
        user.setName("李四");
        sqlSession.update("com.kaishengit.mapper.UserMapper.update",user);
    }

    @Test
    public void delete(){
        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",3);
        sqlSession.delete("com.kaishengit.mapper.UserMapper.delete",user);
    }


    public void testPage2(){
        List<User> userList = sqlSession.selectList("com.kaishengit.mapper.UserMapper.page2");

    }




    @After
    public void destory(){
        //关闭sqlSession
        sqlSession.close();
    }
}
