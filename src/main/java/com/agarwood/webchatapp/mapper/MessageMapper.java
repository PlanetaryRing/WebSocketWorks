package com.agarwood.webchatapp.mapper;

import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    int receiveMessage(Message msg);

    List<Message> getLatestMessage(GroupBase groupBase);
}
