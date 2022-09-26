package com.project.productmanagement.service;

import com.project.productmanagement.model.Product;
import com.project.productmanagement.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc

class ProductServiceTest {
    @MockBean
    ProductRepository productRepository;

    List<String> productImages=new ArrayList<>(Collections.singleton("hjhghtdrd"));

    Product product=new Product("pro3","IPHONE","phone",
            100000.0,"ELECTRONICS","SE01",5L,productImages);


    @Test
    void createNewProduct() {

        when(productRepository.save(product)).thenReturn(product);

        assertEquals(product,productRepository.save(product));
    }

    @Test
    void getAllProducts() {
        List<Product> products2=new ArrayList<>();
        products2.add(new Product("pro2","Lipstick","DELL",
                350000.0,"Cosmetics","SE01",10L,productImages));

        products2.add(new Product("pro3","IPHONE","phone",
                100000.0,"ELECTRONICS","SE01",5L,productImages));

        products2.add(new Product("pro5","Bat","Games",
                5000.0,"SPORTS","SE01",100L,productImages));

        assertEquals(3,products2.stream().collect(Collectors.toList()).size());



    }

    @Test
    void getProductById() {
        when(productRepository.findById("pro3")).thenReturn(Optional.of(product));

        assertEquals(Optional.of(product),productRepository.findById("pro3"));
    }

    @Test
    void getProductByName() {


        List<Product> products2=new ArrayList<>();
        products2.add(new Product("pro2","Lipstick","DELL",
                350000.0,"Cosmetics","SE01",10L,productImages));

        products2.add(new Product("pro3","IPHONE","phone",
                100000.0,"ELECTRONICS","SE01",5L,productImages));

        products2.add(new Product("pro5","Bat","Games",
                5000.0,"SPORTS","SE01",100L,productImages));

        when(productRepository.findByProductName("Lipstick")).thenReturn(Optional.of(products2).get());

        assertEquals(Optional.of(products2).get(),productRepository.findByProductName("Lipstick"));
    }

    @Test
    void getProductByCategoryName() {

        List<Product> products3=new ArrayList<>();
        products3.add(new Product("pro2","Lipstick","DELL",
                350000.0,"Cosmetics","SE01",10L,productImages));

        products3.add(new Product("pro3","IPHONE","phone",
                100000.0,"ELECTRONICS","SE01",5L,productImages));

        products3.add(new Product("pro5","Bat","Games",
                5000.0,"SPORTS","SE01",100L,productImages));

        when(productRepository.findByProductCategoryName("Cosmetics")).thenReturn(Optional.of(products3).get());

        assertEquals(Optional.of(products3).get(),productRepository.findByProductCategoryName("Cosmetics"));
    }

    @Test
    void getProductByNameAndPriceAndCategory() {
       List<Product> products4=new ArrayList<>();
        products4.add(new Product("pro1","Lipstick","M.A.C",
                3000.0,"Cosmetics","SE01",10L,productImages));

        products4.add(new Product("pro2","Lipstick","DELL",
                3000.0,"Cosmetics","SE01",10L,productImages));

        products4.add(new Product("pro3","IPHONE","phone",
                100000.0,"ELECTRONICS","SE01",5L,productImages));

        products4.add(new Product("pro5","Bat","Games",
                5000.0,"SPORTS","SE01",100L,productImages));

        when(productRepository.findByProductNameAndProductCategoryName("Lipstick","Cosmetics"))
                .thenReturn(Optional.of(products4).get());

        assertEquals(Optional.of(products4).get(),productRepository
                .findByProductNameAndProductCategoryName("Lipstick","Cosmetics"));

    }

}