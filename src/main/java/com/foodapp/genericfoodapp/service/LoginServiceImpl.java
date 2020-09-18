package com.foodapp.genericfoodapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.genericfoodapp.Exception.CustomException;
import com.foodapp.genericfoodapp.dto.LoginDTO;
import com.foodapp.genericfoodapp.dto.LoginResponseDTO;
import com.foodapp.genericfoodapp.entity.User;
import com.foodapp.genericfoodapp.repository.UserRepository;
import com.foodapp.genericfoodapp.util.TokenUtil;

@Service
public class LoginServiceImpl extends GenericService implements LoginService  {

	@Autowired
	private UserRepository userRepository;

	@Override
	public LoginResponseDTO getToken(LoginDTO loginDTO) {
		
		LoginResponseDTO responseDTO =  new LoginResponseDTO();
		
		Optional<User> user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
		if (user.isPresent()) {
			responseDTO.setToken(TokenUtil.createToken(user.get().getId()));
		}else {
			throw new CustomException("invalid credentials");
		}
		return responseDTO;
		
	}
}
