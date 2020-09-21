package com.foodapp.genericfoodapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String country;
	private String state;
	private String city;
	private String address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by",referencedColumnName = "id")
	private User UpdatedBy;
	
	@OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY)
	private Set<Crusine> crusine;
	
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
	public Set<Crusine> getCrusine() {
		return crusine;
	}
	public void setCrusine(Set<Crusine> crusine) {
		this.crusine = crusine;
	}
	public User getUpdatedBy() {
		return UpdatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		UpdatedBy = updatedBy;
	}

}
