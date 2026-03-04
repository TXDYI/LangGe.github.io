package com.example.zeinengjie.mapper;

import com.example.zeinengjie.pojo.Game;
import com.example.zeinengjie.pojo.GameImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeaseMapper {
    @Select("select * from games where id=#{id}")
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "gameName", column = "game_name"),
                    @Result(property = "gameCharacterName", column = "game_character_name"),
                    @Result(property = "gameTag", column = "game_tag"),
                    @Result(property = "gameLv", column = "gamelv"),
                    @Result(property = "title", column = "title"),
                    @Result(property = "content", column = "content"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "gamesStatus", column = "games_status"),
                    @Result(property = "gameShow", column = "game_show"),
            }
    )
    Game getZuhaoItem(Integer id);

    @Select("select * from gameimg where games_id=#{id}")
    @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "imgGameName", column = "img_game_name"),
                    @Result(property = "img", column = "img"),
                    @Result(property = "gamesId", column = "games_id"),
            }
    )
    List<GameImg> getGameImg(Integer id);
}
