import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperInterfaceTestCase {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init(){
        //自动提交事务
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        //动态代理 动态生成
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destory(){
        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setName("周润发发发发发");
        user.setAddress("香港1");
        user.setTel("171127113");
        userMapper.save(user);
        int id = user.getId();

        System.out.println("获取主键："+id);

    }


    @Test
    public void testFindById(){
        User user = userMapper.findById(4);
        System.out.println(user);
    }

    @Test
    public void testFindAll(){

        List<User> userList = userMapper.findAll();
        for (User user:userList) {
            System.out.println(user);
        }

    }

    @Test
    public void testFindPage(){
        Map map = new HashMap<String,Integer>();
        map.put("start",0);
        map.put("size",5);
        List<User> userList = userMapper.page(map);
        for (User user: userList) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindPage2(){

        List<User> userList = userMapper.page2(0,4);
        for (User user: userList) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindPage3(){

        List<User> userList = userMapper.page3(0,4);
        for (User user: userList) {
            System.out.println(user);
        }
    }


    @Test
    public void testDelete(){
        userMapper.deleteById(1);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setName("赵四");
        user.setAddress("上海");
        user.setTel("110");
        userMapper.save(user);
        userMapper.findById(user.getId());
        userMapper.update(user);
    }

}
