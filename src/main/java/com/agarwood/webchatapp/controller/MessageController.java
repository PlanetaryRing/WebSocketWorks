package com.agarwood.webchatapp.controller;

import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.Message;
import com.agarwood.webchatapp.domain.ResponseMsg;
import com.agarwood.webchatapp.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private IMessageService messageService;

    @PostMapping("/send")
    public ResponseMsg receiveMessage(@RequestBody Message msg) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        msg.setCreateTime(now.format(formatter));
        ResponseMsg responseMsg = new ResponseMsg();
        int number = messageService.receiveMessage(msg);
        if (number == 1) {
            responseMsg.setCode(200);
            responseMsg.setMsg("ok");
        }
        else {
            responseMsg.setCode(500);
            responseMsg.setMsg("insert error");
        }
        return responseMsg;
    }

    @PostMapping("/get")
    public List<Message> getLatestMessage(@RequestBody GroupBase groupBase){
        System.out.println("[getLatestMessage]收到userId="+groupBase.getUserId()+"的查询groupId="+groupBase.getGroupId()+"消息的请求");
        if(groupBase.getUserId()==null || groupBase.getGroupId()==null){
            System.out.println("[getLatestMessage]消息不完整,拒绝请求");
            return null;
        }
        List<Message> list=messageService.getLatestMessage(groupBase);
        System.out.println("[getLatestMessage]查询成功,结果为:"+list);
        return list;
    }
}
