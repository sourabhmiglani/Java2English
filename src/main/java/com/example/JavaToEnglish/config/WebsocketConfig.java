package com.example.JavaToEnglish.config;


import com.example.JavaToEnglish.Service.WebsocketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Conditional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ConditionalOnExpression("${enable-websocket}")
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${websocket.simple-broker.destinations}")
    private String[] simpleBrokerDestinations;

    @Value("${websocket.application-destination-prefixes}")
    private String applicationDestinationPrefixes;

    @Value("${websocket.endpoint}")
    private String websocketEndpoint;

    @Value("${websocket.allowed-origin-patterns}")
    private String allowedOriginPatterns;

    WebsocketConfig(){
        System.out.println("Websocket Configured");
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(simpleBrokerDestinations);
        config.setApplicationDestinationPrefixes(applicationDestinationPrefixes);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(websocketEndpoint)
                .setAllowedOriginPatterns(allowedOriginPatterns)
                .withSockJS();
        System.out.println("Wensocket endpoint registered");
    }
}