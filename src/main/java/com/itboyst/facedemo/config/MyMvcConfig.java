package com.itboyst.facedemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("toImageDemo").setViewName("imageDemo");
        registry.addViewController("student").setViewName("studentLogin");//设置学生端的访问路径
        registry.addViewController("teacher").setViewName("teacherLogin");//设置教师端的访问路径
        registry.addViewController("demo").setViewName("demo");
        registry.addViewController("auto").setViewName("autoSignIn");
        registry.addViewController("info").setViewName("informationPanel");

    }
}
