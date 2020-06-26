package com.zzus.springbook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;


/**
 * @author wangwei
 * @date 2018/9/23
 */
@Data
public class Book {

    @JsonProperty(value = "book_id")
    private int bookId;

    @JsonProperty(value = "book_name")
    private String bookName;

    @JsonProperty(value = "book_price")
    private double bookPrice;

    @JsonProperty(value = "date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+16")
    private Date date;

    @JsonProperty(value = "id")
    private int id;

}
