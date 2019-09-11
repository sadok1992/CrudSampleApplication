package com.sadok.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sadok.crud.domain.ProductBO;
import com.sadok.crud.repository.ProductRepository;
import com.sadok.crud.transport.request.Product;
import com.sadok.crud.transport.response.GenericResponse;
import com.sadok.crud.transport.response.ProductResponse;
import com.sadok.crud.util.GenericAssister;
import com.sadok.crud.util.ResponseBuilder;
import com.sadok.crud.util.ResponseCodes;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ResponseBuilder responseBuilder;

	public GenericResponse addProduct(Product product) {
		
		GenericResponse genericResponse = validateProductForCreate(product);
		if(genericResponse!=null) {
			return genericResponse;
		}
		productRepository.save(createProductBO(product));
		return responseBuilder.buildResponse(ResponseCodes.SUCCESS_CREATED);
	}
	
	public GenericResponse updateProduct(Product product) {
		GenericResponse genericResponse = validateProductForUpdate(product);
		if(genericResponse!=null) {
			return genericResponse;
		}
		Optional<ProductBO> optionalProductBO = productRepository.findById(product.getRef()); 
		if(optionalProductBO.isPresent()) {
			productRepository.save(createProductBO(product));
			genericResponse = responseBuilder.buildResponse(ResponseCodes.SUCCESS_OK);
		}else {
			genericResponse = responseBuilder.buildResponse(ResponseCodes.PRODUCT_NOT_EXIST);
		}
		return genericResponse;
	}

	public GenericResponse listProducts() {
		System.out.println("List all Products...");
		List<ProductBO> products = (List<ProductBO>) productRepository.findAll();
		ProductResponse productResponse = responseBuilder.buildProductsResponse(ResponseCodes.SUCCESS_OK, products);
		return productResponse;
	}

	public GenericResponse deleteProducts() {
		return new GenericResponse("Success");
	}

	private GenericResponse validateProductForCreate(Product product) {
		System.out.println("Validate product for create...");
		if (GenericAssister.isAnyStringEmpty(product.getRef(),product.getName())) {
			System.out.println("Reference and/or product's name are missing");
			return responseBuilder.buildResponse(ResponseCodes.MISSING_INPUT);
		}
		if(productRepository.existsById(product.getRef())) {
			return responseBuilder.buildResponse(ResponseCodes.PRODUCT_ALREADY_EXIST);
		}
		return null;
	}
	
	private GenericResponse validateProductForUpdate(Product product) {
		System.out.println("Validate product for update...");
		if (GenericAssister.isAnyStringEmpty(product.getRef(),product.getName())) {
			System.out.println("Reference and/or product's name are missing");
			return responseBuilder.buildResponse(ResponseCodes.MISSING_INPUT);
		}
		return null;
	}
	
	
	private ProductBO createProductBO(Product product) {
		ProductBO productBO = new ProductBO();
		productBO.setReference(product.getRef());
		productBO.setName(product.getName());
		productBO.setPrice(product.getPrice());
		productBO.setDescription(product.getDescription());
		return productBO;
	}

}
