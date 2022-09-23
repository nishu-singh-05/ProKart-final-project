package com.project.feedbackservice.controller;


import com.project.feedbackservice.FeedbackService;
import com.project.feedbackservice.exception.AlreadyExistsException;
import com.project.feedbackservice.exception.ResourceNotFoundException;
import com.project.feedbackservice.model.Feedback;
import com.project.feedbackservice.model.QueryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    //registering new query-customer

    @PostMapping("/newRequest")
    public ResponseEntity addQuery(@RequestBody Feedback feedback) {
        ResponseEntity responseEntity;
        try {

            responseEntity= new ResponseEntity<>(feedbackService.createQuery(feedback), HttpStatus.CREATED);
        }
        catch (AlreadyExistsException alreadyExistsException){
            responseEntity= new ResponseEntity("Register new query",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //search for queries-seller

    @GetMapping("searchAllQueries")
    public ResponseEntity<List> getAllQuery() {
        return new ResponseEntity<List>(feedbackService.getListOfFeedback(), HttpStatus.OK);
    }

    //search by queryID-seller/customer


    @GetMapping("searchByQueryId/{queryId}")
    public ResponseEntity searchByName(@PathVariable String queryId){
        ResponseEntity responseEntity;
        try {
           responseEntity=new ResponseEntity<>(feedbackService.getFeedbackByQueryId(queryId), HttpStatus.OK);

        }
        catch (ResourceNotFoundException resourceNotFoundException){
            responseEntity=new ResponseEntity<>("Query not found!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //search by productID-seller/customer

    @GetMapping("searchByProductId/{productId}")
    public ResponseEntity searchByProductId(@PathVariable String productId){
        return new ResponseEntity<>(feedbackService.getFeedbackByProductId(productId), HttpStatus.OK);
    }

    //search by customer email-ID-customer

    @GetMapping("searchByCustomerEmailId/{customerEmailId}")
    public ResponseEntity searchByCustomerEmailId(@PathVariable String customerEmailId){
        return new ResponseEntity<>(feedbackService.getFeedbackByCustomerEmailId(customerEmailId), HttpStatus.OK);
    }

    //search by created date-seller/customer

    @GetMapping("searchByDate/{createdAt}")
    public ResponseEntity searchByDate(@PathVariable ("createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt){
        return new ResponseEntity<>(feedbackService.getFeedbackByCreatedAt(createdAt), HttpStatus.OK);
    }

    //search by query status-seller/customer

    @GetMapping("searchByStatus/{queryStatus}")
    public ResponseEntity searchByQueryStatus(@PathVariable QueryStatus queryStatus){
        return new ResponseEntity<>(feedbackService.getFeedbackByQueryStatus(queryStatus), HttpStatus.OK);
    }

    //to update query-seller

    @PutMapping("/updateQuery")
    private ResponseEntity updateQuery(@RequestBody Feedback feedback) throws ResourceNotFoundException
    {
        ResponseEntity responseEntity;
        try {

            responseEntity= ResponseEntity.ok(feedbackService.updateFeedback(feedback));
        }
        catch (ResourceNotFoundException resourceNotFoundException){
            responseEntity= new ResponseEntity("Query not found with the id"+ feedback.getQueryId() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    //deleting query--seller/customer


    @DeleteMapping("/deleteQuery/{queryId}")
    public ResponseEntity deleteQuery(@PathVariable String queryId) {
        return ResponseEntity.ok(feedbackService.removeQuery(queryId)) ;
    }




}

