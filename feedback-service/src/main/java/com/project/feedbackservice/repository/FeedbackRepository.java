package com.project.feedbackservice.repository;

import com.project.feedbackservice.model.Feedback;
import com.project.feedbackservice.model.QueryStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    Feedback findByProductId(String productId);
    Feedback findByCustomerEmailId(String customerEmailId);
    Feedback findByCreatedAt(LocalDate createdAt);
    Feedback findByQueryStatus( QueryStatus queryStatus);





}