package com.atguigu.www.test;


import com.atguigu.www.mapper.UserMapper;
import com.atguigu.www.pojo.User;
import com.atguigu.www.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * sqlSession：代表Java程序和数据库之间的会话。（HttpSession是Java程序和浏览器之间的会话）
 * SqlSessionFactory：是“生产”SqlSession的“工厂”。
 * 工厂模式：如果创建某一个对象，使用的过程基本固定，那么我们就可以把创建这个对象的相关代码封装到一个“工厂类”中，以后都使用这个工厂类来“生产”我们需要的对象。
 */
@Slf4j
public class MyBatisTest {
    @Test
    public void testInsert() throws IOException {
        //1.获取MyBatis核心配置文件的输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");

        //2.获取sqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        //3.获取sqlsessionFactory对象，工厂模式，通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);

        //4.获取sql的会话对象sqlSession,是myBatis提供的操作数据库对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        SqlSession sqlSession = build.openSession();
        // SqlSession sqlSession = build.openSession(true);//类似mysql每次提交后自动commit

        //5.获取userMapper的代理实现类对象，通过代理模式创建UserMapper接口的代理实现类对象
        /**
         * sqlSession.getMapper()传进一个，class对象，返回该class的实例化对象。
         * 如，传User.clss，返回的是该User类的对象
         * 而传入一个接口的class，则会通过代理实现类实现该接口再返回其实例化对象，使用了代理模式
         * 是通过该接口根据命名规范找到其映射文件xxxMapper.xml，在映射文件里找到映射语句，并将结果返回
         *
         * 接口不能实例化对象，但我们现在需要接口里的方法，所有使用代理实现类，我们就可以通过这个对象帮我们实现接口并实例化其对象再访问到接口里面的方法，就可以调用了
         */
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用UserMapper接口中的方法，就可以根据UserMapper的全类名匹配元素文件，通过调用的方法名匹配映射文件中的SQL标签，并执行标签中的SQL语句
        int result = mapper.insertUser();
        //也可以直接使用sqlSession直接执行Mapper文件
        int insert = sqlSession.insert("com.atguigu.www.mapper.UserMapper.insertUser");

        log.debug("结果：{}",result);

        //6.提交事务
        sqlSession.commit();

        //7.关闭会话，一个会话持有一个链接会话是一个过程
        sqlSession.close();

    }



    @Test
    public void testDelete(){

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.deleteUser();
        log.debug("结果:{}",result);
        sqlSession.close();
    }

    @Test
    public void testUpdate(){

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.updateUser();
        log.debug("结果:{}",result);
        sqlSession.close();
    }

    @Test
    public void testGetOneUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User oneUser = mapper.getOneUser();
        log.debug("{}", oneUser);
    }

    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(user->log.debug("{}",user));
    }

}
