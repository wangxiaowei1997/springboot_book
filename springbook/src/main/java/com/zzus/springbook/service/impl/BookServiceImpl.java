package com.zzus.springbook.service.impl;


import com.zzus.springbook.entity.Book;
import com.zzus.springbook.mapper.BookMapper;
import com.zzus.springbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author wangwei
 * @date 2018/9/23
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BookMapper mapper;



    @Override
    public Collection<Book> findBookInfo() throws Exception {
        stringRedisTemplate.opsForValue();
        return mapper.findBookInfo();
    }

    @Override
    public void addBookInfo(Book book) throws Exception {

        mapper.addBookInfo(book);
    }

    @Override
    public void deleteBookInfo(Book book) throws Exception {
        mapper.deleteBookInfo(book);
    }

    @Override
    public void updateBookInfo(Book book) throws  Exception{

        mapper.updateBookInfo(book);

    }
}
