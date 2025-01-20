package com.mcchat.mc;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, 
                                   org.springframework.web.socket.WebSocketHandler wsHandler, 
                                   Map<String, Object> attributes) throws Exception {
        
        String sessionId = "sid" + (int)(Math.random() * 10000);
        System.out.println("Handshake Interceptor hit ID: " + sessionId);
        response.getHeaders().add("Set-Cookie", "sessionCookie=" + sessionId + " ; HttpOnly; Secure; Path=/");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, 
                                org.springframework.web.socket.WebSocketHandler wsHandler, 
                                Exception exception) {
        // Post-handshake logic (if needed)
    }
}