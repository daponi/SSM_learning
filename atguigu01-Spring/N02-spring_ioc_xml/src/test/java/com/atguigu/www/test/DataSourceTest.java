package com.atguigu.www.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
public class DataSourceTest {
    @Test
    public void testDataSource() throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-dataSource.xml");
        DataSource bean = applicationContext.getBean(DataSource.class);
        log.debug("是否连接{}",bean.getConnection());
    }
}
