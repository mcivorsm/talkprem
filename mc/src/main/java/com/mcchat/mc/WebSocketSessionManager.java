package com.mcchat.mc;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import jakarta.websocket.Session;

@Component
public class WebSocketSessionManager {
    private final ConcurrentHashMap<String, Session> activeSessions = new ConcurrentHashMap<>();

    public void addSession(Session session) {
        activeSessions.put(session.getId(), session);
        System.out.println("New session added: " + session.getId());
    }

    public void removeSession(Session session) {
        activeSessions.remove(session.getId());
        System.out.println("Session removed: " + session.getId());
    }

    public ConcurrentHashMap<String, Session> getActiveSessions() {
        return activeSessions;
    }
    
}
