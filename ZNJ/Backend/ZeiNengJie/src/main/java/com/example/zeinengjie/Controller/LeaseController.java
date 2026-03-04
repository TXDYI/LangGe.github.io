package com.example.zeinengjie.Controller;


import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.derive.GameZUhaoOrder;
import com.example.zeinengjie.service.imp.LeaseImp;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LeaseController {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(LeaseController.class);
    @Autowired
    private LeaseImp leaseImp;
    @GetMapping("zuhaoitem")
    @ResponseBody
    public Map<String,Object> zuhao(@RequestParam("id") Integer id) {
        logger.info("访问了zuhao:id="+id);
        Map<String,Object> hashMap = new HashMap();
        GameZUhaoOrder zuhaoOrder = leaseImp.getZuhaoOrder(id);
        hashMap.put("message","O几把K");
        hashMap.put("data",zuhaoOrder);
        hashMap.put("code",200);
        return hashMap;
    }

    @PostMapping("getdetailedness")
    @ResponseBody
    public Game getZuhaoItem(@RequestBody Game game) {
        logger.info("访问了getdetailedness:gameId="+game.getId());
        return leaseImp.getZuhaoItem(game.getId());
    }
}
