package com.atguigu.www.test;

import com.atguigu.www.mapper.EmpMapper;
import com.atguigu.www.pojo.Emp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class PageTest {
    @Test
    public void testPatePlugin() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession(true);
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        // 查询功能之前开启分页功能
        Page<Object> page = PageHelper.startPage(2, 4);
        List<Emp> emps =mapper.selectByExample(null);
        //查询功能之后可以获取分页相关的所有数据
        PageInfo<Emp> empPageInfo = new PageInfo<>(emps, 5);//以list为数据，导航显示5页
        emps.forEach(ele->log.debug("{}",ele));
        log.debug("分页信息：{}",empPageInfo);
    }
}
