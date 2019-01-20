package com.sg.oddle.weather.exception;

public class Error {
	private String error;
	private String message;
	private String status;
	private String path;
	private String exception;
	private long timestamp;

	public Error() {
		super();
	}

	public Error(String status, String message, String error, String path, String exception, long timestamp) {
		super();
		this.status = status;
		this.message = message;
		this.error = error;
		this.path = path;
		this.exception = exception;
		this.timestamp = timestamp;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
