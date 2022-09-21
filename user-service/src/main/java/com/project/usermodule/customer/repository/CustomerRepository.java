package com.project.usermodule.customer.repository;

import com.project.usermodule.customer.model.Customer;
import com.project.usermodule.seller.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByCustomerFirstName(String customerFirstName);
}
