import com.kaishengit.entity.Tag;
import com.kaishengit.mapper.TagMapper;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class TagMapperInterfaceTestCase {

    private SqlSession sqlSession;
    private TagMapper tagMapper;

    @Before
    public void init(){
        //自动提交事务
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        //动态代理 动态生成
        tagMapper = sqlSession.getMapper(TagMapper.class);
        System.out.println(1);
    }

    @After
    public void destory(){
        // 关闭sqlSession
        sqlSession.close();
    }


    @Test
    public void testFindByStudentId(){
        List<Tag> tagList = tagMapper.findByStudentId(1);
        for (Tag tag:tagList){
            System.out.println(tag);
        }

    }

}
