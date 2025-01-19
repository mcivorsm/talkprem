package com.mcchat.mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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
     
        String aliasName = generateAlias();
        Alias alias = new Alias(aliasName, session.getId());

        aliasService.saveAlias(alias);

      
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
