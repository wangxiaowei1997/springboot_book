package com.zzus.springbook.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zzus.springbook.entity.Book;
import com.zzus.springbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * @author wangwei
 * @create 2018/9/18
 */

@RestController
@RequestMapping(value="/book")
public class BookController {

    private static final String APPLICATION_JSON = "application/json";
    @Autowired
    private BookService bookService;
    @CrossOrigin
    @RequestMapping(value = "/list",method = RequestMethod.GET,produces = APPLICATION_JSON)
    public Collection<Book> listBook() throws  Exception{
        return bookService.findBookInfo();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = APPLICATION_JSON)
    public void addBook(@RequestBody Book input) throws Exception{
        bookService.addBookInfo(input);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = APPLICATION_JSON )
    public void deleteBook(@RequestBody Book book ) throws Exception{
        bookService.deleteBookInfo(book);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = APPLICATION_JSON)
    public void updateBook(@RequestBody Book book) throws Exception{
        bookService.updateBookInfo(book);
    }
}
