package com.quinnsgames.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quinnsgames.userservice.domain.Message;
import com.quinnsgames.userservice.domain.User;
import com.quinnsgames.userservice.repo.MessageRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class MessageServiceImplementation implements MessageService {
	private final MessageRepo messageRepo;

	@Override
	public Message PostMessage(Message message) {
		return messageRepo.save(message);
	}

	@Override
	public List<Message> GetMessages(User user1, User user2) {
		log.info("Retrieving messages shared between ", user1.getUsername(), " and ", user2.getUsername());
		log.info(user1.getId().toString());
		return messageRepo.findUserMessages(user1.getId(), user2.getId());
	}

}
