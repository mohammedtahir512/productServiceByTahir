package com.scaler.productservicebytahir.services;

import com.scaler.productservicebytahir.DTOs.FakeStoreProductDto;
import com.scaler.productservicebytahir.exceptions.ProductNotFoundException;
import com.scaler.productservicebytahir.models.Category;
import com.scaler.productservicebytahir.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    //private RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;

    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct) {
        Product product = new Product();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        Category category = new Category();
        category.setName(fakeStoreProduct.getCategory());
        product.setCategory(category);
        return product;
    }
    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());

        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());


        return fakeStoreProductDto;
    }
    private FakeStoreProductDto getFakeStoreProductDto(Product product){
        FakeStoreProductDto fakeStoreProductDTO = new FakeStoreProductDto() ;

        fakeStoreProductDTO.setTitle(product.getTitle()) ;
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        if(product.getCategory()!=null)fakeStoreProductDTO.setCategory( product.getCategory().getName() );
        fakeStoreProductDTO.setImage(product.getImageUrl());

        return fakeStoreProductDTO ;
    }



    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {

        FakeStoreProductDto productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        if (productDto == null) {
            throw new ProductNotFoundException("Product not found with id" + id);
        }
        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] respone = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> responseAsList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : respone) {
            responseAsList.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }
        return responseAsList;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto productDTO = convertProductToFakeStoreProductDto(product);
        FakeStoreProductDto responseDTO = restTemplate.postForObject("https://fakestoreapi.com/products", productDTO, FakeStoreProductDto.class);
        //if(responseDTO==null) throw new Exception("could not create product");
        return convertFakeStoreProductToProduct(responseDTO);

    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        HttpEntity<FakeStoreProductDto> request = new HttpEntity<>(convertProductToFakeStoreProductDto(product));
        ResponseEntity<FakeStoreProductDto> resp = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id, HttpMethod.PUT, request,
                FakeStoreProductDto.class
        );
        return convertFakeStoreProductToProduct(resp.getBody());
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(convertProductToFakeStoreProductDto(product), FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto responseDto =  restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductToProduct(responseDto);
    }



    @Override
    public boolean deleteProduct(Long id) throws ProductNotFoundException{
        FakeStoreProductDto responseDto = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null, FakeStoreProductDto.class).getBody();
        if (responseDto != null){
            System.out.println("Product deleted");
            return true;
        }

        else throw new ProductNotFoundException("Product with id does not exist");

    }
    }


    





