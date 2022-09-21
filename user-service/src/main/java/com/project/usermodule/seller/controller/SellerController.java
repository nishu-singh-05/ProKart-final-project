package com.project.usermodule.seller.controller;


import com.project.usermodule.exception.CustomerAlreadyExistsException;
import com.project.usermodule.exception.ResourceNotFoundException;
import com.project.usermodule.seller.model.Seller;
import com.project.usermodule.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/newSeller/{password}")
    public ResponseEntity  addNewSeller(@RequestBody Seller seller, @PathVariable String password) {
        return new ResponseEntity(sellerService.createSeller(seller, password),HttpStatus.CREATED);

    }

    @GetMapping("/searchAllSeller")
    public ResponseEntity<List> getSellers() {
        return new ResponseEntity<List>(sellerService.getAllSeller(), HttpStatus.OK);
    }

    @GetMapping("/searchSellerByID/{sellerEmailId}")
        public ResponseEntity getSellerById(@PathVariable String sellerEmailId) throws ResourceNotFoundException {
            return new ResponseEntity(sellerService.getSeller(sellerEmailId), HttpStatus.OK);
    }

    @GetMapping("/searchSellerByName/{sellerName}")
    public ResponseEntity searchByName(@PathVariable String sellerName){
       return new ResponseEntity<>(sellerService.findBySellerName(sellerName), HttpStatus.FOUND);
    }

    @PutMapping("/updateSeller/{sellerEmailId}")
    private ResponseEntity update(@RequestBody Seller seller, @PathVariable String sellerEmailId) throws ResourceNotFoundException
    {
        return ResponseEntity.ok(sellerService.updateSeller(seller));

    }

    @DeleteMapping("/deleteSeller/{sellerEmailId}")
    public ResponseEntity deleteSeller(@PathVariable String sellerEmailId) {
        return ResponseEntity.ok(sellerService.removeSeller(sellerEmailId)) ;
    }


}
