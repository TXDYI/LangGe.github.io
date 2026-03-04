package com.example.zeinengjie.service;


import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameAll;
import com.example.zeinengjie.pojo.GameImg;
import com.example.zeinengjie.pojo.derive.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

public interface BackgroundManageService {
    int insertGame(Game game, OrderPutaway form, List<MultipartFile> files);
    Game getOrderInformation(String gameName,String uname);
    int insertGameImg(GameImg gameImg);
    List<Game> getGameInformation(Integer id);
    List<GameAll> getGameAll();
    List<Game> getGameShow(String show);
    List<GameShow> getGameShowT();
    Integer getUserID(String uname);
    Double getMoney(Integer userId);
    List<UserRentingTable> getUserRentingTable(String userName, Integer page, Integer pageSize);
    List<UserLeaseTable> getUserLeaseTable(String userName, Integer page, Integer pageSize);
    Integer getUserRentingTableCount(String userName);
    String getGameClass(String gameName);
    Integer statisticsAddAuthorizationCommodity(String gameName);
    Integer shoppingAddOrder(Shopping order);//租赁账号
    Integer updateGameStatus(Integer orderId,Integer gameStatus);
    Integer getUserLeaseTableCount(String userName);
    Integer upGameAllStatus3(String gameName);
    Integer insertRentingOrder (Integer gameId,Integer userId,double money);
    Integer getRentalIncome(Integer userId);//出租收入

    Integer getRentalOrder(Integer userId);//出租订单

    Integer LeasingExpenditure(Integer userId);//租赁支出

    Integer LeasingOrder(Integer userId);//租赁订单

    Integer upOrderStatus(String UserName,Integer orderId,Integer status,String gameName);//普通用户修改订单状态

    Integer UserDeleteOrder(String UserName,Integer orderId,Integer status,String gameName);//普通用户删除订单
}
