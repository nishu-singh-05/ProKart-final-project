package com.project.feedbackservice.controller;

import com.project.feedbackservice.model.Feedback;
import com.project.feedbackservice.service.FeedbackService;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.feedbackservice.model.QueryStatus.*;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc

class FeedbackControllerTest {

    @MockBean
    FeedbackService feedbackService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper=new ObjectMapper();



    @Test
    void addQuery() throws Exception {
        mapper.registerModule(new JavaTimeModule());

        Feedback feedback=new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1");

        String data = mapper.writeValueAsString(feedback);

        when(feedbackService.createQuery(feedback)).thenReturn(feedback);

        mockMvc.perform(post("/feedback/newRequest")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    void getAllQuery() throws Exception{
        List<Feedback> list = new ArrayList<>();
        list.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));
        list.add(new Feedback("3","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                PENDING,"Test query resolution2"));
        list.add(new Feedback("2","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution3"));
        list.add(new Feedback("4","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution3"));


        when(feedbackService.getListOfFeedback()).
                thenReturn(list.stream().collect(Collectors.toList()));

        mockMvc.perform(get("/feedback/searchAllQueries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(4)));

    }


    @Test
    void searchByProductId() throws Exception{
        List<Feedback> get = new ArrayList<>();
        get.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));
        get.add(new Feedback("3","test query description2","pro2",
                        "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                        PENDING,"Test query resolution2"));
        get.add(new Feedback("2","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution3"));



        when(feedbackService.getFeedbackByProductId("pro1")).
                thenReturn(get.stream().filter(el->el.getProductId()=="pro1").collect(Collectors.toList()));

        mockMvc.perform(get("/feedback/searchByProductId/pro1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    void searchByCustomerEmailId() throws Exception{

        List<Feedback> get = new ArrayList<>();
        get.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));
        get.add(new Feedback("3","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                PENDING,"Test query resolution2"));
        get.add(new Feedback("2","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution3"));



        when(feedbackService.getFeedbackByCustomerEmailId("meghadograa567@gmail.com")).
                thenReturn(get.stream().filter(el->el.getCustomerEmailId()=="meghadograa567@gmail.com").collect(Collectors.toList()));

        mockMvc.perform(get("/feedback/searchByCustomerEmailId/meghadograa567@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void searchByDate() throws Exception {
        List<Feedback> get = new ArrayList<>();
        get.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));
        get.add(new Feedback("3","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                PENDING,"Test query resolution2"));
        get.add(new Feedback("2","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution3"));



        when(feedbackService.getFeedbackByCreatedAt(LocalDate.of(2022,06,02))).
                thenReturn(get.stream().filter(el->el.getCreatedAt()==LocalDate.of(2022,06,02)).collect(Collectors.toList()));

        /*mockMvc.perform(get("/feedback/searchByDate/2022-06-02")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));*/
    }

    @Test
    void searchByQueryStatus() throws Exception{
        List<Feedback> get = new ArrayList<>();
        get.add(new Feedback("1","test query description1","pro1",
                "singhnishu567@gmail.com","501", LocalDate.of(2022,06,02),
                INPROCESS,"Test query resolution1"));
        get.add(new Feedback("3","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                PENDING,"Test query resolution2"));
        get.add(new Feedback("2","test query description2","pro2",
                "meghadograa567@gmail.com","502", LocalDate.of(2022,06,02),
                RESOLVED,"Test query resolution3"));



        when(feedbackService.getFeedbackByQueryStatus(RESOLVED)).
                thenReturn(get.stream().filter(el->el.getQueryStatus()==RESOLVED).collect(Collectors.toList()));

        mockMvc.perform(get("/feedback/searchByStatus/RESOLVED")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

}