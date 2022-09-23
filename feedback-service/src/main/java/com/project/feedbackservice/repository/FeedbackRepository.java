package com.project.feedbackservice.repository;

import com.project.feedbackservice.model.Feedback;
import com.project.feedbackservice.model.QueryStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    Feedback findByProductId(String productId);
    List<Feedback> findByCustomerEmailId(String customerEmailId);
    List<Feedback> findByCreatedAt(LocalDate createdAt);
    Feedback findByQueryStatus( QueryStatus queryStatus);





}