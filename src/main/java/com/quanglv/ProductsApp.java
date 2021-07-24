package com.quanglv;

import com.quanglv.config.FilesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@EnableResourceServer
public class ProductsApp implements WebMvcConfigurer {

    @Autowired
    private FilesConfig fileConfig;

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ProductsApp.class, args);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations(fileConfig.getPublicDirectory());
    }
}
