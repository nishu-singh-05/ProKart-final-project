package com.project.usermodule.seller.repository;

import com.project.usermodule.seller.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, String> {
   Seller findBySellerName(String sellerName);
}
