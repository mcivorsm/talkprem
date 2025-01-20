package com.mcchat.mc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnClose;


@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private final WebSocketSessionManager sessionManager = new WebSocketSessionManager();
    private final AliasService aliasService;

    @Autowired
    public MyWebSocketHandler(AliasService aliasService){
        this.aliasService = aliasService;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New client connected: " + session.getId());
        sessionManager.addSession(session);

        Map<String, Object> cookieAttributes = session.getAttributes();
        String sessionId = (String)cookieAttributes.get("sessionCookie=");
        System.out.println(cookieAttributes.get("sessionCookie="));
        if (sessionId != null) {
            System.out.println("Cookie Value: " + sessionId);
        } else {
            System.out.println("Cookie not found");
        }
        String aliasName = generateAlias();
        Alias alias = new Alias(aliasName, sessionId);

        aliasService.addAlias(alias);

        HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");
        if (httpSession != null) {
            httpSession.setAttribute("websocketSessionId", session.getId());
        }
      
    }

      @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message from client: " + message.getPayload());
        for (WebSocketSession activeSession : sessionManager.getActiveSessions().values()) {
            if (activeSession.isOpen()) { // Check if the session is still active
                activeSession.sendMessage(new TextMessage(message.getPayload()));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Client disconnected: " + session.getId());
      
        sessionManager.removeSession(session);
    }

    @OnClose
    public void handleClose(WebSocketSession session){
        sessionManager.removeSession(session);

    }

    public String generateAlias(){
      String alias;
     
        alias = "User" + (int)(Math.random() * 10000);
      
    
      return alias;
    }
    
    
   
}
