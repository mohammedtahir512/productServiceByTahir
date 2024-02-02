package com.scaler.productservicebytahir.services;

import com.scaler.productservicebytahir.exceptions.ProductNotFoundException;
import com.scaler.productservicebytahir.models.Product;
import com.scaler.productservicebytahir.repositories.CategoryRepository;
import com.scaler.productservicebytahir.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private  CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) throws ProductNotFoundException {
        return false;
    }
}
