package com.project.productmanagement.controller;

import com.project.productmanagement.exception.AlreadyExistsException;
import com.project.productmanagement.exception.ResourceNotFoundException;
import com.project.productmanagement.model.Product;
import com.project.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //to add new product
    @PostMapping("/newProduct")
    public ResponseEntity addNewProduct(@RequestBody Product product) {
        ResponseEntity responseEntity;
        try {

            responseEntity= new ResponseEntity<>(productService.createNewProduct(product), HttpStatus.CREATED);
        }
        catch (AlreadyExistsException alreadyExistsException){
            responseEntity= new ResponseEntity("Product Already Exist with the same name,Please use another name Or delete previous product",HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //list of products

    @GetMapping("/searchAllProducts")
    public ResponseEntity<List> getAllProducts() {
        ResponseEntity responseEntity;
        try {
            responseEntity= new ResponseEntity<List>(productService.getAllProducts(), HttpStatus.OK);
        }
        catch (ResourceNotFoundException resourceNotFoundException) {
            responseEntity = new ResponseEntity("Product not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //serach product by productID

    @GetMapping("/searchByProductId/{productId}")
    public ResponseEntity getProductId(@PathVariable String productId) {
        ResponseEntity responseEntity;
        try {
            responseEntity=new ResponseEntity(productService.getProductById(productId), HttpStatus.OK);
        }
        catch (ResourceNotFoundException resourceNotFoundException) {
            responseEntity = new ResponseEntity("Product not found with id "+ productId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //serach product by product name

    @GetMapping("/searchProductsByName/{productName}")
    public ResponseEntity searchByName(@PathVariable String productName){
        ResponseEntity responseEntity;
        try {
            responseEntity=new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.OK);
        }
        catch (ResourceNotFoundException resourceNotFoundException) {
            responseEntity = new ResponseEntity("Product not found with name "+ productName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //serach product by product category name

   @GetMapping("/searchProductsByCategory/{productCategoryName}")
    public ResponseEntity searchByCategory(@PathVariable String productCategoryName) {
       ResponseEntity responseEntity;
       try {
           responseEntity=new ResponseEntity<>(productService.getProductByCategoryName(productCategoryName), HttpStatus.OK);
       }
       catch (ResourceNotFoundException resourceNotFoundException) {
           responseEntity = new ResponseEntity("Product not found with category name "+ productCategoryName, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return responseEntity;

   }

   //search product between range(min,max)

    @GetMapping("/searchProductsByPrice/{min}/{max}")
    public ResponseEntity searchByPrice(@PathVariable double min,@PathVariable double max){
        ResponseEntity responseEntity;
        try {
            responseEntity=new ResponseEntity<>(productService.filterByPrice( min, max), HttpStatus.OK);
        }
        catch (ResourceNotFoundException resourceNotFoundException) {
            responseEntity = new ResponseEntity("Enter valid range!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    //filter product by product name,product price and category

    @GetMapping("/searchProductByNameAndPriceAndCategory/{productName}/{min}/{max}/{productCategoryName}")
    public ResponseEntity searchByNameCategoryPrice(@PathVariable String productName,@PathVariable double min,@PathVariable double max,@PathVariable String productCategoryName) {
        return new ResponseEntity<>(productService.getProductByNameAndPriceAndCategory(productName,min,max,productCategoryName), HttpStatus.OK);
    }

    //to update product

    @PutMapping("/updateProduct")
    private ResponseEntity update(@RequestBody Product product) throws ResourceNotFoundException
    {
        ResponseEntity responseEntity;
        try {

            responseEntity= ResponseEntity.ok(productService.updateProduct(product));
        }
        catch (ResourceNotFoundException resourceNotFoundException){
            responseEntity= new ResponseEntity("Product not found with the id "+product.getProductId() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    //to delete product

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity deleteCustomer(@PathVariable String productId) {
        return ResponseEntity.ok(productService.removeProduct(productId)) ;
    }




}
