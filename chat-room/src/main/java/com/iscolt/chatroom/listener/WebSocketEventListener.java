package com.iscolt.chatroom.listener;

import com.iscolt.chatroom.model.domian.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * 监听会话连接和断开
 *
 * @Auther:https://blog.idler.work
 * @Date:2019/6/21
 * @Description:com.iscolt.chatroom.listener
 * @version:1.0
 */
@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        System.out.println("收到新的WebSocket连接");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = null;
        username = (String) headerAccessor.getSessionAttributes().get("username");
        if (null != username) {
            System.out.println("用户: [" + username + "] 断开连接");

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);

            // 发送消息给公共聊天频道, xx 离线
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
