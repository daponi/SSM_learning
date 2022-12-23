package com.atguigu.www.service.impl;

import com.atguigu.www.service.BookService;
import com.atguigu.www.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private BookService bookService;

    /**
     * 一次购买多本数
     * @param customerId
     * @param booksId
     */

    @Override
    @Transactional
    public void buyBook(Integer customerId, Integer[] booksId) {
        for (Integer bookId : booksId) {
            bookService.buyBook(customerId, bookId);
        }
    }
}
