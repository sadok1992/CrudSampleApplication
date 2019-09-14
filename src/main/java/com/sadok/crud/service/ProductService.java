package com.sadok.crud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.sadok.crud.domain.ProductBO;
import com.sadok.crud.repository.ProductRepository;
import com.sadok.crud.transport.request.Product;
import com.sadok.crud.transport.response.GenericResponse;
import com.sadok.crud.transport.response.ProductResponse;
import com.sadok.crud.util.GenericAssister;
import com.sadok.crud.util.LogAround;
import com.sadok.crud.util.ResponseBuilder;
import com.sadok.crud.util.ResponseCodes;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ResponseBuilder responseBuilder;

	@LogAround
	public GenericResponse addProduct(Product product) {

		GenericResponse genericResponse = validateProductForCreate(product);
		if (genericResponse != null) {
			return genericResponse;
		}
		productRepository.save(createProductBO(product));
		return responseBuilder.buildResponse(ResponseCodes.SUCCESS_CREATED);
	}

	@LogAround
	public GenericResponse updateProduct(Product product) {
		GenericResponse genericResponse = validateProductForUpdate(product);
		if (genericResponse != null) {
			return genericResponse;
		}
		Optional<ProductBO> optionalProductBO = productRepository.findById(product.getRef());
		if (optionalProductBO.isPresent()) {
			productRepository.save(createProductBO(product));
			genericResponse = responseBuilder.buildResponse(ResponseCodes.SUCCESS_OK);
		} else {
			genericResponse = responseBuilder.buildResponse(ResponseCodes.PRODUCT_NOT_EXIST);
		}
		return genericResponse;
	}

	@LogAround
	public GenericResponse listProducts() {
		List<ProductBO> products = (List<ProductBO>) productRepository.findAll();
		ProductResponse productResponse = responseBuilder.buildProductsResponse(ResponseCodes.SUCCESS_OK, products);
		return productResponse;
	}

	@LogAround
	public GenericResponse deleteProducts(String productReference) {
		GenericResponse genericResponse = valiateProductForDelete(productReference);
		if (genericResponse == null) {
			productRepository.deleteById(productReference);
			genericResponse = responseBuilder.buildResponse(ResponseCodes.SUCCESS_OK);
		}
		return genericResponse;
	}

	@LogAround
	public GenericResponse searchProducts(String filter) {
		String[] filterInputs;
		Map<String, String> filterMap = new HashMap<String, String>();
		try {
			filterInputs = filter.split("\\|");
			//System.out.println(filterInputs.as);
			for (String f : filterInputs) {
				String[] input = f.split("::");
				filterMap.put(input[0], input[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return responseBuilder.buildResponse(ResponseCodes.INVALID_INPUT);
		}

		List<ProductBO> products = retrieveProductsByFilter(filterMap);
		ProductResponse productResponse = responseBuilder.buildProductsResponse(ResponseCodes.SUCCESS_OK, products);
		
		return productResponse;

	}

	private GenericResponse validateProductForCreate(Product product) {
		if (GenericAssister.isAnyStringEmpty(product.getRef(), product.getName())) {
			return responseBuilder.buildResponse(ResponseCodes.MISSING_INPUT);
		}
		if (productRepository.existsById(product.getRef())) {
			return responseBuilder.buildResponse(ResponseCodes.PRODUCT_ALREADY_EXIST);
		}
		return null;
	}

	private GenericResponse validateProductForUpdate(Product product) {
		if (GenericAssister.isAnyStringEmpty(product.getRef(), product.getName())) {
			return responseBuilder.buildResponse(ResponseCodes.MISSING_INPUT);
		}
		return null;
	}

	private GenericResponse valiateProductForDelete(String ref) {
		if (GenericAssister.isStringEmpty(ref)) {
			return responseBuilder.buildResponse(ResponseCodes.MISSING_INPUT);
		}
		if (!productRepository.existsById(ref)) {
			return responseBuilder.buildResponse(ResponseCodes.PRODUCT_NOT_EXIST);
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

	private List<ProductBO> retrieveProductsByFilter(Map<String, String> filterMap) {
		List<ProductBO> products = productRepository.findAll(new Specification<ProductBO>() {


			@Override
			public Predicate toPredicate(Root<ProductBO> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				if (filterMap.containsKey("reference")) {
					predicates.add(criteriaBuilder.equal(root.get("reference"), filterMap.get("reference")));
				}
				
				if (filterMap.containsKey("name")) {
					predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")), filterMap.get("name").toLowerCase()));
				}
				
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
		return products;
	}

}
