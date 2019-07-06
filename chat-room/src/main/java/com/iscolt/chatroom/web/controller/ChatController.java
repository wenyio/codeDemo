package com.iscolt.chatroom.web.controller;

import com.iscolt.chatroom.model.domian.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 聊天控制器, 处理前端发送的事件和请求
 *
 * @Auther:https://blog.idler.work
 * @Date:2019/6/21
 * @Description:com.iscolt.chatroom.web.controller
 * @version:1.0
 */
@Controller
public class ChatController {

    /**
     * 响应页面请求
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 发送聊天消息
     *
     * @param chatMessage
     * @return
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    /**
     * 将登录的用户信息 添加到会话中
     *
     * @param chatMessage
     * @param headerAccessor
     * @return
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // 添加 username 到 WebSocket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
