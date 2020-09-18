/**
 * 
 */
package com.foodapp.genericfoodapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.tools.Trace;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.foodapp.genericfoodapp.dto.ChatDTO;
import com.foodapp.genericfoodapp.dto.ChatResponseDTO;
import com.foodapp.genericfoodapp.service.ChatService;

/**
 * @author rahul.pandita
 *
 */
@RestController
@RequestMapping("chat")
public class ChatController {

	@Autowired
	ChatService chatService;

	@PostMapping("/{id}")
	public ResponseEntity<JsonNode> postMessage(@Valid @RequestBody ChatDTO chat,@PathVariable Integer id) {

		chatService.postMessage(chat, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ChatResponseDTO>> getchat(@PathVariable(required = true) Integer id) {

		return ResponseEntity.status(HttpStatus.CREATED).body(chatService.getChat(id));
		
	}
	

}
