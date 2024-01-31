package com.scaler.productservicebytahir.services;

import com.scaler.productservicebytahir.exceptions.ProductNotFoundException;
import com.scaler.productservicebytahir.models.Product;

import java.util.List;

public interface ProductService {


    Product getSingleProduct(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();
    Product addNewProduct(Product product);


    Product updateProduct(Long id, Product product) throws ProductNotFoundException ;


   // Product updateProduct(long id, Product product);

    Product replaceProduct(Long id, Product product)throws ProductNotFoundException ;

    boolean deleteProduct(Long id) throws ProductNotFoundException;
}
