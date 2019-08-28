package com.sadok.crud.transport.request;

import lombok.Data;

@Data
public class Product {
	
	private String ref;
	private String name;
	private Double price;
	private String description;

}
