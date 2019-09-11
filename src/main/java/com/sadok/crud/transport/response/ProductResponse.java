package com.sadok.crud.transport.response;

import java.util.List;

import com.sadok.crud.domain.ProductBO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse extends GenericResponse {
	
	public List<ProductBO> products;

}
