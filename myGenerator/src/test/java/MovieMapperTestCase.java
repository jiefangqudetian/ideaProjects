import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.entity.MovieExample;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MovieMapperTestCase {

    private SqlSession sqlSession;
    private MovieMapper movieMapper;

    @Before
    public void init(){
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }
    @After
    public void destory(){
        sqlSession.close();
    }
    @Test
    public void testInsert(){
        Movie movie = new Movie();
        movie.setTitle("有话好好说");
        movie.setDirector("张艺谋");
        movie.setRate(9.1F);
        movieMapper.insertSelective(movie);
        movieMapper.insert(movie);
        /*有选择插入，只插入实体类中有的，无选择插入，实体类属性全部插入，不管是否为null*/
    }

    @Test
    public void testDelete(){
        MovieExample example = new MovieExample();
        example.createCriteria().andDirectorEqualTo("张艺谋").andIdEqualTo(781);
        movieMapper.deleteByExample(example);
        /*deleteByExample通过其他条件删除，deleteByPrimaryId通过主键删除*/
    }
    @Test
    public void testUpdate(){
        MovieExample example = new MovieExample();
        example.createCriteria().andDirectorEqualTo("张艺谋");


        List<Movie> movieList=movieMapper.selectByExample(example);

        for (Movie movie:movieList){
            System.out.println(movie);
        }
        Movie movie = new Movie();
        movie.setReleaseYear("1999");
        movie.setDescription("姜文，翟营");
        //movieMapper.updateByExample(movie,example);

        movieMapper.updateByExampleSelective(movie,example);
    }

    @Test
    public void testFindor(){
        MovieExample example = new MovieExample();
        example.or().andRateBetween(8.5F,9.0F);
        example.or().andReleaseYearBetween("2009","2017");
        List<Movie> movieList = movieMapper.selectByExample(example);
        for (Movie movie:movieList){
            System.out.println(movie);
        }
    }

    @Test
    public void testFindAnd(){
        //PageHelper.startPage(5,10);
        // 第一个参数为起始页，第二个参数为每页多少个
        PageHelper.offsetPage(19,8);
        //从第几条开始，数多少条,每页多少条
        MovieExample example = new MovieExample();
        example.createCriteria().andReleaseYearBetween("2009","2017").andRateBetween(8.5F,9.0F);
        example.setOrderByClause("id asc");//所有数据升序排列
        List<Movie> movieList = movieMapper.selectByExample(example);
        for (Movie movie:movieList){
            System.out.println(movie);
        }

        PageInfo<Movie> page = new PageInfo<>(movieList);
        System.out.println(page.getSize());
        System.out.println(page.getPages());
    }


    public void testPage(){

        List<Movie> movieList ;
    }
}
