package com.atguigu.test;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.User;
import com.atguigu.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * MyBatis获取参数值的两种方式：#{}和${}：
 *
 * #{}的本质是占位符赋值，${}的本质是字符串拼接
 * 1、若mapper接口方法的参数为单个的字面量类型
 * 此时可以通过#{}和${}以任意的内容获取参数值，一定要注意${}的单引号问题
 *
 *  2、若mapper接口方法的参数为多个的字面量类型
 * 此时MyBatis会将参数放在map集合中，以两种方式存储数据
 *  a>以arg0,arg1...为键，以参数为值
 *  b>以param1,param2...为键，以参数为值
 * 因此，只需要通过#{}和${}访问map集合的键，就可以获取相对应的值,一定要注意${}的单引号问题
 *
 * 3、若mapper接口方法的参数为map集合类型的参数
 * 只需要通过#{}和${}访问map集合的键，就可以获取相对应的值,一定要注意${}的单引号问题
 *
 * 4、若mapper接口方法的参数为实体类类型的参数
 * 只需要通过#{}和${}访问实体类中的属性名，就可以获取相对应的属性值，一定要注意${}的单引号问题
 *
 * 5、可以在mapper接口方法的参数上设置@Param注解
 * 此时MyBatis会将这些参数放在map中，以两种方式进行存储
 *  a>以@Param注解的value属性值为键，以参数为值
 *  b>以param1,param2...为键，以参数为值
 * 只需要通过#{}和${}访问map集合的键，就可以获取相对应的值,一定要注意${}的单引号问题
 */
@Slf4j
public class ParameterTest {

    @Test
    public void testParameter(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User two = mapper.getUserbyUsername("Two");
        log.debug("{}",two);
        sqlSession.close();
    }

    @Test
    public void testCheckLogin(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User two = mapper.checkLogin("Two", "123123");
        log.debug("{}",two);
        sqlSession.close();
    }

    @Test
    public void testCheckLoginByMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "Two");
        map.put("password", "123123");
        User two = mapper.checkLoginByMap(map);
        log.debug("{}",two);
        sqlSession.close();
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null, "Tom", "123123", 22, "男", "123123@qq.com");
        int two = mapper.inserUser(user);
        log.debug("{}",two);
        sqlSession.close();
    }

    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User two = mapper.checkLoginByParam("Tom","123123");
        log.debug("{}",two);
        sqlSession.close();
    }

}
