package com.agarwood.webchatapp.service.impl;

import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.Message;
import com.agarwood.webchatapp.mapper.MessageMapper;
import com.agarwood.webchatapp.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int receiveMessage(Message msg) {
        return messageMapper.receiveMessage(msg);
    }

    @Override
    public List<Message> getLatestMessage(GroupBase groupBase) {
        return messageMapper.getLatestMessage(groupBase);
    }
}
