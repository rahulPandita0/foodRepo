package com.foodapp.genericfoodapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.foodapp.genericfoodapp.dto.CrusineDTO;
import com.foodapp.genericfoodapp.dto.RestaurantDTO;
import com.foodapp.genericfoodapp.dto.UpdateRestaurantDTO;
import com.foodapp.genericfoodapp.service.CrusineService;
import com.foodapp.genericfoodapp.service.RestaurantService;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	CrusineService crusineService; 

	
	@PostMapping("/register")
	public ResponseEntity<JsonNode> registerRestaurant(@Valid @RequestBody RestaurantDTO restaurant) {
		restaurantService.registerRestaurant(restaurant);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	
	
	@GetMapping("")
	public ResponseEntity<List<RestaurantDTO>> fetchRestaurant() {
		return ResponseEntity.status(HttpStatus.OK).body(restaurantService.fetchRestaurant());
	}
	
	@PostMapping("/{id}/crusine")
	public ResponseEntity<JsonNode> addCrusine(@PathVariable(required = true)Integer id ,@Valid @RequestBody List<CrusineDTO> restaurant) {
		 crusineService.addCrusineInRestaurent(restaurant, id);
		 return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<JsonNode> UpdateRestaurant(@PathVariable Integer id ,@Valid @RequestBody UpdateRestaurantDTO restaurant) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	} 
	
	

}
