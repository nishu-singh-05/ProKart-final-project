package com.project.usermodule.customer.service;

import com.project.usermodule.customer.model.Customer;
import com.project.usermodule.customer.repository.CustomerRepository;
import com.project.usermodule.exception.CustomerAlreadyExistsException;
import com.project.usermodule.exception.ResourceNotFoundException;
import com.project.usermodule.kafka.UserDto;
import com.project.usermodule.kafka.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private UserProducer userProducer;

    public Customer createCustomer(Customer customer, String password) {
        Customer existingCustomer=customerRepository.findById(customer.getCustomerEmailId()).orElse(null);
        if (existingCustomer == null) {
            UserDto event = new UserDto();
            event.setUserName(customer.getCustomerEmailId());
            event.setPassword(password);
            event.setRole("Customer");

            userProducer.sendMessage(event);

            return customerRepository.save(customer);
        }
        else
            throw new CustomerAlreadyExistsException(
                    "Customer already exists!!");
    }


    public Customer getCustomer(String customerEmailId) {
        return customerRepository.findById(customerEmailId).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" +customerEmailId));
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer findByCustomerName(String customerFirstName) {
        return customerRepository.findByCustomerFirstName(customerFirstName);
    }

    public Customer updateCustomer(Customer customer)
    {
        Customer customerObject=customerRepository.findById(customer.getCustomerEmailId()).orElseThrow(() -> new ResourceNotFoundException("User not found "));
        customerObject.setCustomerFirstName(customer.getCustomerFirstName());
        customerObject.setCustomerLastName(customer.getCustomerLastName());
        customerObject.setCustomerDOB(customer.getCustomerDOB());
        customerObject.setGender(customer.getGender());
        customerObject.setCustomerMobileNumber(customer.getCustomerMobileNumber());
        customerObject.setAddress(customer.getAddress());

        return customerRepository.save(customerObject);

    }

    public Boolean removeCustomer(String customerEmailId) {
        Customer check=customerRepository.findById(customerEmailId).orElseThrow(() -> new ResourceNotFoundException("User not found "));
        customerRepository.deleteById(customerEmailId);
        if(customerRepository.findById(customerEmailId).isPresent()) {
            return false;
        }
        else{
            return true;
        }
    }



}
