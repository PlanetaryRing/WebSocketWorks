package com.agarwood.webchatapp.service;


import com.agarwood.webchatapp.domain.LoginInfo;
import com.agarwood.webchatapp.domain.User;

public interface IUserService {
    User getSelfInfo(User user);

    User verifyUser(LoginInfo loginInfo);

    int registerUser(LoginInfo loginInfo);

    User queryByUsername(User newUser);
}
