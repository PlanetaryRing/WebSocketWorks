package com.agarwood.webchatapp.service.impl;

import com.agarwood.webchatapp.domain.LoginInfo;
import com.agarwood.webchatapp.domain.User;
import com.agarwood.webchatapp.mapper.UserMapper;
import com.agarwood.webchatapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getSelfInfo(User user) {
        return userMapper.getSelfInfo(user);
    }

    @Override
    public User verifyUser(LoginInfo loginInfo) {
        return userMapper.verifyUser(loginInfo);
    }

    @Override
    public int registerUser(LoginInfo loginInfo) {
        return userMapper.registerUser(loginInfo);
    }

    @Override
    public User queryByUsername(User newUser) {
        return userMapper.queryByUsername(newUser);
    }
}
