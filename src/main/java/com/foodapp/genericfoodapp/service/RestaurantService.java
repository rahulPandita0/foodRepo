/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.util.List;

import com.foodapp.genericfoodapp.dto.RestaurantDTO;
import com.foodapp.genericfoodapp.dto.UpdateRestaurantDTO;

/**
 * @author rahul.pandita
 *
 */
public interface RestaurantService {
	
	public void registerRestaurant(RestaurantDTO restaurant);
	
	public List<RestaurantDTO> fetchRestaurant();
	
	public void updateRestaurant(Integer id,UpdateRestaurantDTO restaurantDTO);

}
