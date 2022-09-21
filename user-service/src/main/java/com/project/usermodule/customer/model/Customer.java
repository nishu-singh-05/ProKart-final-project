package com.project.usermodule.customer.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private String customerEmailId;
    private String customerFirstName;
    private String customerLastName;
    private String gender;
    private LocalDate customerDOB;
    private int customerMobileNumber;
    private LocalDate createdAt;
    private Address address;


}
