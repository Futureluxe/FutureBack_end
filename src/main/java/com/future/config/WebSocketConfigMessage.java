package com.future.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigMessage implements WebSocketMessageBrokerConfigurer  {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) { // 配置消息代理
        config.enableSimpleBroker("/topic"); // 启用简单消息代理 用于广播式应用程序
        config.setApplicationDestinationPrefixes("/app");   // 设置应用程序前缀 用于过滤消息 例如：/app/chat
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // 注册STOMP端点 用于连接 WebSocket
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS(); // 注册STOMP端点，指定使用SockJS协议 用于前端和后端的连接
    }
}
