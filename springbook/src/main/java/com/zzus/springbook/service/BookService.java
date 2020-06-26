package com.zzus.springbook.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzus.springbook.entity.Book;
import com.zzus.springbook.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author wangwei
 * @date 2018/9/23
 */
@Service
public class BookService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private BookMapper mapper;

    private final String BOOK_LIST_KEY = "bookList";
    /**
     * 列出图书列表
     * @return 图书实体类
     * @throws Exception
     */
    public Collection<Book> findBookInfo() throws Exception {
        String bookList = stringRedisTemplate.opsForValue().get(BOOK_LIST_KEY);
        if (bookList == null) {
            Collection<Book> bookCollection = mapper.findBookInfo();
            JSONArray array = new JSONArray();
            for(Object o:bookCollection){
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(o));
                array.add(jsonObject);
            }
            String arrayString = array.toJSONString();
            stringRedisTemplate.opsForValue().set(BOOK_LIST_KEY,arrayString,600, TimeUnit.SECONDS);
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

    /**
     * 添加图书
     * @throws Exception
     * @param book
     */
    public void addBookInfo(Book book) throws Exception {
        deleteRedisKey(BOOK_LIST_KEY);
        mapper.addBookInfo(book);
    }

    /**
     * 删除图书
     * @throws Exception
     * @param book
     */
    public void deleteBookInfo(Book book) throws Exception {
        deleteRedisKey(BOOK_LIST_KEY);
        mapper.deleteBookInfo(book);
    }

    /**
     * 更新图书信息
     * @param book
     * @throws Exception
     */
    public void updateBookInfo(Book book) throws  Exception{
        deleteRedisKey(BOOK_LIST_KEY);
        mapper.updateBookInfo(book);

    }

    /**
     * 删除特定的键值对
     */
    private void deleteRedisKey(String key){
        stringRedisTemplate.delete(BOOK_LIST_KEY);
    }
}
