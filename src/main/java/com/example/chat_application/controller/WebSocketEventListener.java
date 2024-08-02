package com.example.chat_application.controller;

import com.example.chat_application.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class WebSocketEventListener {
    private static final Logger logger=LoggerFactory.getLogger(WebSocketEventListener.class);

    @EventListener
    public void handelWebSocketConnectListener(SessionConnectedEvent event){
        logger.info("received a new web socket connection");
    }
    @EventListener
    public void handelWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());

        String username=(String)headerAccessor.getSessionAttributes().get("username");
        if (username!=null){
            logger.info("User Disconnected:"+username);

            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.getSender(username);

        }
    }
}
