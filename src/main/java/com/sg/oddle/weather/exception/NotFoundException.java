package com.sg.oddle.weather.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9219213338307160099L;

	private String message;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super();
		this.message = message;
	}

	public NotFoundException(Exception e) {
		super(e);
	}

	public NotFoundException(String message, Exception e) {
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
