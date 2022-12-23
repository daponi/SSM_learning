package com.atguigu.www.controller;

import com.atguigu.www.service.BookService;
import com.atguigu.www.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CheckoutService checkoutService;
    public void buyBook(Integer customerId,Integer bookId) {

        bookService.buyBook(customerId, bookId);

    }


    public void checkoutBooks(Integer customerId,Integer[] booksId){
        checkoutService.buyBook(customerId, booksId);
    }
}
