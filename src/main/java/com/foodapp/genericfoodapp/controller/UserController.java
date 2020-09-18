package com.foodapp.genericfoodapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.foodapp.genericfoodapp.dto.UserDTO;
import com.foodapp.genericfoodapp.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired 
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<JsonNode> register(@Valid @RequestBody(required = true) UserDTO user )
	{
		 userService.registerUser(user);
		 return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
}
