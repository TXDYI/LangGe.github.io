package com.example.zeinengjie.mapper;


import com.example.zeinengjie.pojo.Exposure;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeManagementMapper {
    @Select("select * from exposure where exposure_show = 'T' limit 7")
    @Results({
        @Result(property = "idExposure", column = "id_exposure"),
        @Result(property = "titleText", column = "title_text"),
        @Result(property = "explainText", column = "explain_text"),
        @Result(property = "exposureImg", column = "exposure_img")
    })
    List<Exposure> getExposure();

    @Select("select count(*) from exposure")
    Integer getExposureAllPage();
    @Select("select * from exposure limit #{page},#{pageSize}")
    @Results({
            @Result(property = "idExposure", column = "id_exposure"),
            @Result(property = "titleText", column = "title_text"),
            @Result(property = "explainText", column = "explain_text"),
            @Result(property = "exposureImg", column = "exposure_img"),
            @Result(property = "exposureShow", column = "exposure_show")
    })
    List<Exposure> getExposureAll(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
}
