package com.example.zeinengjie.service.imp;

import com.example.zeinengjie.mapper.HomeManagementMapper;
import com.example.zeinengjie.pojo.Exposure;
import com.example.zeinengjie.service.HomeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeManagementImp implements HomeManagementService {
    @Autowired
    private HomeManagementMapper homeManagementMapper;
    @Override
    public List<Exposure> getExposure() {
        return homeManagementMapper.getExposure();
    }

    @Override
    public Integer getExposureAllPage() {
        return homeManagementMapper.getExposureAllPage();
    }

    @Override
    public List<Exposure> getExposureAll(Integer page, Integer pageSize) {
        return homeManagementMapper.getExposureAll(page,pageSize);
    }

}
