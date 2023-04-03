package com.future.config.WebSocketHandler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionMap.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获取发送者
        String sender = session.getAttributes().get("author_id").toString();
        // 获取接收者
        String receiver = session.getAttributes().get("reception_id").toString();
        // 获取消息内容
        String payload = message.getPayload();

        String sessionId = session.getId();

        // 向接收者发送消息
        if(sessionMap.containsKey(receiver)){
            WebSocketSession receiverSession = sessionMap.get(receiver);
            if (receiverSession.isOpen()) {
                receiverSession.sendMessage(new TextMessage(sender + ":" + payload));
            }
        }
        // 向发送者发送反馈消息
        if (session.isOpen()) {
            session.sendMessage(new TextMessage("You:" + payload));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
    }
}

