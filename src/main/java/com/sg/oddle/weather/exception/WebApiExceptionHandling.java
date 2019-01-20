package com.sg.oddle.weather.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(basePackages = { "com.sg.oddle.weather.api" })
@RestController
public class WebApiExceptionHandling {


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BadRequestException.class)
	public Error handleBaseException(BadRequestException e, HttpServletRequest req) {
		return new Error("400", e.getMessage(), "invalid_input", req.getRequestURI(), e.getClass().getSimpleName(),
				System.currentTimeMillis());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NotFoundException.class)
	public Error handleNotFoundException(NotFoundException e, HttpServletRequest req) {
		return new Error("404", e.getMessage(), "not_found", req.getRequestURI(), e.getClass().getSimpleName(),
				System.currentTimeMillis());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public Error handleException(Exception e, HttpServletRequest req) {
		return new Error("500", e.getMessage(), "server_error", req.getRequestURI(), e.getClass().getSimpleName(),
				System.currentTimeMillis());
	}
}
