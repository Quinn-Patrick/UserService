package com.quinnsgames.userservice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.quinnsgames.userservice.domain.Message;

import lombok.extern.slf4j.Slf4j;


//This is the controller for exchanging messages via websockets.
//All it actually does is ping the client letting them know that there may be a new message to read.
//When this happens, the client will ask the database for messages.
@Controller
@Slf4j
public class ChatController {
	
	@MessageMapping("/room")
	@SendTo("/topic/messages")
	public String send(final Message message) throws Exception{
		log.info("Received message");
		return "Message received.";
	}
}
