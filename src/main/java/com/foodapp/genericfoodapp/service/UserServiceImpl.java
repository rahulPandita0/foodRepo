/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodapp.genericfoodapp.dto.UserDTO;
import com.foodapp.genericfoodapp.entity.License;
import com.foodapp.genericfoodapp.entity.User;
import com.foodapp.genericfoodapp.repository.UserRepository;

/**
 * @author rahul.pandita
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	@Override
	public void registerUser(UserDTO user) {
		// TODO Auto-generated method stub
		User userEntity =  new User();
		BeanUtils.copyProperties(user, userEntity); 
		userEntity.setLicense(generateDemoLiscence());
		userRepository.save(userEntity);
	}

	
	private License generateDemoLiscence()
	{
		License license =  new License();
		license.setType("demo");
		license.setStartDate(new Date());
		license.setUpdatedOn(new Date());
		Date enddate =  Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		license.setEndDate(enddate);
		license.setUpdatedBy("api");
		return license;
	}
	
}
