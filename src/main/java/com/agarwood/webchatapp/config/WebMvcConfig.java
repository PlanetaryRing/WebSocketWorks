package com.agarwood.webchatapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private SourceInterceptor cookieCheck;

    @Autowired
    private SourceInterceptor cookieCheck2;
    @Autowired
    private SourceInterceptor cookieCheck3;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cookieCheck).addPathPatterns("/GroupInfo/**");
        registry.addInterceptor(cookieCheck2).addPathPatterns("/message/**");
        registry.addInterceptor(cookieCheck3).addPathPatterns("/user/getSelfInfo/**");
    }
}
