package com.quinnsgames.userservice.domain;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class OutputMessage {
	private Date date;
	private String content;
	private User user1;
	private User user2;
	
	
}
