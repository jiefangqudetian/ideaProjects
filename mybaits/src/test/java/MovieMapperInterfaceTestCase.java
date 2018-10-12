import com.kaishengit.entity.Movie;
import com.kaishengit.entity.User;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MovieMapperInterfaceTestCase {

    private SqlSession sqlSession;
    private MovieMapper movieMapper;

    @Before
    public void init(){
        //自动提交事务
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        //动态代理 动态生成
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }

    @After
    public void destory(){
        // 关闭sqlSession
        sqlSession.close();
    }

   @Test
    public void testFindList(){
        String title = "%西游%";
        List<Movie> movieList = movieMapper.findList(title);
        for (Movie movie:movieList){
            System.out.println(movie);
        }
   }

   @Test
    public void testFindByParams(){
        String title = "";
        String director = "%詹姆斯·卡梅隆%";
        Map<String,Object> params = new HashMap<>();
        params.put("title",title);
        params.put("director",director);
        List<Movie> movieList = movieMapper.findByParams(params);
        for (Movie movie:movieList){
            System.out.println(movie);
        }
   }

   @Test
    public void testFindByIdList(){
        List<Integer> idList = Arrays.asList(102,103,104);
        List<Movie> movieList = movieMapper.findByIdList(idList);
        for (Movie movie:movieList){
            System.out.println(movie);
        }
   }

   @Test
    public void testInsertBatch(){
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.setTitle("千与千寻");
        movie.setDirector("宫崎骏");
        movie.setRate(9.1F);

        Movie movie1 = new Movie();
        movie1.setTitle("龙猫");
        movie1.setDirector("宫崎骏");
        movie1.setRate(8.9F);

        movieList.add(movie);
        movieList.add(movie1);
        movieMapper.insertBatch(movieList);
   }

  /* @Test
    public void testFindByReleaseyear(){
        List<Movie> movieList = movieMapper.findByReleaseyear("2000",1,10);
        for (Movie movie:movieList){
            System.out.println(movie);
        }
   }*/

  @Test
  public void testFindByRelyear(){

      Map<String,Object> param = new HashMap<String, Object>();
      param.put("releaseYear","2000");
      param.put("pageNum",1);
      param.put("pageSize",10);
      List<Movie> movieList = movieMapper.findByRelyear(param);
      for (Movie movie:movieList){
          System.out.println(movie);
      }

  }

  @Test
  public  void  testFindById(){
      Movie movie = movieMapper.findById(105);
      System.out.println(movie);
  }

}
