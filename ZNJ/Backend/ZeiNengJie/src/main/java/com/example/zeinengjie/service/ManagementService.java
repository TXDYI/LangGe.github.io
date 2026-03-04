package com.example.zeinengjie.service;

import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameAll;
import com.example.zeinengjie.pojo.derive.AllGameOrder;
import com.example.zeinengjie.pojo.derive.OrderManageAdmin;
import com.example.zeinengjie.pojo.derive.UserListItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ManagementService {
    List<UserListItem> getUsers(Integer page, Integer pageSize);
    Integer getUserAllPage();
    void deleteUser(Integer userId);
    void upUserStatus(Integer status, Integer userId,String remark);
    String getUserPortrait(Integer id);
    List<AllGameOrder> getAllGameOrderInformation(Integer page, Integer pageSize);
    Game getGameOrderInformation(Integer id);
    List<String> getGameOrderImg(Integer id);
    void deleteGameOrder(Integer id);
    Integer upGameAllStatus(String gameName);
    Integer upGameAllStatus2(String gameName);
    Integer UpGameAllLeisureJian(String gameName);
    Integer UpGameAllLeisureknown(String gameName);//订单完成后known-1
    Integer UpGameAllLeisureendOrderNub(String gameName);//订单完成后endOrderNub+1
    Integer getUOrderCount();
    List<OrderManageAdmin> getAllOrderAdmin(@Param("page") Integer page, @Param("pageSize") Integer pageSize, @Param("orderId") Integer orderId, @Param("GameName") String GameName);
    Integer getAllOrderAdminPageCount(@Param("orderId") Integer orderId,@Param("GameName") String GameName);
    List<GameAll> getGameAll(@Param("gameName") String gameName);
    Integer SettingExposureShow(@Param("id") Integer id, @Param("show")String show);
    Integer addExposure(@Param("name") String name, @Param("advert") String advert, @Param("cover") String cover);
    Integer updatePwd(Map<String,String> param);


}
