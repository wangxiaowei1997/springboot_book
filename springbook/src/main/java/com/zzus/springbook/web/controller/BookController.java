package com.zzus.springbook.web.controller;


import com.zzus.springbook.bean.db.Book;
import com.zzus.springbook.bean.dto.RespDTO;
import com.zzus.springbook.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


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
    public RespDTO<List<Book>> listBook() throws  Exception{
        List<Book> bookList = bookService.findBookInfo();
        return RespDTO.success(bookList);
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
