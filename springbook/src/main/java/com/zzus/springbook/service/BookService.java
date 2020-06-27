package com.zzus.springbook.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzus.springbook.bean.db.Book;
import com.zzus.springbook.mapper.BookMapper;
import com.zzus.springbook.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangwei
 * @date 2018/9/23
 */
@Slf4j
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
    public List<Book> findBookInfo() throws Exception {
        List<Book> list;
        String bookList = stringRedisTemplate.opsForValue().get(BOOK_LIST_KEY);
        if (bookList == null) {
            list = mapper.findBookInfo();
            JSONArray array = new JSONArray();
            for(Object o:list){
                JSONObject jsonObject = BeanCopyUtils.map(o,JSONObject.class);
                array.add(jsonObject);
            }
            String arrayString = array.toJSONString();
            stringRedisTemplate.opsForValue().set(BOOK_LIST_KEY,arrayString,120, TimeUnit.SECONDS);
        }else {
            JSONArray array = JSONArray.parseArray(bookList);
            list = BeanCopyUtils.mapList(array,Book.class);
        }
        log.info("list={}",list);
        return list;
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
     * 缓存失效
     */
    private void deleteRedisKey(String key){
        log.info("缓存失效 key={}",key);
        stringRedisTemplate.delete(BOOK_LIST_KEY);
    }
}
