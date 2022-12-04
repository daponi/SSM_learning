package com.atguigu.test;

import com.atguigu.mapper.SelectMapper;
import com.atguigu.pojo.User;
import com.atguigu.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 1.若sql语句查询的结果为多条时，一定不能以实体类类型作为方法的返回值
 * 否则会抛出异常TooManyResultsException
 *
 * 2.若sql语句查询的结果为1条时，此时可以使用实体类类型或list集合类型作为方法的返回值
 *
 * 3.根据用户id查询用户信息为map集合，map集合会以数据库里的字段名为键key，字段值为值value
 */
@Slf4j
public class SelectMapperTest {
    static SqlSession sqlSession=null;
    static SelectMapper mapper=null;

    @Before
    public void getSqlSession(){
        sqlSession = SqlSessionUtil.getSqlSession();
        mapper = sqlSession.getMapper(SelectMapper.class);
    }

    @After
    public void closeSqlSession(){
        sqlSession.close();
    }

    @Test
    public void testSelectOneById(){
        User user = mapper.selectOneById("1");
        log.debug("{}",user);
    }

    @Test
    public void testSelectAll(){
        List<User> user = mapper.selectAll();
        user.forEach(ele->log.debug("{}",ele));
    }

    @Test
    public void testSelectCount(){
        Integer count = mapper.selectCount();
        log.debug("count:{}",count);
    }

    @Test
    public void testSelectOneByIdToMap(){
        Map<String, Object> map = mapper.selectOneByIdToMap("4");
        // {password=123123, gender=男, id=4, age=23, email=1239123@QQ.COM, username=ONE}
        log.debug("{}",map);
    }

    @Test
    public void testSelectAllUserToListMap(){
        List<Map<String, Object>> maps = mapper.selectAllUserToListMap();
        log.debug("{}",maps);
    }

    @Test
    public void testSelectAllUserToMap(){
        Map<String, Object> map = mapper.selectAllUserToMap();
        log.debug("{}",map);
    }
}
