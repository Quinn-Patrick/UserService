package com.quinnsgames.userservice.service;

import java.util.List;

import com.quinnsgames.userservice.domain.Message;
import com.quinnsgames.userservice.domain.User;

public interface MessageService {
	Message PostMessage(Message message);
	
	List<Message> GetMessages(User user1, User user2);
}
