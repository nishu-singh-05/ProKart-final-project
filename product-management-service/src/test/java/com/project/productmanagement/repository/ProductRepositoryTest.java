package com.project.productmanagement.repository;

import com.project.productmanagement.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    List<Product> products=new ArrayList<>();
    List<String> productImages=new ArrayList<>(Collections.singleton("hjhghtdrd"));

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        products.add(new Product("pro1","Lipstick","M.A.C",
                3000.0,"Cosmetics","SE01",10L,productImages));

        products.add(new Product("pro2","Lipstick","DELL",
                350000.0,"Cosmetics","SE01",10L,productImages));

        products.add(new Product("pro3","IPHONE","phone",
                100000.0,"ELECTRONICS","SE01",5L,productImages));

        products.add(new Product("pro4","Ball","Games",
                300.0,"SPORTS","SE01",20L,productImages));

        products.add(new Product("pro5","Bat","Games",
                5000.0,"SPORTS","SE01",100L,productImages));

        productRepository.saveAll(products);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

  @Test
    void findByProductName() {
      String productName = products.get(0).getProductName();
      System.out.println(products.get(0).getProductName());

      List<Product> products1 = productRepository.findByProductName(productName);

      assertEquals(2, products1.stream().collect(Collectors.toList()).size());
  }


    @Test
    void findByProductCategoryName() {

        String productCategoryName=products.get(0).getProductCategoryName();
        System.out.println(products.get(0).getProductCategoryName());

        List<Product> products1=productRepository.findByProductCategoryName(productCategoryName);

        assertEquals(2,products1.stream().collect(Collectors.toList()).size());

    }


    @Test
    void findByProductNameAndProductCategoryName() {
        String productName = products.get(0).getProductName();
        String productCategoryName=products.get(0).getProductCategoryName();


        long expectedCount=products.stream().filter(el->el.getProductName().equals(productName) && el.getProductCategoryName().equals(productCategoryName)).count();

        System.out.println(expectedCount);
        assertEquals(expectedCount,productRepository.findByProductNameAndProductCategoryName(productName,productCategoryName)
                .stream().collect(Collectors.toList()).size());

    }
 }


