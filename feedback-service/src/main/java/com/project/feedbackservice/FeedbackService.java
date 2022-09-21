package com.project.feedbackservice;


import com.project.feedbackservice.exception.AlreadyExistsException;
import com.project.feedbackservice.exception.ResourceNotFoundException;
import com.project.feedbackservice.model.Feedback;
import com.project.feedbackservice.model.QueryStatus;
import com.project.feedbackservice.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    //for customer
    public Feedback createQuery(Feedback feedback) {
        Feedback exist=feedbackRepository.findById(feedback.getQueryId()).orElse(null);
        if (exist == null) {

            return feedbackRepository.save(feedback);
        }
        else
            throw new AlreadyExistsException(
                    "Enter another query here!!");
    }

    public List<Feedback> getListOfFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackByQueryId(String queryId) {
        return (feedbackRepository.findById(queryId).get());
    }

    public Feedback getFeedbackByProductId(String productId) {
        return feedbackRepository.findByProductId(productId);
    }


    public Feedback getFeedbackByCustomerEmailId(String customerEmailId) {
        return feedbackRepository.findByCustomerEmailId(customerEmailId);
    }


    public Feedback getFeedbackByCreatedAt(LocalDate createdAt) {
        return feedbackRepository.findByCreatedAt(createdAt);
    }


    public Feedback getFeedbackByQueryStatus(QueryStatus queryStatus) {
        return feedbackRepository.findByQueryStatus(queryStatus);
    }

    //update for selller

    public Feedback updateProduct(Feedback feedback)
    {
        Feedback update=feedbackRepository.findById(feedback.getQueryId()).orElseThrow(() -> new ResourceNotFoundException("User not found "));
        update.setQueryStatus(feedback.getQueryStatus());
        update.setSellerResolution(feedback.getSellerResolution());

        return feedbackRepository.save(update);

    }

    //for customer

    public Boolean removeQuery(String queryId) {
        feedbackRepository.deleteById(queryId);
        if (feedbackRepository.findById(queryId).isPresent()) {
            return false;
        } else {
            return true;
        }
    }


}