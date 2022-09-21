package com.project.productmanagement.repository;

import com.project.productmanagement.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByProductName(String productName);
    Product findByProductCategoryName(String productCategoryName);
    Product findByProductNameAndProductPriceAndProductCategoryName(String productName,double productPrice,String productCategoryName);


}