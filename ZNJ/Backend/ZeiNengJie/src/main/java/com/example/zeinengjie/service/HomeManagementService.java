package com.example.zeinengjie.service;

import com.example.zeinengjie.pojo.Exposure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeManagementService {
    List<Exposure> getExposure();
    Integer getExposureAllPage();
    List<Exposure> getExposureAll(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
}
