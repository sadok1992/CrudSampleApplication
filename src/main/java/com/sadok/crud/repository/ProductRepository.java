package com.sadok.crud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sadok.crud.domain.ProductBO;

@Repository
public interface ProductRepository extends CrudRepository<ProductBO, String> {
}
