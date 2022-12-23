package com.atguigu.www.service.impl;

import com.atguigu.www.dao.BookDao;
import com.atguigu.www.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 *  认识@Transactional的属性
 *
 *
 *  可以通过@Transactional中的propagation属性设置事务传播行为
 * 修改BookServiceImpl中buyBook()上，注解@Transactional的propagation属性
 * @Transactional(propagation = Propagation.REQUIRED)，默认情况，表示如果当前线程上有已经开启的事务可用，那么就在这个原事务中运行。
 * 经过观察，购买图书的方法buyBook()在checkout()中被调用，checkout()上有事务注解，因此在此事务中执行。所购买的两本图书的价格为80和50，而用户的余额为100，因此在购买第二本图书时余额不足失败，导致整个checkout()回滚，即只要有一本书买不了，就都买不了
 * @Transactional(propagation = Propagation.REQUIRES_NEW)，表示不管当前线程上是否有已经开启的事务，都要开启新事务。同样的场景，每次购买图书都是在buyBook()的事务中执行，因此第一本图书购买成功，事务结束，第二本图书购买失败，只在第二次的buyBook()中回滚，购买第一本图书不受影响，即能买几本就买几本
 */
@SuppressWarnings("all")
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    // @Transactional(timeout = 3,readOnly = true)
    // @Transactional(noRollbackFor = ArithmeticException.class)
    @Transactional(noRollbackForClassName = "java.lang.ArithmeticException",
                    propagation = Propagation.REQUIRES_NEW )
    public void buyBook(Integer customerId, Integer bookId) {
        // try {
        //     TimeUnit.SECONDS.sleep(5);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }

        //查询图书的价格
        Integer price = bookDao.getPriceByBookId(bookId);
        //更行图书的库存
        bookDao.updateStock(bookId);
        //更新用户的余额
        bookDao.updateBalance(customerId, price);
        // System.out.println(10/0);

    }
}
