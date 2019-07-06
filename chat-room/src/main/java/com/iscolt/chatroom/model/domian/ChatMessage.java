package com.iscolt.chatroom.model.domian;

import lombok.Data;

/**
 * 聊天消息的数据结构
 *
 * @Auther:https://blog.idler.work
 * @Date:2019/6/21
 * @Description:com.iscolt.chatroom.model.domian
 * @version:1.0
 */
@Data
public class ChatMessage {

    private MessageType type; // 标题
    private String content; // 内容
    private String sender;  // 给谁
    public enum MessageType {
        CHAT, // 聊天
        JOIN, // 加入聊天
        LEAVE // 离开聊天
    }
}
