package com.mcchat.mc;

import java.util.concurrent.ConcurrentHashMap;

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
     private final ConcurrentHashMap<String, String> activeAliases = new ConcurrentHashMap<>();

     private  String sessionId;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New client connected: " + session.getId());
        sessionManager.addSession(session);
        this.sessionId = session.getId();
        String alias = generateAlias();
        activeAliases.put(session.getId(), alias);
      
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
        activeAliases.remove(session.getId());
        sessionManager.removeSession(session);
    }

    @OnClose
    public void handleClose(WebSocketSession session){
        sessionManager.removeSession(session);

    }

    public String generateAlias(){
      String alias = "";
      while(!activeAliases.containsValue(alias)){
        alias = "User" + (int)(Math.random() * 10000);
      }
    
      return alias;
    }
    public boolean changeAlias(String alias){
        for(String abc: activeAliases.values()){
            if(alias.equals(abc)){
                return false;
            }
        }
        activeAliases.put(this.sessionId, alias);
        return true;
    }

    public String getSessionIdByAlias(String alias) {
        return activeAliases.get(alias);
    }
   
}
