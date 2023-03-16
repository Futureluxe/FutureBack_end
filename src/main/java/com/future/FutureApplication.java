package com.future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FutureApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(FutureApplication.class, args);
    }
    //implements WebMvcConfigurer
    @Override//图片上传
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //imctemp-rainy只是想要映射路径而已，换成a,b,c都可以
        registry.
                addResourceHandler("/imctemp-rainy/**").
                addResourceLocations("file:../src/main/resources/static/img/");
    }

}
