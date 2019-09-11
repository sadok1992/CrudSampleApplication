package com.sadok.crud.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public @ResponseBody GenericResponse createProduct(@RequestBody Product product, HttpServletResponse response) {
		System.out.println(product);
		GenericResponse genericResponse = productService.addProduct(product);
		response.setStatus(genericResponse.getHttpStatus().value());
		return genericResponse;

	}

	@PutMapping(value = "/update")
	public @ResponseBody GenericResponse updateProduct(@RequestBody Product product, HttpServletResponse response) {
		GenericResponse genericResponse = productService.updateProduct(product);
		response.setStatus(genericResponse.getHttpStatus().value());
		return genericResponse;
	}

	@GetMapping(value = "/list")
	public @ResponseBody GenericResponse listProducts(HttpServletResponse response) {
		GenericResponse genericResponse = productService.listProducts();
		response.setStatus(genericResponse.getHttpStatus().value());
		return genericResponse;
	}

	@DeleteMapping(value = "/delete")
	public void deleteProduct() {
		System.out.println("Deleting product...");
	}

}
