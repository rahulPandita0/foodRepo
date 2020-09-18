package com.foodapp.genericfoodapp.dto;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {

	@NotEmpty(message = "email can not be empty")
	private String email;
	
	@NotEmpty(message = "password can not be empty")
	private String password;

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
