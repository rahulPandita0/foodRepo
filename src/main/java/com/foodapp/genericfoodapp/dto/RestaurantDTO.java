package com.foodapp.genericfoodapp.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class RestaurantDTO {
	
	private Integer id;
	@NotBlank(message = "name can not be null")
	private String name;
	@NotBlank(message = "country can not be null")
	private String country;
	@NotBlank(message = "state can not be null")
	private String state;
	@NotBlank(message = "city can not be null")
	private String city;
	@NotBlank(message = "address can not be null")
	private String address;
	
	private Set<CrusineDTO> crusine;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<CrusineDTO> getCrusine() {
		return crusine;
	}
	public void setCrusine(Set<CrusineDTO> crusine) {
		this.crusine = crusine;
	}

}
