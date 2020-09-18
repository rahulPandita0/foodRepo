package com.foodapp.genericfoodapp.dto;

import javax.validation.constraints.NotBlank;

public class CrusineDTO {
	private Integer id;
	@NotBlank(message = "name can not be null")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
