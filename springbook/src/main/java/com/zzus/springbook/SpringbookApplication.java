package com.zzus.springbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;



@CrossOrigin
@SpringBootApplication
@MapperScan("com.zzus.springbook.mapper")
@ServletComponentScan
public class SpringbookApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringbookApplication.class, args);
    }
}
