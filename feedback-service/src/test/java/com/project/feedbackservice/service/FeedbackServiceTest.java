package com.project.feedbackservice.service;

import com.project.feedbackservice.model.Feedback;
import com.project.feedbackservice.repository.FeedbackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.project.feedbackservice.model.QueryStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc

class FeedbackServiceTest {

    @MockBean
    FeedbackRepository feedbackRepository;

    Feedback feedback=new Feedback("1","test query description1","pro1",
            "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
            INPROCESS,"Test query resolution1");


    @Test
    void createQuery() {
        Feedback feedback=new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1");

        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        assertEquals(feedback,feedbackRepository.save(feedback));

    }

    @Test
    void getListOfFeedback() {
        List<Feedback> feedback1= new ArrayList<>();
        feedback1.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));

        feedback1.add(new Feedback("2","test query description2","pro2",
                "singhnishu6@gmail.com","502", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution2"));

        assertEquals(2,feedback1.stream().collect(Collectors.toList()).size());



    }

    @Test
    void getFeedbackByQueryId() {

        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        assertEquals(feedback,feedbackRepository.save(feedback));

    }

    @Test
    void getFeedbackByProductId() {
        List<Feedback> feedback1= new ArrayList<>();
        feedback1.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));

        feedback1.add(new Feedback("2","test query description2","pro2",
                "singhnishu6@gmail.com","502", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution2"));
        when(feedbackRepository.findByProductId("pro1")).thenReturn(Optional.of(feedback1).get());

        assertEquals(Optional.of(feedback1).get(),feedbackRepository.findByProductId("pro1"));
    }

    @Test
    void getFeedbackByCustomerEmailId() {
        List<Feedback> feedback2= new ArrayList<>();
        feedback2.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));

        feedback2.add(new Feedback("2","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution2"));

        when(feedbackRepository.findByCustomerEmailId("meghadograa567@gmail.com")).thenReturn(Optional.of(feedback2).get());

        assertEquals(Optional.of(feedback2).get(),feedbackRepository.findByCustomerEmailId("meghadograa567@gmail.com"));
    }

    @Test
    void getFeedbackByCreatedAt() {

        List<Feedback> feedback3= new ArrayList<>();
        feedback3.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2020,11,05),
                INPROCESS,"Test query resolution1"));

        feedback3.add(new Feedback("2","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution2"));

        when(feedbackRepository.findByCreatedAt(LocalDate.of(2022,06,02))).thenReturn(Optional.of(feedback3).get());

        assertEquals(Optional.of(feedback3).get(),feedbackRepository.findByCreatedAt(LocalDate.of(2022,06,02)));
    }

    @Test
    void getFeedbackByQueryStatus() {
        List<Feedback> feedback4= new ArrayList<>();
        feedback4.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2020,11,05),
                INPROCESS,"Test query resolution1"));

        feedback4.add(new Feedback("2","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution2"));

        when(feedbackRepository.findByQueryStatus(RESOLVED)).thenReturn(Optional.of(feedback4).get());

        assertEquals(Optional.of(feedback4).get(),feedbackRepository.findByQueryStatus(RESOLVED));
    }

}