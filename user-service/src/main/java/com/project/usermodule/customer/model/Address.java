package com.project.usermodule.customer.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    
    private String streetno;
    private String city;
    private String state;
    private String country;
    private Integer zip;
}
