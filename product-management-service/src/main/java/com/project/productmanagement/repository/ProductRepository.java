package com.project.productmanagement.repository;

import com.project.productmanagement.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByProductName(String productName);
    List<Product> findByProductCategoryName(String productCategoryName);
    List<Product> findByProductNameAndProductCategoryName(String productName,String productCategoryName);


}