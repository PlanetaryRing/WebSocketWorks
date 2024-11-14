package com.agarwood.webchatapp.service;

import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.User;

import java.util.List;

public interface IGroupBaseService {
    List<GroupBase> selectUserBelongGroup(GroupBase groupBase);

    List<User> getCurGroupUserList(GroupBase groupBase);

    int addUserIntoGroup(GroupBase groupBase);
}
