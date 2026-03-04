package com.example.zeinengjie.congif;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("贼能借 API 文档")
                        .version("1.0.0")
                        .description("这是一个基于 Spring Boot + Vue 的游戏账号租赁平台 API 文档")
                        .contact(new Contact()
                                .name("开发者")
                                .email("dev@example.com")));
    }
}
