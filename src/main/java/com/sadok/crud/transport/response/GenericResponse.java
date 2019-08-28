package com.sadok.crud.transport.response;

public class GenericResponse {

	private String status;

	public GenericResponse() {

	}

	public GenericResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
