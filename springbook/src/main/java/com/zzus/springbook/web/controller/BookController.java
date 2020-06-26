package com.zzus.springbook.web.controller;


import com.zzus.springbook.entity.Book;
import com.zzus.springbook.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;


/**
 * @author wangwei
 * @create 2018/9/18
 */
@Slf4j
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
    public void addBook(@RequestBody Book book) throws Exception{
        log.info("新增书籍book={}",book);
        bookService.addBookInfo(book);
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
