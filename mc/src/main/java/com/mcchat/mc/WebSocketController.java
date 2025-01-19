package com.mcchat.mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class WebSocketController {
    
    private final AliasService aliasService;

    @Autowired
    public WebSocketController(AliasService aliasService){
        this.aliasService = aliasService;
    }

    @PutMapping("changeAlias/{aliasName}")
    public String putAlias(@PathVariable String aliasName, HttpServletRequest request) {
       HttpSession session = request.getSession(false);
            if (session != null) {
                 session.getId();
            } else {
                return "No active session found";
            }

            aliasService.changeAlias(aliasName, session.getId());

            return "Change Successful!";
    }


    @GetMapping("/chatroom")
    public String chatPage() {
        return "index";  
    }
   

}
