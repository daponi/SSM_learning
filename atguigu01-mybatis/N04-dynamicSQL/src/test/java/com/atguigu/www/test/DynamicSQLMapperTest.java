package com.atguigu.www.test;

import com.atguigu.www.mapper.DynamicSQLMapper;
import com.atguigu.www.pojo.Emp;
import com.atguigu.www.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 动态SQL的测试类
 */
@Slf4j
public class DynamicSQLMapperTest {
    static SqlSession sqlSession =null;
    DynamicSQLMapper mapper=null;

    @Before
    public void getSQLSession(){
        sqlSession = SqlSessionUtil.getSqlSession();
        mapper = sqlSession.getMapper(DynamicSQLMapper.class);
    }
    @After
    public void closeSQLSession(){
        sqlSession.close();
    }

    @Test
    public void testGetDeptByCondition(){
        List<Emp> empList = mapper.getEmpByCondition(new Emp(null, "AAA", 24, "女"));
        empList.forEach(ele->{log.debug("{}",ele);});
    }

    @Test
    public void testInsertEmpBatch(){
        Emp emp = new Emp(null, "明", 20, "男");
        Emp emp2 = new Emp(null, "明2", 22, "男");
        Emp emp3 = new Emp(null, "明3", 23, "男");
        List<Emp> emps = Arrays.asList(emp, emp2, emp3);
        int result = mapper.insertEmpBatch(emps);
        log.debug("{}",result);
    }

    @Test
    public void testDeleteEmpBatch(){
        Integer[] ids = {16,17,18};
        int result = mapper.deleteEmpBatch(ids);
        log.debug("{}",result);
    }
}
