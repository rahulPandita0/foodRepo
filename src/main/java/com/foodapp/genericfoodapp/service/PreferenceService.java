/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.util.List;

import com.foodapp.genericfoodapp.dto.PreferenceDTO;
import com.foodapp.genericfoodapp.dto.UserResponseDTO;

/**
 * @author rahul.pandita
 *
 */
public interface PreferenceService {
	
	
	public void updatePreference(PreferenceDTO preferenceDTO);
	
	public List<UserResponseDTO> comparePreferences();

}
