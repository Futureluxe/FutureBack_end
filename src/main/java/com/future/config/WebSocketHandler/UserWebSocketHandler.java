package com.future.config.WebSocketHandler;


import com.future.util.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserWebSocketHandler extends TextWebSocketHandler {
    public static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    /**
     * 处理新的WebSocket连接
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = session.getAttributes().get("username").toString();
        sessionMap.put(username, session);

        // 发送在线用户列表
        List<String> onlineUsers = new ArrayList<>(sessionMap.keySet());
        //将onlineUsers转换为json格式,使用jackson
        WebSocketMessage<String> message = new TextMessage(JsonUtils.toJson(onlineUsers));
        for (WebSocketSession webSocketSession : sessionMap.values()) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    /**
     * 处理WebSocket消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Do nothing
    }

    /**
     * 处理WebSocket连接关闭事件
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String username = session.getAttributes().get("username").toString();
        sessionMap.remove(username);

        // 发送在线用户列表
        List<String> onlineUsers = new ArrayList<>(sessionMap.keySet());
        WebSocketMessage<String> message = new TextMessage(JsonUtils.toJson(onlineUsers));
        for (WebSocketSession webSocketSession : sessionMap.values()) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(message);
            }
        }
    }
}
