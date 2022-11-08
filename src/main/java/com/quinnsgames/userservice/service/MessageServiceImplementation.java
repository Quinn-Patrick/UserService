package com.quinnsgames.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quinnsgames.userservice.domain.Message;
import com.quinnsgames.userservice.domain.User;
import com.quinnsgames.userservice.repo.MessageRepo;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional
public class MessageServiceImplementation implements MessageService {
	private final MessageRepo messageRepo;

	@Override
	public Message PostMessage(Message message) {
		return messageRepo.save(message);
	}

	@Override
	public List<Message> GetMessages(User user1, User user2) {
		return messageRepo.findUserMessages(user1.getId(), user2.getId());
	}

}
