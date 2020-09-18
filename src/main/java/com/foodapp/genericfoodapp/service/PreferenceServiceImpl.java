package com.foodapp.genericfoodapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.genericfoodapp.Exception.CustomException;
import com.foodapp.genericfoodapp.dto.PreferenceDTO;
import com.foodapp.genericfoodapp.dto.UserResponseDTO;
import com.foodapp.genericfoodapp.entity.Preference;
import com.foodapp.genericfoodapp.entity.User;
import com.foodapp.genericfoodapp.repository.UserRepository;

@Service
public class PreferenceServiceImpl extends GenericService implements PreferenceService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void updatePreference(PreferenceDTO preferenceDTO) {

		Preference preference = new Preference();
		BeanUtils.copyProperties(preferenceDTO, preference);
		User user = getUser();
		user.setPreference(preference);
		userRepository.save(user);
//		return "preference updated";
	}

	private User getUser() {
		return userRepository.findById(getExecutingUser()).get();
	}

	@Override
	public List<UserResponseDTO> comparePreferences() {

		User user = getUser();
		List<UserResponseDTO> userResponseDTOs = new ArrayList<UserResponseDTO>();
		if (Objects.nonNull(user.getPreference())) {
			List<User> findByUserPreferences = userRepository.findByUserPreferences(user.getCountry(), user.getState(),
					user.getCity(), user.getPreference().getCrusine(), user.getPreference().getLunch(),
					user.getPreference().getDinner(), user.getPreference().getVegetarian(),
					user.getPreference().getNonvegetarian(), user.getPreference().getEggetarian(),user.getId());
			
			
			for (User userbypref : findByUserPreferences) {
				UserResponseDTO userResponseDTO = new UserResponseDTO();
				BeanUtils.copyProperties(userbypref, userResponseDTO);
				userResponseDTOs.add(userResponseDTO);
			}
			return userResponseDTOs;
		} else {
			throw new CustomException("first add preferences");
		}
	}

}
