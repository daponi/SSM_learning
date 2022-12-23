package com.atguigu.www.dao.impl;

import com.atguigu.www.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer getPriceByBookId(Integer bookId) {
        String sql = "select price from t_book where book_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,bookId);
    }

    @Override
    public void updateStock(Integer bookId) {
        String sql = "update t_book set stock=stock-1 where book_id = ? ";
        jdbcTemplate.update(sql,bookId);
    }

    @Override
    public void updateBalance(Integer customerId, Integer price) {
        String sql ="update t_customer set balance=balance-? where customer_id=? ";
        jdbcTemplate.update(sql, price,customerId);
    }
}
