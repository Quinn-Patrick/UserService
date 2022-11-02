package com.quinnsgames.userservice.api;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnsgames.userservice.domain.Message;
import com.quinnsgames.userservice.domain.User;
import com.quinnsgames.userservice.service.MessageService;
import com.quinnsgames.userservice.service.UserService;
import com.quinnsgames.userservice.utility.TokenAuthorizer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController 
@RequestMapping("/api") 
@RequiredArgsConstructor
@Slf4j
public class MessageResource {
	private final MessageService messageService;
	private final UserService userService;
	
	@CrossOrigin
	@PostMapping(path="/message/send")
	public ResponseEntity<String> postMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Message message) throws ServletException, IOException{

		if(!TokenAuthorizer.authorizeUser(response, request.getHeader(AUTHORIZATION), "User is not in this room.")) {
			if(!TokenAuthorizer.authorizeUser(response, request.getHeader(AUTHORIZATION), "User is not in this room.")) {
				return ResponseEntity.status(FORBIDDEN).body(null);
			}
		}
		
		if(message.getContent().length() > 2048) {
			return ResponseEntity.status(400).body("Message is too long.");
		}
		
		messageService.PostMessage(message);
		
		return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully.");
	}
	
	@CrossOrigin
	@GetMapping(path="/messages/{user1}/{user2}")
	public ResponseEntity<List<Message>> GetMessages(HttpServletRequest request, HttpServletResponse response, @PathVariable String username1, @PathVariable String username2) throws ServletException, IOException{
		if(!TokenAuthorizer.authorizeUser(response, request.getHeader(AUTHORIZATION), "User is not in this room.")) {
			if(!TokenAuthorizer.authorizeUser(response, request.getHeader(AUTHORIZATION), "User is not in this room.")) {
				return ResponseEntity.status(FORBIDDEN).body(null);
			}
		}
		User user1 = userService.getUser(username1);
		User user2 = userService.getUser(username2);
		
		List<Message> messages = messageService.GetMessages(user1, user2);
		
		return null;
	}

}
