package com.foodapp.genericfoodapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preference")
public class Preference {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String crusine;
	private Boolean lunch;
	private Boolean dinner;
	private Boolean vegetarian;
	private Boolean nonvegetarian;
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
