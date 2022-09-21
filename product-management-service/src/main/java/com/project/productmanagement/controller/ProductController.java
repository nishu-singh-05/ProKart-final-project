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
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/seller/newCategory")
    public ResponseEntity addNewProduct(@RequestBody Product product) {
        try {

            return new ResponseEntity<>(productService.createNewProduct(product), HttpStatus.CREATED);
        }
        catch (AlreadyExistsException alreadyExistsException){
            return new ResponseEntity("Product Already Exist with the same name,Please use another name Or delete previous product",HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/customer/searchAllProducts")
    public ResponseEntity<List> getAllProducts() {
        return new ResponseEntity<List>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("searchByProductId/{ productId}")
    public ResponseEntity getProductId(@PathVariable String productId) {
        return new ResponseEntity(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping("/customer/searchProductsByName/{productName}")
    public ResponseEntity searchByName(@PathVariable String productName){
        return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.FOUND);
    }

   @GetMapping("/searchProductsByCategory/{productCategoryName}")
    public ResponseEntity searchByCategory(@PathVariable String productCategoryName) {
       return new ResponseEntity<>(productService.getProductByCategoryName(productCategoryName), HttpStatus.FOUND);
   }

    @GetMapping("/searchProductsByPrice/{min}/{max}")
    public ResponseEntity searchByPrice(@PathVariable double min,@PathVariable double max){
        return new ResponseEntity<>(productService.filterByPrice( min, max), HttpStatus.FOUND);
    }


    @GetMapping("/searchProductByNameAndPriceAndCategory/{productName}/{productPrice}/{productCategoryName}")
    public ResponseEntity searchByNameCategoryPrice(@PathVariable String productName,@PathVariable double productPrice,@PathVariable String productCategoryName) {
        return new ResponseEntity<>(productService.getProductByNameAndPriceAndCategory(productName,productPrice,productCategoryName), HttpStatus.FOUND);
    }



    @PutMapping("/updateProduct/{productId}")
    private ResponseEntity update(@RequestBody Product product, @PathVariable String productId) throws ResourceNotFoundException
    {
        try {

            return ResponseEntity.ok(productService.updateProduct(product));
        }
        catch (ResourceNotFoundException resourceNotFoundException){
            return new ResponseEntity("Product not found with the id"+ productId ,HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity deleteCustomer(@PathVariable String productId) {
        return ResponseEntity.ok(productService.removeProduct(productId)) ;
    }




}
