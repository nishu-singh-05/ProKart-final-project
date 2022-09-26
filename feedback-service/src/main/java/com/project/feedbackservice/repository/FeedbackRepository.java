package com.project.feedbackservice.repository;

import com.project.feedbackservice.model.Feedback;
import com.project.feedbackservice.model.QueryStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    @Override
    Optional<Feedback> findById(String s);

    List<Feedback> findByProductId(String productId);
    List<Feedback> findByCustomerEmailId(String customerEmailId);
    List<Feedback> findByCreatedAt(LocalDate createdAt);
    List<Feedback> findByQueryStatus( QueryStatus queryStatus);





}