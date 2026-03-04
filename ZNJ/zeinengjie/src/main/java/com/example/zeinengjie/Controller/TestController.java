package com.example.zeinengjie.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private DataSource dataSource;

    @GetMapping("/druid")
    public String testDruid() {
        try (Connection conn = dataSource.getConnection()) {
            return "Druid 连接成功：" + conn;
        } catch (Exception e) {
            e.printStackTrace();
            return "Druid 连接失败";
        }
    }
}
