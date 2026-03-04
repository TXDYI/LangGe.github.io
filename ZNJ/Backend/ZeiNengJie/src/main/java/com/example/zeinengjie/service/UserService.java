package com.example.zeinengjie.service;

import com.example.zeinengjie.pojo.User;

import java.util.List;

public interface UserService{
   List<User> getUserByNames();
   User getUserByName(String username,String password);
   User getUserStatus(String username);
   void register(User user);
   void updateUser(String username,String avatar);
   String getAvatar(String username);
   void insertMoney(Integer userId);
}
