package com.sadok.crud.transport.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse {

	private String status;
	
	@JsonIgnore
	private HttpStatus httpStatus;

	public GenericResponse() {

	}

	public GenericResponse(String status) {
		this.status = status;
	}


}
