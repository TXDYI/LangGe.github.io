package com.example.zeinengjie.mapper;


import com.example.zeinengjie.pojo.Exposure;
import com.example.zeinengjie.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users")
    List<User> getUserByNames();

    @Select("select * from users where username=#{username} and password = MD5(#{password})")
    User getUserByName(String username,String password);

    @Select("select * from users where username=#{username}")
    User getUserStatus(String username);

    @Insert("insert into users(username,password,mail,phone) values(#{username},MD5(#{password}),#{mail},#{phone})")
    void register(User user);

    @Update("update users set avatar=#{avatar} where username=#{username}")
    void updateUser(String username,String avatar);

    @Select("select avatar from users where username=#{username}")
    String getAvatar(String username);

    @Insert("insert into moneyall(users_id,users_money) values(#{userId},0)")
    void insertMoney(Integer userId);
}
