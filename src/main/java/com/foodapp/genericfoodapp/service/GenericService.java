package com.foodapp.genericfoodapp.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class GenericService {
	
	@Autowired
	protected HttpServletRequest httpServletRequest;
	
	public Integer getExecutingUser() {
		 return Integer.parseInt(httpServletRequest.getAttribute("USER_ID").toString()); 
		
	}

}
