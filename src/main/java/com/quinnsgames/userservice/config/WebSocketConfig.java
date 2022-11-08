package com.quinnsgames.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

//The web socket config class.
@Configuration
@EnableWebSocketMessageBroker
@Slf4j
@Controller
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	//This method puts the message broker on the /topic endpoint and prefixes /app to the endpoint for sending messages.
	@Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
	
	//This is the important one. It is called when with the configuration class and exposes the /chat endpoint for connecting
	//to the websocket. The only allowed origin is the angular frontend running on localhost port 4200.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	log.info("#####Registering Endpoint######");
         registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:4200").withSockJS();
    }
}
