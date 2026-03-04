package com.example.zeinengjie.Controller;

import com.example.zeinengjie.Util.Tool;
import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameAll;
import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.pojo.derive.*;
import com.example.zeinengjie.service.imp.BackgroundManageImp;
import com.example.zeinengjie.service.imp.UserImp;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"all"})
@RestController
@RequestMapping("/")
public class BackgroundManageController {
    List<MultipartFile> imgList = new LinkedList<>();
    Logger logger = org.slf4j.LoggerFactory.getLogger(BackgroundManageController.class);
    @Autowired
    private  BackgroundManageImp backgroundManageImp;
    @Autowired
    private UserImp userImp;
    @PostMapping("backgroundtest")
    public Tool backgroundtest(@RequestBody OrderPutaway form) {
        Tool tool = new Tool();
        Game game = new Game();
        if(imgList.isEmpty()){
            logger.info("backgroundtest:"+"imgList:"+imgList.isEmpty());
            tool.setStatusCode(400);
            tool.setMessage("上架失败");
            imgList.clear();
            return tool;
        }
        int i = backgroundManageImp.insertGame(game, form, imgList);
        if (i == 1) {
            logger.info("backgroundtest:"+"i:"+i);
            tool.setStatusCode(200);
            tool.setMessage("上架成功");

            imgList.clear();
        } else {
            logger.info("backgroundtest:"+"i:"+i);
            tool.setStatusCode(400);
            tool.setMessage("账户已存在上架失败");
            imgList.clear();
        }
        return tool;
    }
    @PostMapping("test")
    public Tool test(@RequestParam("file") MultipartFile file,
                     @RequestParam("userName") String userName) {
        logger.info("test:" + file.getOriginalFilename() + "userName:" + userName);
        Tool tool = new Tool();
        // 检查文件是否为空
        if (file.isEmpty()) {
            tool.ToolInit();
            tool.setStatusCode(400);
            tool.setMessage("上传文件为空");
            return tool;
        }

        // 检查文件是否可读
        try {
            if (!(file.getInputStream().available() > 0)) {
                tool.ToolInit();
                tool.setStatusCode(400);
                tool.setMessage("文件不可读");
                return tool;
            }
        } catch (IOException e) {
            logger.error("文件读取失败", e);
            tool.ToolInit();
            tool.setStatusCode(500);
            tool.setMessage("文件读取失败");
            return tool;
        }

        imgList.add(file);
        tool.ToolInit();
        return tool;
    }


    @PostMapping("getGameform")
    public Tool getGameForm() {
        Tool tool = new Tool();
        List<GameAll> gameAll = backgroundManageImp.getGameAll();
        if (gameAll.isEmpty()) {
            tool.setStatusCode(400);
            tool.setMessage("游戏列表获取失败");
        } else {
            tool.setStatusCode(200);
            tool.setMessage("游戏列表获取成功");
            tool.setData(gameAll);
        }
        return tool;
    }

    @PostMapping("getgameshow")
    public List<GameShow> getGameShow(){
        Tool tool = new Tool();
        List<GameShow> gameShowT = backgroundManageImp.getGameShowT();

        logger.info("getGameShow");
        if(!gameShowT.isEmpty()){
            tool.setStatusCode(200);
            tool.setMessage("获取成功");
            tool.setData(gameShowT);
        }else{
            tool.setStatusCode(404);
            tool.setMessage("获取失败");
        }
        return gameShowT;
    }
    @PostMapping("getmoney")
    public Double getMoney(@RequestBody Map<String,String> userName){
        String uname = userName.get("userName");
        logger.info("getMoney:"+userName);
        Double userMoney = backgroundManageImp.getUserMoney(uname);
        return userMoney;
    }
    @PostMapping("allrental")
    public List<UserRentingTable> getGameShow(@RequestBody Map<String,String> show){
        logger.info("allrental:"+show.toString());
        String userName = show.get("userName");
        String Page = show.get("Page");
        String pageSize = show.get("pageSize");
        Integer PageInt= ((Integer.parseInt(Page))-1)*(Integer.parseInt(pageSize));
        logger.info("alllease:"+PageInt);
        List<UserRentingTable>  rentingTable= backgroundManageImp.getUserRentingTable(userName,PageInt, Integer.parseInt(pageSize));
        return rentingTable;
    }

    @PostMapping("alllease")
    public List<UserLeaseTable> getleaseTable(@RequestBody Map<String,String> show){
        logger.info("alllease:"+show.toString());
        String userName = show.get("userName");
        String Page = show.get("Page");
        String pageSize = show.get("pageSize");
        Integer PageInt= ((Integer.parseInt(Page))-1)*(Integer.parseInt(pageSize));
        logger.info("alllease:"+PageInt);
        List<UserLeaseTable> userLeaseTable = backgroundManageImp.getUserLeaseTable(userName, PageInt, Integer.parseInt(pageSize));
        return userLeaseTable;
    }
    @PostMapping("getrentalcount")
    public Integer getRentalCount(@RequestBody Map<String,String> userName){
        String uname = userName.get("userName");
        logger.info("getRentalCount:"+uname);
        Integer rentalCount = backgroundManageImp.getUserRentingTableCount(uname);
        return rentalCount;
    }

    @PostMapping("getleasecount")
    public Integer getLeaseCount(@RequestBody Map<String,String> userName){
        String uname = userName.get("userName");
        logger.info("getLeaseCount:"+uname);
        Integer leaseCount = backgroundManageImp.getUserLeaseTableCount(uname);
        return leaseCount;
    }

    @PostMapping("shopping")
    public void shopping(@RequestBody Shopping order){
        logger.info("shopping:"+order.toString());
        backgroundManageImp.shoppingAddOrder(order);
    }
    //获取用户订单详情
    @PostMapping("getOrderDetail")
    public Map<String,Integer> getOrderDetail(@RequestBody Map<String,String> userAccount){
        String userName = userAccount.get("userAccount");
        User userStatus = userImp.getUserStatus(userName);
        Long id = userStatus.getId();
        logger.info("获取用户订单详情:"+userStatus.getId());
        Integer rentalIncome = backgroundManageImp.getRentalIncome(id.intValue());//出租收入
        if(rentalIncome==null){
            rentalIncome=0;
        }
        Integer rentalOrder = backgroundManageImp.getRentalOrder(id.intValue());//出租订单
        Integer LeasingExpenditure = backgroundManageImp.LeasingExpenditure(id.intValue());//租赁支出
        if(LeasingExpenditure==null){
            LeasingExpenditure=0;
        }
        Integer LeasingOrder = backgroundManageImp.LeasingOrder(id.intValue());//租赁订单
        Map<String,Integer> map = new HashMap<>();
        map.put("rentalIncome",rentalIncome);
        map.put("rentalOrder",rentalOrder);
        map.put("LeasingExpenditure",LeasingExpenditure);
        map.put("LeasingOrder",LeasingOrder);
        return map;
    }
    @PostMapping("deleteOrder")
    public  String deleteOrder(@RequestBody Map<String,String> Userdata){
        String userName = Userdata.get("userName");
        String orderId = Userdata.get("orderId");
        String gamesStatus = Userdata.get("gamesStatus");
        String gameName = Userdata.get("gameName");
        if("1".equals(gamesStatus)){
            logger.info("删除操作-deleteOrder:"+Userdata.toString());
            backgroundManageImp.UserDeleteOrder(userName, Integer.parseInt(orderId), Integer.parseInt(gamesStatus),gameName);
        }else if("3".equals(gamesStatus)){
            backgroundManageImp.upOrderStatus(userName, Integer.parseInt(orderId), Integer.parseInt(gamesStatus),gameName);
            logger.info("重新上架操作-deleteOrder:"+Userdata.toString());
        }else{
            logger.info("状态码-deleteOrder:"+Userdata.toString());
        }
        return "删除成功";
    }
}
