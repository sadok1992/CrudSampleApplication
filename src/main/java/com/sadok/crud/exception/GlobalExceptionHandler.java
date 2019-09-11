package com.sadok.crud.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sadok.crud.transport.response.GenericResponse;
import com.sadok.crud.util.ResponseBuilder;
import com.sadok.crud.util.ResponseCodes;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private ResponseBuilder responseBuilder;
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody GenericResponse handleException() {
		System.out.println("General Exception Executing...");
		return responseBuilder.buildResponse(ResponseCodes.INTERNAL_SERVER_ERROR);
	}

}
