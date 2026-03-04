package com.example.zeinengjie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.zeinengjie.mapper")
@EnableScheduling
public class ZeiNengJieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeiNengJieApplication.class, args);
    }

}
