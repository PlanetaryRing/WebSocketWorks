package com.agarwood.webchatapp.mapper;

import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupBaseMapper {
    /**
     * 根据userID来选择用户所在的群组列表
     */
    List<GroupBase> selectUserBelongGroup(GroupBase groupBase);

    /**
     * 获取当前群组的成员
     */
    List<User> getCurGroupUserList(GroupBase groupBase);

    int addUserIntoGroup(GroupBase groupBase);
}
