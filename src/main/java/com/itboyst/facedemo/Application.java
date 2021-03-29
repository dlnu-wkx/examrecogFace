package com.itboyst.facedemo;

import com.itboyst.facedemo.base.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Import(SpringUtil.class)
@MapperScan({"com.itboyst.facedemo.dao.mapper", "com.itboyst.facedemo.mapper"})
@EnableTransactionManagement

public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(Application.class);
    }



}

   