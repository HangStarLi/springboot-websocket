package com.springboot.websocket.controller;

import com.springboot.websocket.vo.Chat;
import com.springboot.websocket.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GreetingController {

   /*群发*/
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message){
        return message;
    }

    /*点对点*/
    @Autowired
    private SimpMessagingTemplate template;
    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat){
        String from = principal.getName();
        chat.setFrom(from);
        template.convertAndSendToUser(chat.getTo(),"/queue/chat",chat);
    }


}

