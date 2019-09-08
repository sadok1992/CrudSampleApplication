package com.sadok.crud.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Entity
@Table(name = "PRODUCT")
@Data 
public class ProductBO {
	
	@Autowired
	@Id
	@Column(name = "Reference")
	private String reference;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Price")
	private Double price;

}
