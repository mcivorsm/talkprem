package com.mcchat.mc;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;




@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer,     WebMvcConfigurer{
    
    private final AliasService as;
    

   
    public WebSocketConfig(AliasService as) {
        this.as = as;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
            .allowedOrigins("http://127.0.0.1:5500") // Allow the frontend origin
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific HTTP methods
            .allowCredentials(true) // Allow credentials (cookies, authorization headers, etc.)
            .allowedHeaders("*") // Allow all headers
            .maxAge(3600); // Cache the preflight request for 1 hour
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(new MyWebSocketHandler(as), "/websocket")
        .setAllowedOrigins("http://127.0.0.1:5500");
        
        
    }
  

}
