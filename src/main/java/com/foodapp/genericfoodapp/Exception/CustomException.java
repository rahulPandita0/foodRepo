/**
 * 
 */
package com.foodapp.genericfoodapp.Exception;

/**
 * @author rahul.pandita
 *
 */
public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final String message;
	
	public CustomException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
