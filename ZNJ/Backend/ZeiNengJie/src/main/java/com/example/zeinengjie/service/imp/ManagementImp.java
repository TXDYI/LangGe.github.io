package com.example.zeinengjie.service.imp;

import com.example.zeinengjie.mapper.ManagementMapper;
import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameAll;
import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.pojo.derive.AllGameOrder;
import com.example.zeinengjie.pojo.derive.OrderManageAdmin;
import com.example.zeinengjie.pojo.derive.OrderPutaway;
import com.example.zeinengjie.pojo.derive.UserListItem;
import com.example.zeinengjie.service.ManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ManagementImp implements ManagementService {
    private Logger logger = LoggerFactory.getLogger(ManagementImp.class);

    @Autowired
    private LeaseImp leaseImp;
    @Autowired
    private ManagementMapper managementMapper;
    @Override
    public List<UserListItem> getUsers(Integer page, Integer pageSize) {

        return managementMapper.getUsers(page,pageSize);
    }

    @Override
    public Integer getUserAllPage() {
        return managementMapper.getUserAllPage();
    }

    @Override
    public void deleteUser(Integer userId) {
        managementMapper.deleteUser(userId);
    }

    @Override
    public void upUserStatus(Integer status, Integer userId, String remark) {
        managementMapper.upUserStatus(status,userId,remark);
    }

    @Override
    public String getUserPortrait(Integer id) {
        return managementMapper.getUserPortrait(id);
    }

    @Override
    public List<AllGameOrder> getAllGameOrderInformation(Integer page, Integer pageSize) {
        return managementMapper.getAllGameOrderInformation(page,pageSize);
    }

    @Override
    public Game getGameOrderInformation(Integer id) {
        return managementMapper.getGameOrderInformation(id);
    }



    public OrderPutaway getOrderPutaway(Integer id){
        OrderPutaway arrList = new OrderPutaway();
        Game gameOrderInformation = getGameOrderInformation(id);
        List<String> gameOrderImg = getGameOrderImg(id);
        arrList.setGame(gameOrderInformation.getGameName());
        arrList.setUname(gameOrderInformation.getUname());
        arrList.setPwd(gameOrderInformation.getPwd());
        arrList.setContact(gameOrderInformation.getContent());
        arrList.setTitledesc(gameOrderInformation.getTitle());
        arrList.setImgList(gameOrderImg);
        return arrList;
    }
    @Override
    public List<String> getGameOrderImg(Integer id) {
        return managementMapper.getGameOrderImg(id);
    }

    @Override
    public void deleteGameOrder(Integer id) {
        Game zuhaoItem = leaseImp.getZuhaoItem(id);
        if(zuhaoItem!=null){
            upGameAllStatus(zuhaoItem.getGameName());
            managementMapper.deleteGameOrder(id);
        }else{
            logger.info("订单删除失败");
        }
    }
    public void deleteGameOrder2(Integer OrderId,Integer statuNub,String gameName){
        if(statuNub==0){
            managementMapper.upGameAllStatus(gameName);
        }else if(statuNub == 1){
            managementMapper.UpGameAllLeisureJian(gameName);
        }else if (statuNub == 2){
            managementMapper.UpGameAllLeisureknown(gameName);
        }
        logger.info("订单删除成功:"+"状态"+statuNub+"的"+gameName);
        managementMapper.deleteGameOrder(OrderId);
    }

    @Override
    public Integer upGameAllStatus(String gameName) {
        return managementMapper.upGameAllStatus(gameName);
    }

    @Override
    public Integer upGameAllStatus2(String gameName) {
        return managementMapper.upGameAllStatus2(gameName);
    }

    @Override
    public Integer UpGameAllLeisureJian(String gameName) {
        return managementMapper.UpGameAllLeisureJian(gameName);
    }

    @Override
    public Integer UpGameAllLeisureknown(String gameName) {
        return managementMapper.UpGameAllLeisureknown(gameName);
    }

    @Override
    public Integer UpGameAllLeisureendOrderNub(String gameName) {
        return managementMapper.UpGameAllLeisureendOrderNub(gameName);
    }

    @Override
    public Integer getUOrderCount() {
        return managementMapper.getUOrderCount();
    }

    @Override
    public List<OrderManageAdmin> getAllOrderAdmin(Integer page, Integer pageSize, Integer orderId, String GameName) {
        return managementMapper.getAllOrderAdmin(page,pageSize,orderId,GameName);
    }

    @Override
    public Integer getAllOrderAdminPageCount(Integer orderId, String GameName) {
        return managementMapper.getAllOrderAdminPageCount(orderId,GameName);
    }

    @Override
    public List<GameAll> getGameAll(String gameName) {
        return managementMapper.getGameAll(gameName);
    }

    @Override
    public Integer SettingExposureShow(Integer id, String show) {
        return managementMapper.SettingExposureShow(id,show);
    }

    @Override
    public Integer addExposure(String name, String advert, String cover) {
        return managementMapper.addExposure(name,advert,cover);
    }

    @Override
    public Integer updatePwd(Map<String, String> param) {
        return managementMapper.updatePwd(param);
    }

}
