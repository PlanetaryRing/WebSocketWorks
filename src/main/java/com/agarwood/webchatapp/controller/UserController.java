package com.agarwood.webchatapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.agarwood.webchatapp.domain.GroupBase;
import com.agarwood.webchatapp.domain.LoginInfo;
import com.agarwood.webchatapp.domain.ResponseMsg;
import com.agarwood.webchatapp.domain.User;
import com.agarwood.webchatapp.service.IGroupBaseService;
import com.agarwood.webchatapp.service.IUserService;
import com.agarwood.webchatapp.until.MD5Until;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupBaseService groupBaseService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/getSelfInfo")
    public User getSelfInfo(@RequestBody User user) {
        System.out.println("[getSelfInfo]收到查询个人信息的请求,参数为:"+user);
        if (user.getUserId() == null) {
            return null;
        }
        User responseUser=userService.getSelfInfo(user);
        System.out.println("[getSelfInfo]返回信息:"+responseUser);
        return responseUser;
    }

    @PostMapping("/login")
    public ResponseMsg UserLogin(HttpServletRequest request, HttpServletResponse response) {
        LoginInfo loginInfo = new LoginInfo();
        String requestBody = "";
        try {
            // 从请求体中获取数据
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            requestBody = sb.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject recvData= JSON.parseObject(requestBody);
        loginInfo.setUsername(recvData.getString("username"));
        loginInfo.setPassword(recvData.getString("password"));
        System.out.println("[UserLogin]:收到登录信息" + loginInfo);
        ResponseMsg msg = new ResponseMsg();
        JSONObject data = new JSONObject();
        loginInfo.setPassword(MD5Until.encrypt(loginInfo.getPassword()));
        User userResult = userService.verifyUser(loginInfo);
        if (userResult != null && userResult.getUserId() != null) {
            data.put("url", "/pages/chat/groupChatPage.html?userId=" + userResult.getUserId());
            data.put("userId", userResult.getUserId());
            msg.setMsg("ok");
            msg.setCode(200);
            msg.setData(data.toString());

            // 生成缓存
            Random random=new Random();
            String token = Integer.toString(random.nextInt());
            String userId=Integer.toString(data.getInteger("userId"));
            token= MD5Until.encrypt(token);
            System.out.println("将userId:"+userId+"的cookie设置为:"+token);

            // 将cookie添加到用户的浏览器
            Cookie cookie=new Cookie("userId",token);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);

            // 将token添加到redis缓存中
            redisTemplate.opsForValue().set(userId,token);
            redisTemplate.expire(userId,3600, TimeUnit.SECONDS);
        }
        else {
            msg.setMsg("账号密码不匹配!");
            msg.setCode(400);
        }
        return msg;
    }

    @PostMapping("/register")
    public ResponseMsg registerUser(@RequestBody LoginInfo loginInfo){
        System.out.println("[registerUser]收到注册请求:"+loginInfo);
        loginInfo.setPassword(MD5Until.encrypt(loginInfo.getPassword()));
        int result = userService.registerUser(loginInfo);
        ResponseMsg responseMsg=new ResponseMsg();
        if(result==0){
            responseMsg.setCode(500);
            responseMsg.setMsg("账号已存在!");
        }else {
            responseMsg.setCode(200);
            responseMsg.setMsg("ok");
            JSONObject data=new JSONObject();
            data.put("url","/");
            responseMsg.setData(data.toString());
        }
        User newUser=new User();
        newUser.setUsername(loginInfo.getUsername());
        User user=userService.queryByUsername(newUser);
        GroupBase groupBase=new GroupBase();
        groupBase.setGroupId(1);
        groupBase.setUserId(Long.valueOf(user.getUserId()));
        int addResult=groupBaseService.addUserIntoGroup(groupBase);
        System.out.println("[registerUser]已将用户添加到默认群组,返回结果是"+addResult);
        System.out.println("[registerUser]注册返回信息"+responseMsg);
        return responseMsg;
    }
}
