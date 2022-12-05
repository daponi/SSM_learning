package com.atguigu.www.test;

import com.atguigu.www.mapper.DeptMapper;
import com.atguigu.www.mapper.EmpMapper;
import com.atguigu.www.pojo.Dept;
import com.atguigu.www.pojo.Emp;
import com.atguigu.www.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 *字段名和属性名不一致的情况，如何处理映射关系:
 *    1、在SQL语句中为查询的字段设置别名，和属性名保持一致,太麻烦
 *    2、当字段符合MySQL的要求使用_，而属性符合java的要求使用驼峰
 *         此时可以在MyBatis的核心配置文件中settings设置一个全局配置，可以自动将下划线映射为驼峰: emp_id:empId,emp_name:empName
 *    3、使用resultMap自定义映射处理
 *         处理多对一的映射关系：
 *         1、级联方式处理
 *         2、association
 *         3、分步查询
 *
 * 处理一对多的映射关系：
 *         1、collection
 *         2、分步查询
 */
@Slf4j
public class ResultMapTest {
    static SqlSession sqlSession = null;
    static EmpMapper empMapper = null;
    static DeptMapper deptMapper = null;

    @Before
    public void getSqlSession() {
        sqlSession = SqlSessionUtil.getSqlSession();
        empMapper=sqlSession.getMapper(EmpMapper.class);
        deptMapper=sqlSession.getMapper(DeptMapper.class);
    }

    @After
    public void closeSqlSeesion(){
        sqlSession.close();
    }

    @Test
    public void testGetEmpByEmpId(){
        List<Emp> empByEmpId = empMapper.getEmpByEmpId(3);
        empByEmpId.forEach(ele->log.debug("{}",ele));
    }

    @Test
    public void testGetEmpAndDeptByEmpId(){
        List<Emp> empList = empMapper.getEmpAndDeptByEmpId(1);
        empList.forEach(ele->log.debug("结果:{}",ele));
    }

    @Test
    public void testGetDeptByDeptId(){
        Dept dept = deptMapper.getByDeptId(1);
        log.debug(dept.toString());
    }

    @Test
    public void testGetEmpAndDeptByEmpIdByStep(){
        List<Emp> empList = empMapper.getEmpAneDeptByEmpIDByStep(5);
        empList.forEach(ele->log.debug("结果:{}",ele.getEmpName()));
    }

    @Test
    public void testGetDeptAndEmpByDeptId(){
        Dept dept = deptMapper.getDeptAndEmpByDeptId(1);
        log.debug("结果:{}",dept);
    }

    @Test
    public void testGetDeptAndEmpByStep(){
        Dept dept = deptMapper.getDeptAndEmpByStepOne(1);
        log.debug("结果:{}",dept);
    }

}
