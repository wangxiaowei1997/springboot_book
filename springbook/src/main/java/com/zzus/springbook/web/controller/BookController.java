package com.zzus.springbook.web.controller;


import com.zzus.springbook.entity.Book;
import com.zzus.springbook.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;


/**
 * @author wangwei
 * @create 2018/9/18
 */

@RestController
@RequestMapping(value="/book")
public class BookController {


    @Resource
    private BookService bookService;


    @GetMapping(value = "/list")
    public Collection<Book> listBook() throws  Exception{
        return bookService.findBookInfo();
    }

    @PostMapping(value = "/add")
    public void addBook(@RequestBody Book input) throws Exception{
        bookService.addBookInfo(input);
    }

    @PostMapping(value = "/delete")
    public void deleteBook(@RequestBody Book book ) throws Exception{
        bookService.deleteBookInfo(book);
    }

    @PostMapping(value = "/update")
    public void updateBook(@RequestBody Book book) throws Exception{
        bookService.updateBookInfo(book);
    }
}
