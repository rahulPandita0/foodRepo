package com.foodapp.genericfoodapp.service;

import java.util.List;

import com.foodapp.genericfoodapp.dto.CrusineDTO;

public interface CrusineService {
	
	public void addCrusineInRestaurent(List<CrusineDTO> crusines,Integer resraurantId);

}
