
/**
 * 
 */
package com.foodapp.genericfoodapp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.foodapp.genericfoodapp.Exception.CustomException;
import com.foodapp.genericfoodapp.dto.UpdateUserDTO;
import com.foodapp.genericfoodapp.dto.UserDTO;
import com.foodapp.genericfoodapp.entity.License;
import com.foodapp.genericfoodapp.entity.User;
import com.foodapp.genericfoodapp.repository.UserRepository;
import com.foodapp.genericfoodapp.util.CommonUtils;

/**
 * @author rahul.pandita
 *
 */
@Service
public class UserServiceImpl extends GenericService implements UserService {

	@Autowired
	UserRepository userRepository;

	@Value("${profile.path}")
	private String profileDirectory;

	@Transactional
	@Override
	public void registerUser(UserDTO user) {
		// TODO Auto-generated method stub
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setLicense(generateDemoLiscence());
		userRepository.save(userEntity);
	}

	private License generateDemoLiscence() {
		License license = new License();
		license.setType("demo");
		license.setStartDate(new Date());
		license.setUpdatedOn(new Date());
		Date enddate = Date
				.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		license.setEndDate(enddate);
		license.setUpdatedBy("api");
		return license;
	}

	@Override
	public void uploadProfilePicture(MultipartFile file) {

		String fileNameExt = FilenameUtils.getExtension(file.getOriginalFilename());

		List<String> supportedFileExtensions = new ArrayList<String>();
		supportedFileExtensions.add("jpeg");
		supportedFileExtensions.add("JPEG");
		supportedFileExtensions.add("png");
		supportedFileExtensions.add("png");

		if (!supportedFileExtensions.contains(fileNameExt)) {
			throw new CustomException("invalid file");
		}

		if (file.getSize() > 1000000) {
			throw new CustomException("invalid file size ");
		}

		try {
			File uploadedFile = CommonUtils.multipartToFile(file,
					profileDirectory + CommonUtils.randomAlphaNumeric() + ".png");
			User user = getUser();
			user.setImage(uploadedFile.getName());
			userRepository.save(user);
		} catch (IOException e) {
			throw new CustomException("unable to upload file");
		}

	}

	private User getUser() {
		return userRepository.findById(getExecutingUser()).get();
	}

	@Override
	public InputStreamResource getPicture() {
		User user = getUser();
		if (StringUtils.isBlank(user.getImage())) {
			return null;
		}
		File file = new File(profileDirectory + user.getImage());

		InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			throw new CustomException("unable to fetch profile");
		}

		return resource;

	}

	@Override
	public void updateUser(UpdateUserDTO updateUserDTO) {

		User user = getUser();

		if (StringUtils.isNoneBlank(updateUserDTO.getCity())) {
			user.setCity(updateUserDTO.getCity());
		}
		if (StringUtils.isNoneBlank(updateUserDTO.getCountry())) {
			user.setCountry(updateUserDTO.getCountry());
		}
		if (StringUtils.isNoneBlank(updateUserDTO.getPassword())) {
			if(StringUtils.isBlank(updateUserDTO.getPreviousPassword())) {
				throw new CustomException("to update password previous password is mendatory");
			}
			else {
				if(!user.getPassword().equals(updateUserDTO.getPreviousPassword())) {
					throw new CustomException("previous password does not match");
				}
			}
			
			user.setPassword(updateUserDTO.getPassword());
		}
		if (StringUtils.isNoneBlank(updateUserDTO.getState())) {
			user.setState(updateUserDTO.getState());
		}
		userRepository.save(user);
	}

}
