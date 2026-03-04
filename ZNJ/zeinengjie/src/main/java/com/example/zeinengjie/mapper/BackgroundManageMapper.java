package com.example.zeinengjie.mapper;


import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameAll;
import com.example.zeinengjie.pojo.GameImg;
import com.example.zeinengjie.pojo.derive.GameShow;
import com.example.zeinengjie.pojo.derive.Shopping;
import com.example.zeinengjie.pojo.derive.UserLeaseTable;
import com.example.zeinengjie.pojo.derive.UserRentingTable;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface BackgroundManageMapper {

    @Insert("insert into games(game_name,game_character_name,game_tag,gamelv,title,content,username,games_status,money,uname,pwd) values(#{gameName},#{gameCharacterName},#{gameTag},#{gameLv},#{title},#{content},#{userId},#{gameStatus},#{money},#{uname},#{pwd})")
    int insertGame(Game game);//上架账号
    @Select("select * from games where game_name=#{gameName} and uname=#{uname}")//获取游戏名和上架的游戏账号
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "gameName", column = "game_name"),
                    @Result(property = "gameCharacterName", column = "game_character_name"),
                    @Result(property = "gameTag", column = "game_tag"),
                    @Result(property = "gameLv", column = "gamelv"),
                    @Result(property = "title", column = "title"),
                    @Result(property = "content", column = "content"),
                    @Result(property = "orderTime", column = "order_time"),
            }
    )
    Game getOrderInformation(String gameName,String uname);
    @Insert("insert into gameimg(img_game_name,img,games_id) values(#{imgGameName},#{img},#{gamesId})")
    int insertGameImg(GameImg gameImg);
    @Select("select * from games where username=#{id}")
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "gameName", column = "game_name"),
                    @Result(property = "gameCharacterName", column = "game_character_name"),
                    @Result(property = "gameTag", column = "game_tag"),
                    @Result(property = "gameLv", column = "gamelv"),
                    @Result(property = "title", column = "title"),
                    @Result(property = "content", column = "content"),
            }
    )
    List<Game> getGameInformation(Integer id);
    @Select("select * from gameall")
    @Results(
            value = {
                    @Result(property = "gameallId", column = "gameall_id"),
                    @Result(property = "gameallName", column = "gameall_name"),
                    @Result(property = "gameallClass", column = "gameall_class"),
                    @Result(property = "os", column = "os"),
                    @Result(property = "known", column = "known"),
                    @Result(property = "leisure", column = "leisure"),
                    @Result(property = "authorization", column = "authorization")
            }
    )
    List<GameAll> getGameAll();
    @Select("select * form games where game_show=#{show}")
    List<Game> getGameShow(String show);
    @Select("SELECT g.id, g.game_name, i.img, g.title,g.money\n" +
            "FROM games g\n" +
            "INNER JOIN gameimg i ON g.id = i.games_id\n" +
            "AND g.game_show = 'T'\n" +
            "AND i.id = (SELECT MIN(id) FROM gameimg WHERE games_id = g.id);")//把所有的game_show为T的订单显示出来
    @Results({
            @Result(property = "gameName", column = "game_name"),
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "img", column = "img"),
            @Result(property = "money", column = "money")
    })
    List<GameShow> getGameShowT();

    @Select("select id from users where username=#{name}")
    Integer getUserID(String name);

    @Select("select users_money from moneyall where users_id=#{userId}")
    Double getMoney(Integer userId);

    List<UserRentingTable> getUserRentingTable(String userName, Integer page,Integer pageSize);

    List<UserLeaseTable> getUserLeaseTable(String userName, Integer page, Integer pageSize);

    Integer getUserRentingTableCount(String userName);

    Integer getUserLeaseTableCount(String userName);

    String getGameClass(String gameName);

    Integer statisticsAddAuthorizationCommodity(String gameName);//用户每上架一个商品，未审核的商品就加1

    Integer shoppingAddOrder(@Param("order") Shopping order,@Param("endTime") Timestamp endTime,@Param("userId") Integer userId);//租赁账号

    Integer updateGameStatus(@Param("id") Integer orderId,@Param("gameStatus") Integer gameStatus);//修改订单状态（游戏状态 0：未上架/待审核 1：上架中 2：进行中 3：已结束）

    Integer upGameAllStatus3(String gameName);//订单进行后known的数量+1

    Integer insertRentingOrder (Integer gameId,Integer userId,double money);
    Integer getRentalIncome(Integer userId);//出租收入

    Integer getRentalOrder(Integer userId);//出租订单

    Integer LeasingExpenditure(Integer userId);//租赁支出

    Integer LeasingOrder(Integer userId);//租赁订单

    Integer upOrderStatus(Integer UserID,Integer orderId,Integer status);//普通用户修改订单状态

    Integer UserDeleteOrder(Integer UserID,Integer orderId,Integer status);//普通用户删除订单
}
