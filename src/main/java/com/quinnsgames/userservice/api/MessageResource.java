package com.quinnsgames.userservice.api;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

//This resource exposes the endpoints for sending and receiving messages through the database. 
//This is not related to the websocket functionality.
@RestController 
@RequestMapping("/api") 
@RequiredArgsConstructor
public class MessageResource {
	private final MessageService messageService;
	private final UserService userService;
	
	//Messages coming to this endpoint are sent to the database, but only if the identity of user1 matches the identity of the user encoded in the access token.
	@CrossOrigin
	@PostMapping(path="/message/send")
	public ResponseEntity<String> postMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Message message) throws ServletException, IOException{
		if(!TokenAuthorizer.authorizeUser(response, request.getHeader(AUTHORIZATION), message.getUser1().getUsername())) {
			return ResponseEntity.status(FORBIDDEN).body("{\"response\": \"Looks like you aren't in this room.\"}");
		}
		
		if(message.getContent().length() > 2048) {
			return ResponseEntity.status(400).body("{\"response\": \"Message is too long.\"}");
		}
		
		messageService.PostMessage(message);
		
		return ResponseEntity.ok("{\"response\": \"Message sent successfully.\"}");
	}
	
	//This endpoint retrieves all messages shared by user1 and user2. Again, it makes sure that the users are correct.
	@CrossOrigin
	@GetMapping(path="/messages/{username1}/{username2}")
	public ResponseEntity<List<Message>> GetMessages(HttpServletRequest request, HttpServletResponse response, @PathVariable String username1, @PathVariable String username2) throws ServletException, IOException{
		if(!TokenAuthorizer.authorizeUser(response, request.getHeader(AUTHORIZATION), username1)) {
			if(!TokenAuthorizer.authorizeUser(response, request.getHeader(AUTHORIZATION), username2)) {
				return ResponseEntity.status(FORBIDDEN).body(null);
			}
		}
		User user1 = userService.getUser(username1);
		User user2 = userService.getUser(username2);
		
		List<Message> messages = messageService.GetMessages(user1, user2);
		
		for(Message m : messages) {
			m.getUser1().setPassword("");
			m.getUser2().setPassword("");
		}
		
		return ResponseEntity.ok(messages);
	}

}
