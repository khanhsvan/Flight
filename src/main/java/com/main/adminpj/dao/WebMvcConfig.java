package com.main.adminpj.dao;

import com.main.adminpj.service.LoginInterceptor;
import com.main.adminpj.service.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("com.main.adminpj.service")
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    protected LoginInterceptor loginInterceptor;

    @Autowired
    protected SessionInterceptor sessionInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/logout","/");
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/dashboard/**")
                .excludePathPatterns("/login", "/logout");

    }
}