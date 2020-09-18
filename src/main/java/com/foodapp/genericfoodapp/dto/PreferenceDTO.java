package com.foodapp.genericfoodapp.dto;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class PreferenceDTO {

	private Integer id;
	@NotBlank(message =  "crusine can not be blank")
	private String crusine;
	@NotNull
	private Boolean lunch;
	@NotNull
	private Boolean dinner;
	@NotNull
	private Boolean vegetarian;
	@NotNull
	private Boolean nonvegetarian;
	@NotNull
	private Boolean eggetarian;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCrusine() {
		return crusine;
	}
	public void setCrusine(String crusine) {
		this.crusine = crusine;
	}
	public Boolean getLunch() {
		return lunch;
	}
	public void setLunch(Boolean lunch) {
		this.lunch = lunch;
	}
	public Boolean getDinner() {
		return dinner;
	}
	public void setDinner(Boolean dinner) {
		this.dinner = dinner;
	}
	public Boolean getVegetarian() {
		return vegetarian;
	}
	public void setVegetarian(Boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
	public Boolean getNonvegetarian() {
		return nonvegetarian;
	}
	public void setNonvegetarian(Boolean nonvegetarian) {
		this.nonvegetarian = nonvegetarian;
	}
	public Boolean getEggetarian() {
		return eggetarian;
	}
	public void setEggetarian(Boolean eggetarian) {
		this.eggetarian = eggetarian;
	}
	
	
}
