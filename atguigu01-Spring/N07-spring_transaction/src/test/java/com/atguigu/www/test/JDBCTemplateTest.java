package com.atguigu.www.test;

import com.atguigu.www.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


//指定当前测试类在Spring的测试环境中执行，此时就可以通过注入的方式直接获取IOC容器中bean
@RunWith(SpringJUnit4ClassRunner.class)
//设置Spring测试环境的配置文件
@ContextConfiguration("classpath:spring-JdbcTemplate.xml")
public class JDBCTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 测试增删改功能
     */
    @Test
    public void testUpdate(){
        String sql= " insert into t_user values(null,?,?,?,?,?)";
        jdbcTemplate.update(sql,"lisi","123123",23,"男","123111@qq.com");
    }

    /**
     * 查询一条数据为实体类对象
     */
    @Test
    public void testSeleteEmpById(){
        String sql = "select * from t_emp where emp_id=?";
        Emp emp = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class), 1);
        System.out.println(emp);
    }

    /**
     * 查询多条数据为一个list集合
     */
    @Test
    public void testSelectList(){
        String sql = "select * from t_emp";
        List<Emp> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
        query.forEach(ele-> System.out.println(ele));

    }

    /**
     * 查询单行单列的值
     */
    @Test
    public void selectCount(){
        String sql = "select count(emp_id) from t_emp ";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(integer);
    }

    /**
     * 删除一条数据
     */
    @Test
    public void deleteOne(){
        String sql="delete from t_emp where emp_id = ?";
        int update = jdbcTemplate.update(sql, 5);
    }
}
