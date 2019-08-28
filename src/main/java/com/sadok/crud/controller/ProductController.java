package com.sadok.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sadok.crud.service.ProductService;
import com.sadok.crud.transport.request.Product;
import com.sadok.crud.transport.response.GenericResponse;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/save")
	public @ResponseBody GenericResponse createProduct() {
		System.out.println("creating product...");
		return productService.addProduct(new Product());
	}
	
	@PutMapping(value = "/update")
	public void updateProduct() {
		System.out.println("Updating product...");
	}
	
	@GetMapping(value = "/list")
	public void listProducts() {
		System.out.println("Listing product...");
	}
	
	@DeleteMapping(value = "/delete")
	public void deleteProduct() {
		System.out.println("Deleting product...");
	}
	

}
