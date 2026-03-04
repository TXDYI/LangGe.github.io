package com.example.zeinengjie.pojo.derive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings({"all"})
/*
*  const form = ref({
      game: "",
      level: "",
      rank: "",
      skins: "",
      price: 3,
      rentTime: "2小时",
      deposit: 0,
      contact: "",
      imgUrl: "",
      imgList: [],
      articles: [], // 存储文章
      titledesc: "",
    });
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPutaway implements Serializable {
    private String game;
    private String level;
    private String rank;
    private String uname;
    private String pwd;
    private Integer price;
    private String contact;
    private String imgUrl;
    private List<String> imgList;
    private List<Articles> articles;
    private String titledesc;
    private String userName;
}
