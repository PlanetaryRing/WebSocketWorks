package com.agarwood.webchatapp.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {

    /**
     * 线程安全的无序的集合
     */
    private static final CopyOnWriteArraySet<Session> SESSIONS = new CopyOnWriteArraySet<>();

    /**
     * 存储在线连接数
     */
    private static final Map<String, Session> SESSION_POOL = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        try {
            SESSIONS.add(session);
            SESSION_POOL.put(userId, session);
            System.out.println("[WebSocket]有新的连接，总数为:"+ SESSIONS.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        try {
            SESSIONS.remove(session);
            System.out.println("[WebSocket]连接断开，总数为:" + SESSIONS.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("[WebSocket]收到客户端消息:" + message);
        sendAllMessage(message);
    }

    /**
     * 此为广播消息
     *
     * @param message 消息
     */
    public void sendAllMessage(String message) {
        System.out.println("[WebSocket]广播消息:" + message);
        for (Session session : SESSIONS) {
            try {
                if (session.isOpen()) {
                    session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此为单点消息
     *
     * @param userId  用户编号
     * @param message 消息
     */
    public void sendOneMessage(String userId, String message) {
        Session session = SESSION_POOL.get(userId);
        if (session != null && session.isOpen()) {
            try {
                synchronized (session) {
                    System.out.println("[WebSocket]单点消息:" + message);
                    session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 此为单点消息(多人)
     *
     * @param userIds 用户编号列表
     * @param message 消息
     */
    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = SESSION_POOL.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    System.out.println("[WebSocket]单点消息:" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
