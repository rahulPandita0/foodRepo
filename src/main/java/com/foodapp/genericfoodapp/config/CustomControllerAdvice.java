package com.foodapp.genericfoodapp.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foodapp.genericfoodapp.Exception.CustomException;
import com.foodapp.genericfoodapp.Exception.ExceptionResponse;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders header, HttpStatus status, WebRequest request) {
		ExceptionResponse response =  new ExceptionResponse();
//		response.setErrorMessage(e.getBindingResult());
		if(StringUtils.isNotBlank(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())) {
			response.setErrorMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		}else {
			response.setErrorMessage("invalid request body");
		}
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	}
	
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e,WebRequest request){
		ExceptionResponse response =  new ExceptionResponse();
		response.setErrorMessage(e.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException e,WebRequest request){
		ExceptionResponse response =  new ExceptionResponse();
		System.out.println("Internal server error");
		e.printStackTrace();
		response.setErrorMessage("internal server error");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleRunTimeException(Exception e,WebRequest request){
		ExceptionResponse response =  new ExceptionResponse();
		System.out.println("Internal server error");
		e.printStackTrace();
		response.setErrorMessage("internal server error");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	
	
	
}
