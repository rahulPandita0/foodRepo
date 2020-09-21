package com.foodapp.genericfoodapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.foodapp.genericfoodapp.dto.UpdateUserDTO;
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
	
	@PostMapping("/picture")
	public ResponseEntity<JsonNode> uploadFile(@RequestParam("file") MultipartFile file){
		userService.uploadProfilePicture(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@GetMapping("/picture")
    public ResponseEntity<Object> getImage()  {
		return ResponseEntity.ok().header("", "").contentType(MediaType.IMAGE_PNG).body(userService.getPicture());
		
    }
	
	@PutMapping("")
	public ResponseEntity<Object> UpdateUser(@Valid @RequestBody(required = true) UpdateUserDTO updateUserDTO) {
		userService.updateUser(updateUserDTO);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	
}
