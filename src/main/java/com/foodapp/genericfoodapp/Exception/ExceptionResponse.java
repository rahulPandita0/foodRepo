package com.foodapp.genericfoodapp.Exception;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String errorMessage;

}
