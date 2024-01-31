package com.scaler.productservicebytahir.models;

import com.fasterxml.jackson.core.JsonFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product  {
    private Long id;
    private String title;
    private double price;
    private  Category category;
    private String description;
    private String ImageUrl;
}
