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
    public RespDTO addBook(@RequestBody Book book) throws Exception{
        log.info("新增书籍book={}",book);
        bookService.addBookInfo(book);
        return RespDTO.success("新增书籍成功");
    }

    @PostMapping(value = "/delete")
    public RespDTO deleteBook(@RequestBody Book book ) throws Exception{
        log.info("删除书籍book={}",book);
        bookService.deleteBookInfo(book);
        return RespDTO.success("删除书籍成功");
    }

    @PostMapping(value = "/update")
    public RespDTO updateBook(@RequestBody Book book) throws Exception{
        log.info("更新书籍book={}",book);
        bookService.updateBookInfo(book);
        return RespDTO.success("更新书籍成功");
    }
}
