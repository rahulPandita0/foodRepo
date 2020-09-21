/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.io.File;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import com.foodapp.genericfoodapp.dto.UpdateUserDTO;
import com.foodapp.genericfoodapp.dto.UserDTO;

/**
 * @author rahul.pandita
 *
 */
public interface UserService {
	
	public void registerUser(UserDTO user);
	
	public void uploadProfilePicture(MultipartFile file);
	
	public InputStreamResource getPicture();
	
	public void updateUser(UpdateUserDTO updateUserDTO);

}
