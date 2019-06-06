package com.zzus.springbook.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzus.springbook.entity.Book;
import com.zzus.springbook.mapper.BookMapper;
import com.zzus.springbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String bookList = stringRedisTemplate.opsForValue().get("bookList");
        if (bookList == null) {
            Collection<Book> bookCollection = mapper.findBookInfo();
            JSONArray array = new JSONArray();
            for(Object o:bookCollection){
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(o));
                array.add(jsonObject);
            }
            String arrayString = array.toJSONString();
            stringRedisTemplate.opsForValue().set("bookList",arrayString,60);
            return bookCollection;
        }else {
            JSONArray array = JSONArray.parseArray(bookList);
            Collection<Book> bookCollection = new ArrayList<>();
            for(Object o:array){
                JSONObject jsonObject =(JSONObject)o;
                Book book = JSON.parseObject(jsonObject.toJSONString(),Book.class);
                bookCollection.add(book);
            }
            return bookCollection;
        }
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
