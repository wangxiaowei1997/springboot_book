package com.zzus.springbook.bean.db;

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

    private int bookId;

    private String bookName;

    private double bookPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+16")
    private Date date;

    private int id;

}
