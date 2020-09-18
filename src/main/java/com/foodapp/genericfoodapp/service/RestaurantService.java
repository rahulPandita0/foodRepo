/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.util.List;

import com.foodapp.genericfoodapp.dto.RestaurantDTO;

/**
 * @author rahul.pandita
 *
 */
public interface RestaurantService {
	
	public void registerRestaurant(RestaurantDTO restaurant);
	
	public List<RestaurantDTO> fetchRestaurant();

}
