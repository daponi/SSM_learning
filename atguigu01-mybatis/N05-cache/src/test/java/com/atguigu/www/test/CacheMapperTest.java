package com.atguigu.www.test;

import com.atguigu.www.mapper.CacheMapper;
import com.atguigu.www.pojo.Emp;
import com.atguigu.www.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * MyBatis的一级缓存：
 * MyBatis的一级缓存是SqlSession级别的，即通过同一个SqlSession查询的数据会被缓存
 * 再次使用同一个SqlSession查询同一条数据，会从缓存中获取
 * 使一级缓存失效的四种情况：
 * 1) 不同的SqlSession对应不同的一级缓存
 * 2) 同一个SqlSession但是查询条件不同
 * 3) 同一个SqlSession两次查询期间执行了任何一次增删改操作
 * 4) 同一个SqlSession两次查询期间手动清空了缓存
 *
 * MyBatis的二级缓存：
 * MyBatis的二级缓存是SqlSessionFactory级别的，即通过同一个SqlSessionFactory所获取的SqlSession对象
 * 查询的数据会被缓存，在通过同一个SqlSessionFactory所获取的SqlSession查询相同的数据会从缓存中获取
 * MyBatis二级缓存开启的条件：
 * a>在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为true，不需要设置
 * b>在映射文件中设置标签<cache/>
 * c>二级缓存必须在SqlSession关闭或提交之后有效
 * d>查询的数据所转换的实体类类型必须实现序列化的接口
 * 使二级缓存失效的情况：
 * 两次查询之间执行了任意的增删改，会使一级和二级缓存同时失效
 *
 * 整合第三方缓存EHCache，效果一样只是使用了第三方的缓存，日志打印和性能有所不同
 */

@Slf4j
public class CacheMapperTest {
    static SqlSession sqlSession=null;
    static CacheMapper mapper=null;
    @Before
    public void getSqlSession(){
        sqlSession = SqlSessionUtil.getSqlSession();
        mapper = sqlSession.getMapper(CacheMapper.class);
    }
    @After
    public void closeSqlSession(){
        sqlSession.close();
    }

    /**
     * mybatis一级缓存失效SqlSession
     */
    @Test
    public void testGetEmpById(){
        Emp emp = mapper.getEmpById(20);
        Emp emp2 = mapper.getEmpById(20);
        log.debug("输出1：{}",emp);
        mapper.inertEmp(new Emp(null,"红",22,"男",null));
        log.debug("输出2：{}",emp2);
        SqlSession sqlSession1 = SqlSessionUtil.getSqlSession();
        CacheMapper mapper2 = sqlSession1.getMapper(CacheMapper.class);
        // sqlSession1.clearCache();// 手动清除缓存
        Emp emp3 = mapper2.getEmpById(20);
        log.debug("{}",emp3);
    }

    /**
     * 测试二级缓存SqlSessionFactory
     */
    @Test
    public void testGetEmpByIdTwo() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session1 = factory.openSession(true);
        SqlSession session2 = factory.openSession(true);
        CacheMapper mapper1 = session1.getMapper(CacheMapper.class);
        CacheMapper mapper2 = session2.getMapper(CacheMapper.class);
        log.debug("输出1：{}",mapper1.getEmpById(24));
        session1.close();
        log.debug("输出2：{}",mapper2.getEmpById(24));
    }
}
