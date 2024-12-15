package com.mcchat.mc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class WebSocketController {
    

    @GetMapping("/chatroom")
    public String chatPage() {
        return "index";  
    }

   /*  @MessageMapping("/sendMessage") 
    @SendTo("/chatroom/messages")  
    public String sendMessage(String message) {
        System.out.println("Received message: " + message);
        return message; 
    }*/
}
