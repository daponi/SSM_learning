package com.atguigu.www.test;


import com.atguigu.www.mapper.EmpMapper;
import com.atguigu.www.pojo.Emp;
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
 * 简单测试生成的方法
 * selective结尾命名是SQL操作非null字段，Example结尾命名是指条件
 */
@Slf4j
public class EmpMapperTest {
    @Test
    public void testMBG() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据，null为条件则是查询全部
           /* List<Emp> list = mapper.selectByExample(null); list.forEach(emp -> System.out.println(emp));*/

            //根据条件查询
            /*EmpExample example = new EmpExample();

            example.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThanOrEqualTo(20); example.or().andDidIsNotNull();
            List<Emp> list = mapper.selectByExample(example); list.forEach(emp -> System.out.println(emp));*/

            mapper.updateByPrimaryKeySelective(new Emp(20, "admin", 22, null, 3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
