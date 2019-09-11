package com.sadok.crud.util;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sadok.crud.domain.ProductBO;
import com.sadok.crud.transport.response.GenericResponse;
import com.sadok.crud.transport.response.ProductResponse;

@Service
public class ResponseBuilder {

	public GenericResponse buildResponse(ResponseCodes responseCode) {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setHttpStatus(responseCode.getHttpStatus());
		genericResponse.setStatus(responseCode.getStatus());
		return genericResponse;
	}

	public ProductResponse buildProductsResponse(ResponseCodes responseCode, List<ProductBO> products) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setHttpStatus(responseCode.getHttpStatus());
		productResponse.setStatus(responseCode.getStatus());
		productResponse.setProducts(products);
		return productResponse;
	}

}
