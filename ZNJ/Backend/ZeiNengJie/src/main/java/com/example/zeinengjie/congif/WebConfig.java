package com.example.zeinengjie.congif;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径
                .allowedHeaders("*")  // 允许所有请求头
                .exposedHeaders("Authorization")  // 让前端可以获取 `Authorization` 头
                .allowCredentials(true);  // 允许发送 Cookies
    }
}
