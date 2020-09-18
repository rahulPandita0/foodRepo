package com.foodapp.genericfoodapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.genericfoodapp.dto.LoginDTO;
import com.foodapp.genericfoodapp.dto.LoginResponseDTO;
import com.foodapp.genericfoodapp.service.LoginService;

@RestController
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@PostMapping("")
	public ResponseEntity<LoginResponseDTO> Login(@Valid @RequestBody(required = true) LoginDTO loginDTO) {
		
		return ResponseEntity.status(HttpStatus.OK).body(loginService.getToken(loginDTO));
		
	}
}
