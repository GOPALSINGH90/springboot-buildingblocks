package com.restservice.exceptions;

import java.util.Date;

public class CustomError {
	private Date timestamp;
	private String message;
	private String errorDatails;

	public CustomError(Date timestamp, String message, String errorDatails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDatails = errorDatails;
	}

	public CustomError() {
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorDatails() {
		return errorDatails;
	}

	public void setErrorDatails(String errorDatails) {
		this.errorDatails = errorDatails;
	}

}
