package com.project.feedbackservice.controller;


import com.project.feedbackservice.FeedbackService;
import com.project.feedbackservice.exception.AlreadyExistsException;
import com.project.feedbackservice.exception.ResourceNotFoundException;
import com.project.feedbackservice.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/newRequest")
    public ResponseEntity addQuery(@RequestBody Feedback feedback) {
        try {

            return new ResponseEntity<>(feedbackService.createQuery(feedback), HttpStatus.CREATED);
        }
        catch (AlreadyExistsException alreadyExistsException){
            return new ResponseEntity("Product register new query",HttpStatus.CONFLICT);
        }
    }

    @GetMapping("searchAllQueries")
    public ResponseEntity<List> getAllQuery() {
        return new ResponseEntity<List>(feedbackService.getListOfFeedback(), HttpStatus.OK);
    }


    @GetMapping("searchByQueryId/{queryId}")
    public ResponseEntity searchByName(@PathVariable String queryId){
        ResponseEntity responseEntity;
        try {
           responseEntity=new ResponseEntity<>(feedbackService.getFeedbackByQueryId(queryId), HttpStatus.FOUND);

        }
        catch (ResourceNotFoundException resourceNotFoundException){
            responseEntity=new ResponseEntity<>("Query not found!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("searchByProductId/{productId}")
    public ResponseEntity searchByProductId(@PathVariable String productId){
        return new ResponseEntity<>(feedbackService.getFeedbackByProductId(productId), HttpStatus.FOUND);
    }

    @GetMapping("searchByCustomerEmailId/{customerEmailId}")
    public ResponseEntity searchByCustomerEmailId(@PathVariable String customerEmailId){
        return new ResponseEntity<>(feedbackService.getFeedbackByCustomerEmailId(customerEmailId), HttpStatus.FOUND);
    }

    @GetMapping("searchByDate/{createdAt}")
    public ResponseEntity searchByDate(@PathVariable String customerEmailId){
        return new ResponseEntity<>(feedbackService.getFeedbackByCustomerEmailId(customerEmailId), HttpStatus.FOUND);
    }



}

  /*  @Autowired
    ProductService productService;





    @GetMapping("/customer/searchProductsByName/{productName}")
    public ResponseEntity searchByName(@PathVariable String productName){
        return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.FOUND);
    }

   @GetMapping("/searchProductsByCategory/{productCategoryName}")
    public ResponseEntity searchByCategory(@PathVariable String productCategoryName) {
       return new ResponseEntity<>(productService.getProductByCategoryName(productCategoryName), HttpStatus.FOUND);
   }

    @GetMapping("/searchProductsByPrice/{min}/{max}")
    public ResponseEntity searchByPrice(@PathVariable double min,@PathVariable double max){
        return new ResponseEntity<>(productService.filterByPrice( min, max), HttpStatus.FOUND);
    }


    @GetMapping("/searchProductByNameAndPriceAndCategory/{productName}/{productPrice}/{productCategoryName}")
    public ResponseEntity searchByNameCategoryPrice(@PathVariable String productName,@PathVariable double productPrice,@PathVariable String productCategoryName) {
        return new ResponseEntity<>(productService.getProductByNameAndPriceAndCategory(productName,productPrice,productCategoryName), HttpStatus.FOUND);
    }



    @PutMapping("/updateProduct/{productId}")
    private ResponseEntity update(@RequestBody Product product, @PathVariable String productId) throws ResourceNotFoundException
    {
        try {

            return ResponseEntity.ok(productService.updateProduct(product));
        }
        catch (ResourceNotFoundException resourceNotFoundException){
            return new ResponseEntity("Product not found with the id"+ productId ,HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity deleteCustomer(@PathVariable String productId) {
        return ResponseEntity.ok(productService.removeProduct(productId)) ;
    }




}*/
