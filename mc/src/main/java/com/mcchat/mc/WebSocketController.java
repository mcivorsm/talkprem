package com.mcchat.mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/api")
public class WebSocketController {
    
    @Autowired
    MyWebSocketHandler handler = new MyWebSocketHandler();



    @GetMapping("/chatroom")
    public String chatPage() {
        return "index";  
    }
    @PutMapping("/{alias}")
    public String changeAlias(@PathVariable String alias){

        if(handler.changeAlias(alias)){
            return "Alias Changed Successfully.";
        }
        return "Alias taken.";
    }

   /*  @MessageMapping("/sendMessage") 
    @SendTo("/chatroom/messages")  
    public String sendMessage(String message) {
        System.out.println("Received message: " + message);
        return message; 
    }*/
}
