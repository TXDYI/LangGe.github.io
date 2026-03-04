package com.example.zeinengjie.service.imp;

import com.example.zeinengjie.mapper.UserMapper;
import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.service.UserService;
import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserImp implements UserService {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserImp.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserByNames() {
        logger.info(userMapper.toString());
        return userMapper.getUserByNames();
    }

    @Override
    public User getUserByName(String username, String password) {
        return userMapper.getUserByName(username,password);
    }

    @Override
    public User getUserStatus(String username) {
        return userMapper.getUserStatus(username);
    }

    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Override
    public void updateUser(String username, String avatar) {
        userMapper.updateUser(username,avatar);
    }
    @Override
    public String getAvatar(String username) {
        return userMapper.getAvatar(username);
    }

    @Override
    public void insertMoney(Integer userId) {
        userMapper.insertMoney(userId);
    }

}
