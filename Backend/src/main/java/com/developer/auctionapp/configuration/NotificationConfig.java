package com.developer.auctionapp.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Controller
public class NotificationConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker (MessageBrokerRegistry messageBrokerRegistry) {
        messageBrokerRegistry.enableSimpleBroker("/topic/", "/queue/", "/user/");
        messageBrokerRegistry.setApplicationDestinationPrefixes("/auctionapp");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //stompEndpointRegistry.addEndpoint("/ws").setAllowedOrigins("http://localhost:4200").withSockJS();
        stompEndpointRegistry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}
