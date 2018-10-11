package com.zzus.springbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@CrossOrigin
@SpringBootApplication
@MapperScan("com.zzus.springbook.mapper")
public class SpringbookApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringbookApplication.class, args);
    }
}
