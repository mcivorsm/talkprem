package com.mcchat.mc;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
@Controller
public class WebSocketController {
    
    @MessageMapping("/send")
    @SendTo("/chatroom/messages")
    public String sendMessage(String message){
        return message;
    }
}
