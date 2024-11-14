package com.agarwood.webchatapp.mapper;

import com.agarwood.webchatapp.domain.LoginInfo;
import com.agarwood.webchatapp.domain.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    User getSelfInfo(User user);
    User verifyUser(LoginInfo loginInfo);
    int registerUser(LoginInfo loginInfo);
    User queryByUsername(User newUser);
}
