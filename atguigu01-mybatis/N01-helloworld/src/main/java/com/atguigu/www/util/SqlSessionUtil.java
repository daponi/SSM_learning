package com.atguigu.www.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获得SqlSession的工具类
 * <p>
 * 这个函数用于获取SqlSession对象，它首先加载mybatis-config.xml文件，然后使用加载的配置信息创建一个SqlSessionFactory对象，
 * 并利用该工厂对象创建一个SqlSession对象。
 * 这个SqlSession对象是自动提交的，即每次执行SQL操作后会自动提交。最后将SqlSession对象返回
 */
public class SqlSessionUtil {
    public static SqlSession getSqlSession() {
        SqlSession sqlsession = null;
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
            sqlsession = factory.openSession(true); //自动commit

            resourceAsStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sqlsession;
    }

    public static void closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /**
     * 改善getSqlSession方法，使用try-with-resource语句自动关闭资源
     * 获取SqlSession会话对象
     *
     * @return 返回SqlSession对象
     */
    public static SqlSession getSqlSession2() {
        String xml = "mybatis-config.xml";
        // 使用try-with-resources块确保资源自动关闭
        try (InputStream resourceAsStream = Resources.getResourceAsStream(xml)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            // 自动commit，通过SqlSession对象所操作的sql都必须手动提交或回滚事务
            return sqlSessionFactory.openSession(true);

        } catch (IOException e) {
            // 可能需要更具体的异常类型，如FileNotFoundException
            throw new RuntimeException("无法加载或解析" + xml, e);
        }
    }


}
