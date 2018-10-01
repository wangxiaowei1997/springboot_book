package com.zzus.springbook.service;



import com.zzus.springbook.entity.Book;

import java.util.Collection;

/**
 * @author wangwei
 * @date 2018/9/23
 *
 */
public interface BookService {
    /**
     * 列出图书列表
     * @return 图书实体类
     * @throws Exception
     */
    public Collection<Book> findBookInfo() throws Exception;

    /**
     * 添加图书
     * @throws Exception
     * @param book
     */
    public void addBookInfo(Book book) throws Exception;

    /**
     * 删除图书
     * @throws Exception
     * @param book
     */
    public void deleteBookInfo(Book book) throws Exception;

    /**
     * 更新图书信息
     * @param book
     * @throws Exception
     */
    public void updateBookInfo(Book book) throws Exception;
}
