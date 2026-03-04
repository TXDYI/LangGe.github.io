package com.example.zeinengjie.mapper;

import com.example.zeinengjie.pojo.derive.Bargain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BargainMapper {
    Integer getBargain(String gameName);

    List<Bargain> getBargains(Integer page,Integer pageSize,String gameName);

    String getGameItemImg(Integer id);
}
