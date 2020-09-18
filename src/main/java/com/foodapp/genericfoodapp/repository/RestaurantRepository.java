package com.foodapp.genericfoodapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.genericfoodapp.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	List<Restaurant> findByCountryAndStateAndCity(String country,String state,String city);

}
