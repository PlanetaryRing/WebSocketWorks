package com.agarwood.webchatapp.service.impl;

import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.User;
import com.agarwood.webchatapp.mapper.GroupBaseMapper;
import com.agarwood.webchatapp.service.IGroupBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupBaseServiceImpl implements IGroupBaseService {
    @Autowired
    private GroupBaseMapper groupBaseMapper;

    @Override
    public List<GroupBase> selectUserBelongGroup(GroupBase groupBase) {
        return groupBaseMapper.selectUserBelongGroup(groupBase);
    }

    @Override
    public List<User> getCurGroupUserList(GroupBase groupBase) {
        return groupBaseMapper.getCurGroupUserList(groupBase);
    }

    @Override
    public int addUserIntoGroup(GroupBase groupBase) {
        return groupBaseMapper.addUserIntoGroup(groupBase);
    }
}
