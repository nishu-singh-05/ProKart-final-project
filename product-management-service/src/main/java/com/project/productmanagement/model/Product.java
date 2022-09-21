package com.project.productmanagement.model;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String productId ;
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productCategoryName;
    private String sellerId;
    private Long stock;
    List<String> productImages;



}
