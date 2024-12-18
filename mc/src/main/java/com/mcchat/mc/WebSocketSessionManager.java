package com.mcchat.mc;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketSessionManager {
    private final ConcurrentHashMap<String, WebSocketSession> activeSessions = new ConcurrentHashMap<>();
   

    public void addSession(WebSocketSession session) {
        activeSessions.put(session.getId(), session);
        System.out.println("New session added: " + session.getId());
    }

    public void removeSession(WebSocketSession session) {
        activeSessions.remove(session.getId());
        System.out.println("Session removed: " + session.getId());
    }

    public ConcurrentHashMap<String, WebSocketSession> getActiveSessions() {
        return activeSessions;
    }
  
 
}
