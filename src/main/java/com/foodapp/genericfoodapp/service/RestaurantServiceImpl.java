/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.genericfoodapp.Exception.CustomException;
import com.foodapp.genericfoodapp.dto.RestaurantDTO;
import com.foodapp.genericfoodapp.dto.UpdateRestaurantDTO;
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
		restaurantEntity.setUpdatedBy(getUser());
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

	@Override
	public void updateRestaurant(Integer id, UpdateRestaurantDTO restaurantDTO) {
		
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if(restaurant.isPresent()) {
			if(!restaurant.get().getUpdatedBy().getId().equals(getExecutingUser())) {
				throw new CustomException("not authorized to update");
			}else {
				if(StringUtils.isNoneBlank(restaurantDTO.getAddress())) {
					restaurant.get().setAddress(restaurantDTO.getAddress());
				}
				if(StringUtils.isNoneBlank(restaurantDTO.getCity())){
					restaurant.get().setCity(restaurantDTO.getCity());
				}
				if(StringUtils.isNoneBlank(restaurantDTO.getCountry())) {
					restaurant.get().setCountry(restaurantDTO.getCountry());
				}
				if(StringUtils.isNoneBlank(restaurantDTO.getState())) {
					restaurant.get().setState(restaurantDTO.getState());
				}
				restaurantRepository.save(restaurant.get());
			}
			
		}else {
			throw new CustomException("unable to find restaurant");
		}
		
	}

}
