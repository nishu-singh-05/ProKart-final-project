package com.project.productmanagement.service;


import com.project.productmanagement.exception.AlreadyExistsException;
import com.project.productmanagement.exception.ResourceNotFoundException;
import com.project.productmanagement.model.Product;
import com.project.productmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;



    public Product createNewProduct(Product product) {
        Product existingProduct=productRepository.findById(product.getProductId()).orElse(null);
        if (existingProduct == null) {

            return productRepository.save(product);
        }
        else
            throw new AlreadyExistsException(
                    "Product already exists!!");
    }




    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public Product getProductByCategoryName(String productCategoryName) {
        return productRepository.findByProductCategoryName(productCategoryName);
    }


    public Product getProductByNameAndPriceAndCategory(String productName,double productPrice,String productCategoryName) {
        return productRepository.findByProductNameAndProductPriceAndProductCategoryName(productName, productPrice, productCategoryName);
    }

        public List<Product> filterByPrice(double min,double max){

        List<Product> newlist= new ArrayList<>();

            List<Product> listOfProduct = productRepository.findAll();
            System.out.println(listOfProduct +" size: " +listOfProduct.size());

            for (Product productsVar : listOfProduct) {
                if(productsVar.getProductPrice()>= min && productsVar.getProductPrice()<= max){
                     newlist.add(productsVar);
                }
            }
            return newlist;
        }


    public Product updateProduct(Product product)
    {
        Product update=productRepository.findById(product.getProductId()).orElseThrow(() -> new ResourceNotFoundException("User not found "));
        update.setProductName(product.getProductName());
        update.setProductDescription(product.getProductDescription());
        update.setProductPrice(product.getProductPrice());
        update.setProductCategoryName(product.getProductCategoryName());
        update.setSellerId(product.getSellerId());
        update.setStock(product.getStock());
        update.setProductImages(product.getProductImages());

        return productRepository.save(update);

    }

    public Boolean removeProduct(String productId) {
        productRepository.deleteById(productId);
        if (productRepository.findById(productId).isPresent()) {
            return false;
        } else {
            return true;
        }
    }


}
