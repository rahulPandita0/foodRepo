package com.foodapp.genericfoodapp.service;

import com.foodapp.genericfoodapp.dto.LoginDTO;
import com.foodapp.genericfoodapp.dto.LoginResponseDTO;

public interface LoginService {

	public LoginResponseDTO getToken(LoginDTO loginDTO);
	
	
}
