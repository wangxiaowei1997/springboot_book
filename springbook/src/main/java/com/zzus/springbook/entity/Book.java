package com.zzus.springbook.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author wangwei
 * @date 2018/9/23
 */

public class Book {

   //参数

//    @JsonProperty(value = "book_id")
    private int bookId;

//    @JsonProperty(value = "book_name")
    private String bookName;

//    @JsonProperty(value = "book_price")
    private double bookPrice;

//    @JsonProperty(value = "date")
    private String date;

//    @JsonProperty(value = "id")
    private int id;

   //get方法

    public int getBookId(){
       return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public String getDate() {
        return date;
    }

    //set方法

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
