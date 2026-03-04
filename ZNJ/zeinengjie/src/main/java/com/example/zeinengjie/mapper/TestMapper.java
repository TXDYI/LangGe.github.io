package com.example.zeinengjie.mapper;

import com.example.zeinengjie.pojo.GameImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {
    @Select("select * from gameimg")
    List<GameImg> getGameImg();
}
