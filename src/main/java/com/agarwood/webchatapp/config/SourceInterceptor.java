package com.agarwood.webchatapp.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;


@Component
public class SourceInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String userId = request.getParameter("userId");
        String userCookie = null;
        String redisCookie = redisTemplate.opsForValue().get(userId);
        if (redisCookie == null) {
            System.out.println("[SourceInterceptor]:用户未在缓存中注册!");
            // 此处重定向
            redirectIndex(response);
            return false;
        }
        // 寻找用户的cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userCookie = cookie.getValue();
                    break;
                }
            }
        }
        else {
            // 此处重定向
            redirectIndex(response);
            return false;
        }

        if (redisCookie != null && redisCookie.equals(userCookie)) {
            System.out.println("收到来自userId=" + userId + "的请求,验证通过");
            return true;
        }
        redirectIndex(response);
        return false;
    }

    private void redirectIndex(HttpServletResponse response){
        response.setContentType("application/json;charset=UTF-8");
        JSONObject msg=new JSONObject();
        msg.put("msg","Verification information failed");
        msg.put("code",301);
        JSONObject data=new JSONObject();
        data.put("url","/");
        msg.put("data",data);
        try (PrintWriter writer = response.getWriter()){
            writer.print(msg);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
