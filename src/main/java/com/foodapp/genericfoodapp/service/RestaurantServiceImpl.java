/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.genericfoodapp.dto.RestaurantDTO;
import com.foodapp.genericfoodapp.entity.Restaurant;
import com.foodapp.genericfoodapp.entity.User;
import com.foodapp.genericfoodapp.repository.RestaurantRepository;
import com.foodapp.genericfoodapp.repository.UserRepository;

/**
 * @author rahul.pandita
 *
 */
@Service
public class RestaurantServiceImpl extends GenericService implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void registerRestaurant(RestaurantDTO restaurant) {

		Restaurant restaurantEntity = new Restaurant();
		BeanUtils.copyProperties(restaurant, restaurantEntity);
		restaurantRepository.save(restaurantEntity);

	}

	@Override
	public List<RestaurantDTO> fetchRestaurant() {
		User sender = getUser();
		List<RestaurantDTO> restaurantDTOs = new ArrayList<RestaurantDTO>();
		List<Restaurant> restaurants = restaurantRepository.findByCountryAndStateAndCity(sender.getCountry(),
				sender.getState(), sender.getCity());
		for (Restaurant restaurant : restaurants) {
			RestaurantDTO restaurantDTO = new RestaurantDTO();
			BeanUtils.copyProperties(restaurant, restaurantDTO);
			restaurantDTOs.add(restaurantDTO);
		}
		return restaurantDTOs;
	}

	private User getUser() {
		return userRepository.findById(getExecutingUser()).get();
	}

}
