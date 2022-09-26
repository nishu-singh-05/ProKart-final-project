package com.project.productmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.productmanagement.model.Product;
import com.project.productmanagement.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;
    List<String> productImages=new ArrayList<>(Collections.singleton("hjhghtdrd"));

    Product product = new Product("pro3", "IPHONE", "phone",
            100000.0, "ELECTRONICS", "SE01", 5L,productImages);

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void addNewProduct() throws Exception {

        mapper.registerModule(new JavaTimeModule());

        Product product = new Product("pro3", "IPHONE", "phone",
                100000.0, "ELECTRONICS", "SE01", 5L,productImages);

        String data = mapper.writeValueAsString(product);

        when(productService.createNewProduct(product)).thenReturn(product);

        mockMvc.perform(post("/product/newProduct")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllProducts() throws Exception {
        List<Product> list = new ArrayList<>();
        list.add(new Product("pro2", "Lipstick", "DELL",
                350000.0, "Cosmetics", "SE01", 10L,productImages));

        list.add(new Product("pro3", "IPHONE", "phone",
                100000.0, "ELECTRONICS", "SE01", 5L,productImages));

        list.add(new Product("pro5", "Bat", "Games",
                5000.0, "SPORTS", "SE01", 100L,productImages));

        when(productService.getAllProducts()).
                thenReturn(list.stream().collect(Collectors.toList()));

        mockMvc.perform(get("/product/searchAllProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));

    }

    @Test
    void getProductId() throws Exception {

        when(productService.getProductById("pro3"))
                .thenReturn(product);
        mockMvc.perform(get("/product/searchByProductId/pro3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    void searchByName() throws Exception {
        List<Product> list1 = new ArrayList<>();
        list1.add(new Product("pro2", "Lipstick", "DELL",
                350000.0, "Cosmetics", "SE01", 10L,productImages));

        list1.add(new Product("pro3", "IPHONE", "phone",
                100000.0, "ELECTRONICS", "SE01", 5L,productImages));

        list1.add(new Product("pro5", "Bat", "Games",
                5000.0, "SPORTS", "SE01", 100L,productImages));


        when(productService.getProductByName("IPHONE")).
                thenReturn(list1.stream().filter(el -> el.getProductName() == "IPHONE").collect(Collectors.toList()));

        mockMvc.perform(get("/product/searchProductsByName/IPHONE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void searchByCategory() throws Exception {
        List<Product> list2 = new ArrayList<>();
        list2.add(new Product("pro2", "Lipstick", "DELL",
                350000.0, "Cosmetics", "SE01", 10L,productImages));

        list2.add(new Product("pro3", "IPHONE", "phone",
                100000.0, "ELECTRONICS", "SE01", 5L,productImages));

        list2.add(new Product("pro5", "Bat", "Games",
                5000.0, "SPORTS", "SE01", 100L,productImages));

        when(productService.getProductByCategoryName("SPORTS")).
                thenReturn(list2.stream().filter(el -> el.getProductCategoryName() == "SPORTS").collect(Collectors.toList()));

        mockMvc.perform(get("/product/searchProductsByCategory/SPORTS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));


    }


    @Test
    void searchByNameCategoryPrice() throws Exception {

        List<Product> list3 = new ArrayList<>();
        list3.add(new Product("pro2", "Lipstick", "DELL",
                350000.0, "Cosmetics", "SE01", 10L,productImages));

        list3.add(new Product("pro3", "SAMSUNG", "phone",
                10000.0, "ELECTRONICS", "SE01", 5L,productImages));

        list3.add(new Product("pro5", "Bat", "Games",
                5000.0, "SPORTS", "SE01", 100L,productImages));

        list3.add(new Product("pro7", "SAMSUNG", "phone",
                5000.0, "ELECTRONICS", "SE01", 50L,productImages));



        when(productService.getProductByNameAndPriceAndCategory("SAMSUNG",2000,50000,"ELECTRONICS"))
                .thenReturn(list3);
        mockMvc.perform(get("/product/searchProductByNameAndPriceAndCategory/SAMSUNG/2000/50000/ELECTRONICS")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(4)));


    }
}