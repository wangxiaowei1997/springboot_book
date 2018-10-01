package com.zzus.springbook.mapper;

import com.zzus.springbook.entity.Book;

import java.util.Collection;

public interface BookMapper {
    public Collection<Book> findBookInfo();

    public void addBookInfo(Book book);

    public void  deleteBookInfo(Book book);

    public void  updateBookInfo(Book book);
}
