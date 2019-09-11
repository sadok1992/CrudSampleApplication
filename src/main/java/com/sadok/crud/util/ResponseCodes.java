package com.sadok.crud.util;

import org.springframework.http.HttpStatus;

import lombok.Getter;
 
@Getter
public enum ResponseCodes {
	
	SUCCESS_CREATE("Success",HttpStatus.CREATED),
	SUCCESS_RETRIEVE("Success",HttpStatus.OK),
	MISSING_INPUT("Missing Inputs",HttpStatus.PRECONDITION_FAILED),
	INVALID_INPUT("Invalid Input Provided",HttpStatus.EXPECTATION_FAILED),
	PRODUCT_ALREADY_EXIST("Product Already exist in DB",HttpStatus.EXPECTATION_FAILED),
	INTERNAL_SERVER_ERROR("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
	
	ResponseCodes(String status,HttpStatus httpStatus){
		this.status = status;
		this.httpStatus = httpStatus;
	}
	 
	private String status; 
	private HttpStatus httpStatus;

}
