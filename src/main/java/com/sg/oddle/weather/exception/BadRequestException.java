package com.sg.oddle.weather.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -1580166248644729836L;

	private String message;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super();
		this.message = message;
	}

	public BadRequestException(Exception e) {
		super(e);
	}

	public BadRequestException(String message, Exception e) {
		super(e);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
