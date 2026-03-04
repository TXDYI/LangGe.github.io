package com.example.zeinengjie.service;

import com.example.zeinengjie.pojo.derive.Bargain;

import java.util.List;

public interface BargainService {
    Integer getBargain(String gameName);

    List<Bargain> getBargains(Integer page,Integer pageSize,String gameName);
}
