package com.example.zeinengjie.Controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.example.zeinengjie.Util.AliOsUtil;
import com.example.zeinengjie.Util.OssConfig;
import com.example.zeinengjie.pojo.Exposure;
import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameAll;
import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.pojo.derive.*;
import com.example.zeinengjie.service.imp.*;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/management")
public class ManagementController {
   private Logger logger = org.slf4j.LoggerFactory.getLogger(ManagementController.class);
    private ManagementImp managementImp;
    private HomeManagementImp  homeManagementImp;
    @Autowired
    public ManagementController(ManagementImp managementImp,HomeManagementImp  homeManagementImp) {
        this.managementImp = managementImp;
        this.homeManagementImp = homeManagementImp;
    }

    @Autowired
    private BackgroundManageImp backgroundManageImp;
    @Autowired
    private AliOsUtil aliOsUtil;
    @Autowired
    private OssConfig ossConfig;
    @Autowired
    private LeaseImp leaseImp;
    @PostMapping("adminIndex")
    @RequiresRoles("admin")
    public String adminIndex(){

        return "adminIndex";
    }
    @PostMapping("userList")
    @RequiresRoles("admin")
    public List<UserListItem> userList(@RequestBody Map<String,String> param){
        String Page = param.get("Page");
        String pageSize = param.get("pageSize");
        Integer PageInt= ((Integer.parseInt(Page))-1)*(Integer.parseInt(pageSize));
        List<UserListItem> users = managementImp.getUsers(PageInt, Integer.parseInt(pageSize));
        return users;
    }
    @GetMapping("userallpagenumber")
    @RequiresRoles("admin")
    public Integer userallpagenumber(){
        logger.info("获取用户总数");
        return managementImp.getUserAllPage();
    }
    @DeleteMapping("deleteuser")
    @RequiresRoles("admin")
    public void deleteUser(@RequestParam("userId") Integer userId){
        logger.info("删除用户:"+userId);
        try {
            managementImp.deleteUser(userId);
            String Portrait = managementImp.getUserPortrait(userId);
            try{
                aliOsUtil.removeFile(Portrait);
            }catch (Exception e){
                logger.info("头像删除发生异常");
            }
        }catch (Exception e){
            logger.info("删除用户失败");
        }
    }
    @PostMapping("upuserstatus")
    @RequiresRoles("admin")
    public void upUserStatus(@RequestBody UpUserStatus upUserStatus){
        logger.info("修改用户状态"+upUserStatus.toString());
        managementImp.upUserStatus(upUserStatus.getSelectedRoleId(),upUserStatus.getOrderId(),upUserStatus.getRemark());
    }

    @PostMapping("getunauthorizedOrder")
    @RequiresRoles("admin")
    public List<AllGameOrder> getUnauthorizedOrder(@RequestBody Map<String,String> param){
        String Page = param.get("Page");
        String pageSize = param.get("pageSize");
        Integer PageInt= ((Integer.parseInt(Page))-1)*(Integer.parseInt(pageSize));
        return managementImp.getAllGameOrderInformation(PageInt, Integer.parseInt(pageSize));
    }
    @PostMapping("setOrderId")
    @RequiresRoles("admin")
    public OrderPutaway getOrderPutaway(@RequestBody Map<String,String> Orderid){
        int orderid = Integer.parseInt(Orderid.get("Orderid"));
        logger.info("Orderid:"+orderid);
        OrderPutaway orderPutaway = managementImp.getOrderPutaway(orderid);
        return orderPutaway;
    }
    @PostMapping("verifiedOrder")
    @RequiresRoles("admin")
    public String verifiedOrder(@RequestBody Map<String,String> param){
        String orderid = param.get("orderid");
        backgroundManageImp.updateGameStatus(Integer.parseInt(orderid),1);
        Game zuhaoItem = leaseImp.getZuhaoItem(Integer.parseInt(orderid));
        managementImp.upGameAllStatus(zuhaoItem.getGameName());//更新游戏总表，未上架数量减1
        managementImp.upGameAllStatus2(zuhaoItem.getGameName());//更新游戏总表，空闲数量加1
        return "上架成功";
    }

    @DeleteMapping("deleteOrder")
    @RequiresRoles("admin")
    public String deleteOrder(@RequestParam("OrderId") Integer orderid){
        logger.info("删除订单"+orderid);
        managementImp.deleteGameOrder(orderid);
        return "删除成功";
    }
    @DeleteMapping("deleteOrderAdmin")
    @RequiresRoles("admin")
    public String deleteOrder2(@RequestParam("OrderId") Integer OrderId, @RequestParam("statuNub") Integer statuNub,@RequestParam("gameName") String gameName){
        managementImp.deleteGameOrder2(OrderId,statuNub,gameName);
        return "删除成功";
    }

    @GetMapping("UOrderPageCount")
    @RequiresRoles("admin")
    public Integer UOrderPageCount(){
        logger.info("获取未上架订单总数");
        return managementImp.getUOrderCount();
    }
    /*
    *  orderId:searchForm.value.OrderId,//订单id搜索
        userName:searchForm.value.UserName,//游戏名称订单搜索
        Page:currentPage.value,
        pageSize: pageSize.value,
    * */
    @PostMapping("getAllOrderAdmin")
    @RequiresRoles("admin")
    public List<OrderManageAdmin> getAllOrderAdmin(@RequestBody Map<String,Object> param){

        String orderId = param.get("orderId").toString();
        String GameName = param.get("GameName").toString();
        String Page = param.get("Page").toString();
        String pageSize = param.get("pageSize").toString();
        Integer PageInt= ((Integer.parseInt(Page))-1)*(Integer.parseInt(pageSize));
        logger.info("获取所有订单"+orderId+":"+GameName+":"+Page+":"+pageSize);
        if(orderId.equals("")){
            orderId="-1";
        }
        List<OrderManageAdmin> allOrderAdmin = managementImp.getAllOrderAdmin(PageInt, Integer.parseInt(pageSize), Integer.parseInt(orderId), GameName);
        return allOrderAdmin;
    }
    @PostMapping("getAllOrderAdminPageCount")
    @RequiresRoles("admin")
    public Integer getAllOrderAdminPageCount(@RequestBody Map<String,Object> param){
        String orderId = param.getOrDefault("OrderId", "-1").toString();
        String GameName = param.getOrDefault("GameName","").toString();
        logger.info("获取所有订单总数"+orderId+":"+GameName);
        if(orderId.equals("")){
            orderId="-1";
        }
        Integer allOrderAdminPageCount = managementImp.getAllOrderAdminPageCount(Integer.parseInt(orderId), GameName);
        return allOrderAdminPageCount;
    }
    @GetMapping("getPieChart")
    @RequiresRoles("admin")
    public GameAll getPieChart(@RequestParam("gameName") String gameName){
        List<GameAll> gameAll = managementImp.getGameAll(gameName);
        GameAll gameAll1 = new GameAll();
        logger.info("获取" + gameName + "饼图数据");
        if("".equals(gameName)|| gameName == null) {
            int Authorization = 0;
            int EndOrderNub = 0;
            int Known = 0;
            int Leisure = 0;
            for (GameAll g : gameAll) {
                Authorization += g.getAuthorization();
                EndOrderNub += g.getEndOrderNub();
                Known += g.getKnown();
                Leisure += g.getLeisure();
            }
            gameAll1.setGameallName("游戏账号订单总汇");
            gameAll1.setAuthorization(Authorization);
            gameAll1.setEndOrderNub(EndOrderNub);
            gameAll1.setKnown(Known);
            gameAll1.setLeisure(Leisure);
            return gameAll1;
        }
        gameAll1 = gameAll.get(0);
        return gameAll1;
    }
    @GetMapping("getExposureNum")
    @RequiresRoles("admin")
    public Integer getExposureNum(){
        logger.info("获取曝光表的所有数量");
        Integer exposureAllPage = homeManagementImp.getExposureAllPage();
        return exposureAllPage;
    }
    @GetMapping("getAllExposureM")
    @RequiresRoles("admin")
    public List<Exposure> getAllExposureM(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        logger.info("获取总曝光量"+page+":"+pageSize);
        Integer PageInt= (page-1)*pageSize;
        List<Exposure> exposureAll = homeManagementImp.getExposureAll(PageInt, pageSize);
        return exposureAll;
    }
    @PostMapping("setting")
    @RequiresRoles("admin")
    public String setting(@RequestBody Map<String,String> param){
        Integer Id = Integer.parseInt(param.get("Id"));
        String Show = param.get("Status");
        managementImp.SettingExposureShow(Id, Show);
        logger.info("修改曝光表"+Id+":"+Show);
        return "已修改完成！";
    }
    @PostMapping("addExposure")
    @RequiresRoles("admin")
    public String addExposure(  @RequestParam("name") String name,
                                @RequestParam("advert") String advert,
                                @RequestParam("cover") MultipartFile coverFile){
        logger.info("addExposure:"+name+"advert:"+advert+"cover:"+coverFile.getOriginalFilename());
        String imgFileName = aliOsUtil.createImgFileName();
        aliOsUtil.uploadFile(coverFile, imgFileName);
        String imgUrl = "https://" + ossConfig.getBucketName()+"."+ossConfig.getEndpoint()+ "/" + imgFileName;
        managementImp.addExposure(name, advert, imgUrl);
        return "添加成功！";
    }
    //修改密码
    @PostMapping("updatePro")
    @RequiresRoles("admin")
    public String updatePro(@RequestBody Map<String,Object> param){
        String oldPassword = param.get("oldPassword").toString();
        String newPassword = param.get("newPassword").toString();
        String User = param.get("User").toString();
        String code = "200";
        Map<String,String> map = new HashMap<>();
        if(!"".equals(oldPassword)&&!"".equals(newPassword)){
            map.put("oldPassword",oldPassword);
            map.put("newPassword",newPassword);
            map.put("User",User);
        }else {
            code  = "401";
            return code;
        }
        int test = managementImp.updatePwd(map);
        if(test!=1){
            code = "402";
        }
        logger.info("修改密码"+oldPassword+":"+newPassword+":"+test);
        return code;
    }
}
