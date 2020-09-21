package com.foodapp.genericfoodapp.dto;

import javax.validation.constraints.Size;

public class UpdateUserDTO {
	private String country;
	private String state;
	private String city;
	@Size(min = 6,message = "message should be greater than 6 letters")
	private String password;
	private String previousPassword;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPreviousPassword() {
		return previousPassword;
	}
	public void setPreviousPassword(String previousPassword) {
		this.previousPassword = previousPassword;
	}

}
