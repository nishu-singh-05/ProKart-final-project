package com.project.feedbackservice.repository;

import com.project.feedbackservice.model.Feedback;
import com.project.feedbackservice.model.QueryStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.feedbackservice.model.QueryStatus.*;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class FeedbackRepositoryTest {

    @Autowired
    FeedbackRepository feedbackRepository;

    List<Feedback> query=new ArrayList<>();

    @BeforeEach
    void setUp() {
        feedbackRepository.deleteAll();
        query.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));

        query.add(new Feedback("2","test query description2","pro2",
                "singhnishu6@gmail.com","502", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution2"));

        query.add(new Feedback("3","test query description3","pro3",
                "singhnishu567@gmail.com","503", LocalDate.of(2022,06,02),
                PENDING,"Test query resolution3"));

        query.add(new Feedback("4","test query description3","pro3",
                "singhnishu567@gmail.com","503", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution3"));

        feedbackRepository.saveAll(query);
    }

    @AfterEach
    void tearDown() {
        feedbackRepository.deleteAll();
    }

    // Feedback findByProductId(String productId)

    @Test
    void findByProductId() {
        String productId=query.get(0).getProductId();
        System.out.println(query.get(0).getProductId());

        List<Feedback> feedback1=feedbackRepository.findByProductId(productId);

        assertEquals(1,feedback1.stream().collect(Collectors.toList()).size());
    }

    // List<Feedback> findByCustomerEmailId(String customerEmailId)

    @Test
    void findByCustomerEmailId() {
        String customerEmailId=query.get(0).getCustomerEmailId();
        System.out.println(query.get(0).getCustomerEmailId());

        List<Feedback> feedback2=feedbackRepository.findByCustomerEmailId(customerEmailId);


        assertEquals(3,feedback2.stream().collect(Collectors.toList()).size());
    }

    //List<Feedback> findByCreatedAt(LocalDate createdAt)

    @Test
    void findByCreatedAt() {
        LocalDate createdAt=query.get(0).getCreatedAt();
        System.out.println(query.get(0).getCreatedAt());

        List<Feedback> feedback3=feedbackRepository.findByCreatedAt(createdAt);


        assertEquals(4,feedback3.stream().collect(Collectors.toList()).size());
    }

    //Feedback findByQueryStatus( QueryStatus queryStatus)

    @Test
    void findByQueryStatus() {
        QueryStatus queryStatus=query.get(0).getQueryStatus();
        System.out.println(query.get(0).getQueryStatus());

        List<Feedback> feedback4=feedbackRepository.findByQueryStatus(queryStatus);

        assertEquals(2,feedback4.stream().collect(Collectors.toList()).size());
    }







}