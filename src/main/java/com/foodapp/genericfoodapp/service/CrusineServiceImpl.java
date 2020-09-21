package com.foodapp.genericfoodapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.genericfoodapp.Exception.CustomException;
import com.foodapp.genericfoodapp.dto.CrusineDTO;
import com.foodapp.genericfoodapp.entity.Crusine;
import com.foodapp.genericfoodapp.entity.Restaurant;
import com.foodapp.genericfoodapp.repository.CrusineRepository;
import com.foodapp.genericfoodapp.repository.RestaurantRepository;

@Service
public class CrusineServiceImpl extends GenericService implements CrusineService{
	
	@Autowired
	private RestaurantRepository restaurantRepository; 
	
	@Autowired
	private CrusineRepository crusineRepository; 

	@Override
	public void addCrusineInRestaurent(List<CrusineDTO> crusines, Integer resraurantId) {
		
		 Optional<Restaurant>  restaurant = restaurantRepository.findById(resraurantId);
		 if(restaurant.isPresent())
		 {
			 if(!restaurant.get().getUpdatedBy().getId().equals(getExecutingUser())) {
				 throw new CustomException("unauthorized to edit");
			 }
			 
			 List<Crusine> crusineList = new ArrayList<Crusine>();
			 for(CrusineDTO crusineDTO :crusines) {
				 Crusine crusine =  new Crusine();
				 crusine.setCrusine(crusineDTO.getName());
				 crusine.setRestaurant(restaurant.get());
				 crusineList.add(crusine);
			 }
			 crusineRepository.saveAll(crusineList);
		 }
		 else
		 {
			 throw new CustomException("restaurant not found");
		 }
		
	}

}
