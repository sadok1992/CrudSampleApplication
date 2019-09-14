package com.sadok.crud.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sadok.crud.transport.response.GenericResponse;
import com.sadok.crud.util.LogAround;
import com.sadok.crud.util.ResponseBuilder;
import com.sadok.crud.util.ResponseCodes;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private ResponseBuilder responseBuilder;
	
	@ExceptionHandler(Exception.class)
	@LogAround
	public @ResponseBody GenericResponse handleException(HttpServletResponse response, Exception ex) {
		ex.printStackTrace();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return responseBuilder.buildResponse(ResponseCodes.INTERNAL_SERVER_ERROR);
	}

}
