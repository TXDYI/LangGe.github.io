package com.example.zeinengjie.service.imp;

import com.example.zeinengjie.mapper.BargainMapper;
import com.example.zeinengjie.pojo.derive.Bargain;
import com.example.zeinengjie.service.BargainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BargainImp implements BargainService {

    @Autowired
    private BargainMapper bargainMapper;
    @Override
    public Integer getBargain(String gameName) {
        return bargainMapper.getBargain(gameName);
    }

    @Override
    public List<Bargain> getBargains(Integer page,Integer pageSize, String gameName) {
        return bargainMapper.getBargains(page,pageSize,gameName);
    }
}
