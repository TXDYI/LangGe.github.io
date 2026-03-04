package com.example.zeinengjie.Controller;


import com.example.zeinengjie.Util.Tool;
import com.example.zeinengjie.pojo.Exposure;
import com.example.zeinengjie.service.imp.HomeManagementImp;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeManageController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(HomeManageController.class);
    private final HomeManagementImp homeManagementImp;
    @Autowired
    public HomeManageController(HomeManagementImp homeManagementImp) {
        this.homeManagementImp = homeManagementImp;
    }
    @GetMapping("getexposure")
    @ResponseBody
    public List<Exposure> getExposure(){
        List<Exposure> exposure = homeManagementImp.getExposure();
        for (Exposure e: exposure){
            logger.info(e.toString());
        }
        return exposure;
    }
}
