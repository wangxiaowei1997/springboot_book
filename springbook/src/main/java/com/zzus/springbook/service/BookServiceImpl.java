package com.zzus.springbook.service;


import com.zzus.springbook.entity.Book;
import com.zzus.springbook.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author wangwei
 * @date 2018/9/23
 */
@Service
public class BookServiceImpl implements BookService {
    private BookMapper mapper;

    public BookServiceImpl(BookMapper mapper) {
        this.mapper = mapper;
    }


    public Collection<Book> findBookInfo() throws Exception {

        return mapper.findBookInfo();
    }

    public void addBookInfo(Book book) throws Exception {

        mapper.addBookInfo(book);
    }

    public void deleteBookInfo(Book book) throws Exception {
        mapper.deleteBookInfo(book);
    }

    public void updateBookInfo(Book book) throws  Exception{

        mapper.updateBookInfo(book);

    }
}
