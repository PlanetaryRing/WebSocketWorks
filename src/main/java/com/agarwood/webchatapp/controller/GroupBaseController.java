package com.agarwood.webchatapp.controller;

import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.User;
import com.agarwood.webchatapp.service.IGroupBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GroupInfo")
@CrossOrigin(origins = "*",maxAge = 3600)
public class GroupBaseController {
    @Autowired
    private IGroupBaseService groupBaseService;

    /**
     * 查询用户所在的列表
     * 传入参数为当前用户的ID,获取左边的群组列表
     */
    @PostMapping("/userBelong")
    public List<GroupBase> getUserBelongGroup(@RequestBody GroupBase groupBase){
        System.out.println("[getUserBelongGroup]:收到查询信息"+groupBase);
        List<GroupBase> list=groupBaseService.selectUserBelongGroup(groupBase);
        System.out.println("[getUserBelongGroup]:返回信息:"+list);
        return list;
    }

    /**
     * 查询当前群组的成员
     * 传入的参数为userId和groupId
     * 其中userId是做拦截器检验的
     */
    @PostMapping("/groupUserList")
    public List<User> getCurGroupUserList(@RequestBody GroupBase groupBase){
        if(groupBase.getUserId()==null||groupBase.getGroupId()==null){
            return null;
        }
        List<User> list=groupBaseService.getCurGroupUserList(groupBase);
        return list;
    }
}
