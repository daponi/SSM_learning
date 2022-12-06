package com.atguigu.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获得SqlSession的工具类
 */
public class SqlSessionUtil {
    public static SqlSession getSqlSession(){
        SqlSession sqlsession=null;
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
            sqlsession = factory.openSession(true); //自动commit

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sqlsession;
    }
}
