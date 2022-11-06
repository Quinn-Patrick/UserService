package com.quinnsgames.userservice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.quinnsgames.userservice.domain.Message;
import com.quinnsgames.userservice.domain.OutputMessage;

@Controller
public class ChatController {
	
	@MessageMapping("/room")
	@SendTo("/topic/messages")
	public OutputMessage send(final Message message) throws Exception{
		return new OutputMessage(message.getDate(), message.getContent(), message.getUser1(), message.getUser2());
	}
}
