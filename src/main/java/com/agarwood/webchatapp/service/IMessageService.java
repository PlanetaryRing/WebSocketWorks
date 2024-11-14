package com.agarwood.webchatapp.service;

import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.Message;

import java.util.List;

public interface IMessageService {
    int receiveMessage(Message msg);

    List<Message> getLatestMessage(GroupBase groupBase);
}
