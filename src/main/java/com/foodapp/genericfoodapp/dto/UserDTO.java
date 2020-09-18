package com.foodapp.genericfoodapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
	@NotEmpty(message = "Name can not be blank")
	private String name;
	@NotEmpty(message = "email can not be blank")
	private String email;
	@NotEmpty(message = "country can not be blank")
	private String country;
	@NotEmpty(message = "state can not be blank")
	private String state;
	@NotEmpty(message = "city can not be blank")
	private String city;
	private PreferenceDTO preference;
	private LicenseDTO license;
	@Size(min = 6,message = "message should be greater than 6 letters")
	@NotEmpty(message = "password can not be blank")
	private String password;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public PreferenceDTO getPreference() {
		return preference;
	}
	public void setPreference(PreferenceDTO preference) {
		this.preference = preference;
	}
	public LicenseDTO getLicense() {
		return license;
	}
	public void setLicense(LicenseDTO license) {
		this.license = license;
	}

	
}
