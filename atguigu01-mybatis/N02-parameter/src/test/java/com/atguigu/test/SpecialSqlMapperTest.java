package com.atguigu.test;

import com.atguigu.mapper.SpecialSqlMapper;
import com.atguigu.pojo.User;
import com.atguigu.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;

/**
 * 特殊SQL的执行：
 *
 * 1.模糊查询like的三种办法：
 * 1. like ‘%${xxx}%’ ，但有SQL注入的风险
 * 2. like concat(‘%’,#{xxx},’’%)，使用Mysql/Oracle内置的字符串拼接函数，但项目更换数据库时可能出现问题，且不会走索引
 * 3. leke “%” #{xxx} “%” ，推荐使用
 *
* 2.批量删除in不能用#{xxx},用${xxx}~
 * 或者替换成mybatis框架自带的foreach循环
 *
 * 3.添加功能获取自增的主键,在xxxmapper.xml文件中设置：
 *  	useGeneratedKeys：设置使用自增的主键
 *  	keyProperty：因为增删改有统一的返回值是受影响的行数，因此只能将获取的自增的主键放在传输的参数user对象的某个属性中
 */
@Slf4j
public class SpecialSqlMapperTest {
    static SqlSession sqlSession = null;
    static SpecialSqlMapper mapper = null;

    @Before
    public void getSqlSession() {
        sqlSession = SqlSessionUtil.getSqlSession();
        mapper = sqlSession.getMapper(SpecialSqlMapper.class);
    }

    @After
    public void closeSqlSession() {
        sqlSession.close();
    }


    @Test
    public void testSelectByLike() {
        List<User> list = mapper.selectByLike("T");
        list.forEach(t->log.debug("{}", t));

    }

    /**
     * mybatis内部封装的就是JDBC，所有很多功能可以从JDBC中看出来
     * 1.查看占位符?写入引号时‘’再传参会报异常
     * 2.查看insert数据时，返回其自动生成的主键
     */
    @Test
    public void testJDBC() {
        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            Connection connection = DriverManager.getConnection("url", "root", "123123");
            // 执行SQL
            // PreparedStatement preparedStatement = connection.prepareStatement("select * from t_user where username like '%?%'");//查看占位符?写入引号时‘’再传参会报异常
            String sql ="select * from t_user where username like '%?%'";
            String sql2 ="insert t_user values(null,null,'123123')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();//执行SQL
            ResultSet resultSet = preparedStatement.getGeneratedKeys();//获得生成的key,返回的是个单行单列的结果集
            resultSet.next();//结果集指向下一个数据
            int key = resultSet.getInt(1);//结果集获取列好为1的数据,即返回的key


            //
            // preparedStatement.setString(1, "a"); //报错，Cannot resolve query parameter '1'，即证明写在引号里的不是占位符而是字符串

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeletBatch(){
        int i = mapper.deleteBatch("6,8");
        log.debug("{}",i);
    }

    @Test
    public void testInserUserAndGetId(){
        User user = new User(null, "Jerry2", "123123", 22, "男", "123123.qq.com");
        log.debug("{}",user);
        int i = mapper.insertUserAndGetId(user);
        log.debug("{}",i);
        log.debug("{}",user);
    }
}
