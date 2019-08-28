package com.sadok.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sadok.crud.domain.ProductBO;
import com.sadok.crud.transport.request.Product;
import com.sadok.crud.transport.response.GenericResponse;

@Service
public class ProductService {
	
	public List<ProductBO> products = new ArrayList<ProductBO>();
	
	public GenericResponse addProduct(Product product) {
		return new GenericResponse("Success");
		
		
	}
	

}
