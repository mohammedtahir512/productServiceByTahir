package com.scaler.productservicebytahir.controllers;

import com.scaler.productservicebytahir.exceptions.ProductNotFoundException;
import com.scaler.productservicebytahir.models.Product;
import com.scaler.productservicebytahir.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private RestTemplate restTemplate;
    public ProductController (@Qualifier("selfProductService")ProductService productService,RestTemplate restTemplate){
        this.restTemplate=restTemplate;
        this.productService=productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){

        ResponseEntity<List<Product>> response=new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);

        return response;
     }

     @GetMapping ("/{id}")
     public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getSingleProduct(id);
     }

     @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);

     }

     @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotFoundException{
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotFoundException{
        return productService.replaceProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id)throws ProductNotFoundException{
        return productService.deleteProduct(id);

    }





}
