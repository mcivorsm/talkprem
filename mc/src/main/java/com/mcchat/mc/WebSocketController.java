package com.mcchat.mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class WebSocketController {
    
    private final AliasService aliasService;

    @Autowired
    public WebSocketController(AliasService aliasService){
        this.aliasService = aliasService;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("changeAlias/{aliasName}")
    @ResponseBody
    public String putAlias(@PathVariable String aliasName, HttpSession httpSession) {
        System.out.println("Change Alias Controller Point Hit***");
    
       String websocketSessionId = (String) httpSession.getAttribute("websocketSessionId");
        
       if (websocketSessionId == null) {
           return "No active WebSocket session found";
       }

            aliasService.changeAlias(aliasName, websocketSessionId);

            return "Change Successful!";
    }


    @GetMapping("/chatroom")
    public String chatPage() {
        return "index";  
    }
   

}
