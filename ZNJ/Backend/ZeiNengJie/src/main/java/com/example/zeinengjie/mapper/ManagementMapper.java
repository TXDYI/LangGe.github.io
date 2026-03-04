package com.example.zeinengjie.mapper;

import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameAll;
import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.pojo.derive.AllGameOrder;
import com.example.zeinengjie.pojo.derive.OrderManageAdmin;
import com.example.zeinengjie.pojo.derive.UserListItem;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
public interface ManagementMapper {
    List<UserListItem> getUsers(Integer page, Integer pageSize);
    @Select("select count(*) from users")
    Integer getUserAllPage();

    @Delete("delete from users where id=#{userId}")
    void deleteUser(Integer userId);
    @Select("select avatar from users where id=#{id}")
    String getUserPortrait(Integer id);

    @Update("update users set status=#{status}, Userdescription=#{remark} where id=#{userId}")
    void upUserStatus(Integer status, Integer userId,String remark);




    @Select("SELECT * from games where id = #{id}")
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "gameName", column = "game_name"),
                    @Result(property = "gameCharacterName", column = "game_character_name"),
                    @Result(property = "gameTag", column = "game_tag"),
                    @Result(property = "gameLv", column = "gamelv"),
                    @Result(property = "title", column = "title"),
                    @Result(property = "content", column = "content"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "orderTime", column = "order_time"),
                    @Result(property = "gameStatus", column = "games_status"),
                    @Result(property = "money", column = "money"),
                    @Result(property = "uname", column = "uname"),
                    @Result(property = "pwd", column = "pwd"),
            }
    )
    Game getGameOrderInformation(Integer id);

    @Select("SELECT img from gameimg where games_id = #{id}")
    List<String> getGameOrderImg(Integer id);

    List<AllGameOrder> getAllGameOrderInformation(Integer page, Integer pageSize);

    @Delete("delete from games where id=#{id}")
    void deleteGameOrder(Integer id);

    Integer upGameAllStatus(String gameName);//删除一个订单，未审核的订单数量-1

    Integer upGameAllStatus2(String gameName);//订单上架后leisure的数量+1

    Integer UpGameAllLeisureJian(String gameName);//订单进行后leisure的数量-1

    Integer UpGameAllLeisureknown(String gameName);//订单完成后known-1


    Integer UpGameAllLeisureendOrderNub(String gameName);//订单完成后endOrderNub+1

    @Select("SELECT count(*) FROM games where games_status=0;")
    Integer getUOrderCount();

    List<OrderManageAdmin> getAllOrderAdmin(@Param("page") Integer page, @Param("pageSize") Integer pageSize, @Param("orderId") Integer orderId, @Param("GameName") String GameName);

    Integer getAllOrderAdminPageCount(@Param("orderId") Integer orderId,@Param("GameName") String GameName);

    List<GameAll> getGameAll(@Param("gameName") String gameName);

    Integer SettingExposureShow(@Param("id") Integer id, @Param("show")String show);

    Integer addExposure(@Param("name") String name, @Param("advert") String advert, @Param("cover") String cover);

    Integer updatePwd(Map<String,String> param);



}
