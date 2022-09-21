package com.project.usermodule.seller.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "seller")

public class Seller {

    @Id
    private String sellerEmailId;
    private String sellerName;
    private String sellerDescription;
    private  Address address;

}
