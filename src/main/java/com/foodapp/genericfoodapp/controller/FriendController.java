package com.foodapp.genericfoodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.foodapp.genericfoodapp.dto.FriendResponseDTO;
import com.foodapp.genericfoodapp.service.FriendService;

@RestController
@RequestMapping("friend")
public class FriendController {

	@Autowired
	FriendService friendService; 
	
	@PostMapping("{id}/request")
	public ResponseEntity<JsonNode> PostFriendRequest(@PathVariable(required = true) Integer id) {
		friendService.postFriendRequest(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@PostMapping("{id}/respond/{response}")
	public ResponseEntity<JsonNode> respondFriendRequest(@PathVariable(required = true) Integer id,@PathVariable(required = true) Boolean response) {
		friendService.respondFriendRequest(id, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@GetMapping("")
	public List<FriendResponseDTO> fetchFriendList(){
		return friendService.fetchAllFriends();
	}
	
	@GetMapping("/pending")
	public List<FriendResponseDTO> fetchPendingFriendList(){
		return friendService.fetchPendingFriends();
	}
}
