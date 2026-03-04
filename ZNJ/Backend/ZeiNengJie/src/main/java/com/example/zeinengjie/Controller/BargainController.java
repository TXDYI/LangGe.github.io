package com.example.zeinengjie.Controller;


import com.example.zeinengjie.mapper.BargainMapper;
import com.example.zeinengjie.pojo.derive.Bargain;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bargain/")
public class BargainController {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(BargainController.class);
    @Autowired
    private BargainMapper bargainMapper;
    @GetMapping ("getBargainData/{page}/{pageSize}")
    @ResponseBody
    public List<Bargain> bargainList (@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize ,@RequestParam("gameName") String gameName){
        logger.info("bargainList-获取的page为："+page);
        logger.info("bargainList-获取的pageSize为："+pageSize);
        logger.info("bargainList-获取的游戏名为："+gameName);
        int page_ = (page-1)*pageSize;
        List<Bargain> bargain = bargainMapper.getBargains(page_, pageSize,gameName);
        for (Bargain g:bargain) {
            String gameItemImg = bargainMapper.getGameItemImg(g.getId());
            g.setImg(gameItemImg);
        }
        return bargain;
    }
    @GetMapping("getpage")
    public Integer getPage(@RequestParam("gameName") String gameName){
        logger.info("获取的gameName为："+gameName);
        Integer bargain = bargainMapper.getBargain(gameName);
        logger.info("获取的page为："+bargain);
        return bargain;
    }
}
