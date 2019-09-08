package com.sadok.crud.util;

import org.springframework.stereotype.Service;

import com.sadok.crud.transport.response.GenericResponse;

@Service
public class ResponseBuilder {
	
	public GenericResponse buildResponse(ResponseCodes responseCode) {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setHttpStatus(responseCode.getHttpStatus());
		genericResponse.setStatus(responseCode.getStatus());
		return genericResponse;
	}

}
