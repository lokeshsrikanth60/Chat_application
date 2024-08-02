package com.example.chat_application.controller;

import com.example.chat_application.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage){
        return chatMessage;
    }
    @MessageMapping("/chat.adduser")
    @SendTo("/topic/public")
    public ChatMessage adduser(ChatMessage chatMessage){
        return chatMessage;
    }
}
