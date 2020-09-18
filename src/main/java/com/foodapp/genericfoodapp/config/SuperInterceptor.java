package com.foodapp.genericfoodapp.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.genericfoodapp.Exception.ExceptionResponse;
import com.foodapp.genericfoodapp.entity.User;
import com.foodapp.genericfoodapp.repository.UserRepository;
import com.foodapp.genericfoodapp.util.TokenUtil;

import io.jsonwebtoken.Claims;

public class SuperInterceptor implements HandlerInterceptor {
	
	@Autowired
	UserRepository userRepository; 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String token;
		System.out.println("pre handling");
		if (StringUtils.isNotBlank(request.getHeader("auth"))) {

			token = request.getHeader("auth");
			if (StringUtils.isBlank(token)) {
				unAuthorized(response);
				return false;
			}

			Claims decodeJWT = null;

			try {
				decodeJWT = TokenUtil.decodeJWT(token);
			} catch (Exception e) {
				unAuthorized(response);
				return false;
			}

			
			
			if (Objects.isNull(decodeJWT.getId())) {
				unAuthorized(response);
				return false;
			}

			 Optional<User> user = userRepository.findById(Integer.parseInt(decodeJWT.getId()));
			if (!user.isPresent()) {
				unAuthorized(response);
				return false;
			}

			request.setAttribute("USER_ID", Integer.parseInt(decodeJWT.getId()));
			

		} else {
			unAuthorized(response);
			return false;
		}
		return true;
	}

	private void unAuthorized(HttpServletResponse response) throws IOException {
		ExceptionResponse errors = new ExceptionResponse();
		errors.setStatus(HttpStatus.UNAUTHORIZED.value());
		errors.setErrorMessage("unauthorized");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(errors));
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

}
