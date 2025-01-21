package com.mcchat.mc;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class WebSocketController {
    
    private final AliasService aliasService;

   
    public WebSocketController(AliasService aliasService){
        this.aliasService = aliasService;
    }
   
    @GetMapping("/set-cookie")
    public ResponseEntity<Void> setCookie() {
        String sessionId = "sid" + (int) (Math.random() * 10000);
        System.out.println("Setting Session Cookie: " + sessionId);
        
        return ResponseEntity.ok()
                .header("Set-Cookie", "sessionCookie=" + sessionId + "; Path=/; Domain=localhost; Secure; HttpOnly; SameSite=None")
                .build();
    }

   
    @PutMapping("changeAlias/{aliasName}")
    @ResponseBody
    public String putAlias(@PathVariable String aliasName, @CookieValue("sessionCookie=") String sessionId) {
        System.out.println("Change Alias Controller Point Hit***");
    
      
        
       if (sessionId == null) {
           return "No active WebSocket session found";
       }

            aliasService.changeAlias(aliasName, sessionId);

            return "Change Successful!";
    }


    @GetMapping("/chatroom")
    public String chatPage() {
        return "index";  
    }
   

}
