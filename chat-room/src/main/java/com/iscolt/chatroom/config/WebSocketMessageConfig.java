package com.iscolt.chatroom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 配置消息的Broker
 *
 * @Auther:https://blog.idler.work
 * @Date:2019/6/21
 * @Description:com.iscolt.chatroom.config
 * @version:1.0
 */
@Configuration
@EnableWebSocketMessageBroker // 启用WebSocket服务器
public class WebSocketMessageConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册一个WebSockJS端点, 客户端将使用它来连接到WebSocket服务器
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS(); // 当浏览器不支持webScoket时, 使用SockJS
    }

    /**
     * 配置消息代理, 用于将消息从一个客户端路由到另一个客户端
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 以/app开头的消息应该被路由到消息处理方法
        registry.setApplicationDestinationPrefixes("/app");
        // 以/topic开头的消息应该被路由到消息代理
        registry.enableSimpleBroker("/topic");
        // 在真实的生产环境中, 可以自由使用任何其他全功能的消息代理, 如RabbitMQ或ActiveMQ
    }
}
