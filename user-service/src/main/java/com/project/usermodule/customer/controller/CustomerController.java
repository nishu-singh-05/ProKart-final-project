package com.project.usermodule.customer.controller;

import com.project.usermodule.customer.model.Customer;
import com.project.usermodule.customer.service.CustomerService;
import com.project.usermodule.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //registering new customer

    @PostMapping("/newCustomer/{password}")
    public ResponseEntity addNewCustomer(@RequestBody Customer customer, @PathVariable String password) {

        return new ResponseEntity<>(customerService.createCustomer(customer,password), HttpStatus.CREATED);
    }

    //list of customers

    @GetMapping("/searchAllCustomer")
    public ResponseEntity<List> getCustomer() {
        return new ResponseEntity<List>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    //search by customer email-id

    @GetMapping("/searchCustomerByID/{customerEmailId}")
    public ResponseEntity getCustomerById(@PathVariable String customerEmailId) throws ResourceNotFoundException {
        return new ResponseEntity(customerService.getCustomer(customerEmailId), HttpStatus.OK);
    }

    //search by customer firstname

    @GetMapping("/searchCustomerByName/{customerFirstName}")
    public ResponseEntity searchByName(@PathVariable String customerFirstName){
        return new ResponseEntity<>(customerService.findByCustomerName(customerFirstName), HttpStatus.OK);
    }

    // To update Customer

    @PutMapping("/updateCustomer")
    private ResponseEntity update(@RequestBody Customer customer) throws ResourceNotFoundException
    {
        return ResponseEntity.ok(customerService.updateCustomer(customer));

    }

    // To delete Customer

    @DeleteMapping("/deleteCustomer/{customerEmailId}")
    public ResponseEntity deleteCustomer(@PathVariable String customerEmailId) {
        return ResponseEntity.ok(customerService.removeCustomer(customerEmailId)) ;
    }


}
