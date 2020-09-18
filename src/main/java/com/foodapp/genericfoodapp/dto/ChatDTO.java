package com.foodapp.genericfoodapp.dto;

import javax.validation.constraints.NotBlank;

public class ChatDTO {

	@NotBlank(message = "message can not be blank")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
