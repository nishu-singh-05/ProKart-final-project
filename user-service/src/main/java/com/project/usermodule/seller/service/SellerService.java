package com.project.usermodule.seller.service;

import com.project.usermodule.exception.CustomerAlreadyExistsException;
import com.project.usermodule.exception.ResourceNotFoundException;
import com.project.usermodule.kafka.UserDto;
import com.project.usermodule.kafka.UserProducer;
import com.project.usermodule.seller.model.Seller;
import com.project.usermodule.seller.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    private UserProducer userProducer;

    public Seller createSeller(Seller seller, String password) {
        Seller existingSeller=sellerRepository.findById(seller.getSellerEmailId()).orElse(null);
        if (existingSeller == null) {
            UserDto event = new UserDto();
            event.setUserName(seller.getSellerEmailId());
            event.setPassword(password);
            event.setRole("Seller");

            userProducer.sendMessage(event);
            return sellerRepository.save(seller);
        }
        else
            throw new CustomerAlreadyExistsException(
                    "Seller already exists!!");
    }


    public Seller getSeller(String sellerEmailId) {
        return sellerRepository.findById(sellerEmailId).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + sellerEmailId));

    }

    public List<Seller> getAllSeller() {
        return sellerRepository.findAll();
    }

    public Seller findBySellerName(String sellerName) {
        return sellerRepository.findBySellerName(sellerName);
    }

    public Seller updateSeller(Seller seller)
    {
        Seller sellerobject=sellerRepository.findById(seller.getSellerEmailId()).orElseThrow(() -> new ResourceNotFoundException("User not found "));
        sellerobject.setSellerName(seller.getSellerName());
        sellerobject.setSellerDescription(seller.getSellerDescription());
        sellerobject.setAddress(seller.getAddress());

        return sellerRepository.save(sellerobject);

    }

    public Boolean removeSeller(String sellerEmailId) {
        Seller check=sellerRepository.findById(sellerEmailId).orElseThrow(() -> new ResourceNotFoundException("User not found "));
        sellerRepository.deleteById(sellerEmailId);
        if(sellerRepository.findById(sellerEmailId).isPresent()) {
            return false;
        }
        else{
            return true;
        }
    }



}
