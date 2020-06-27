package com.zzus.springbook.mapper;

import com.zzus.springbook.bean.db.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookMapper {
    public Collection<Book> findBookInfo();

    public void addBookInfo(Book book);

    public void  deleteBookInfo(Book book);

    public void  updateBookInfo(Book book);
}
