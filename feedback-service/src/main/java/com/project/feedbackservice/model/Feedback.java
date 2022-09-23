package com.project.feedbackservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "feedback")
public class Feedback {

    @Id
    @Generated
    private String queryId ;
    private String queryDescription;
    private String productId;
    private String customerEmailId;
    private String sellerId;
    private LocalDate createdAt;
    private QueryStatus queryStatus;
    private String sellerResolution;



}
