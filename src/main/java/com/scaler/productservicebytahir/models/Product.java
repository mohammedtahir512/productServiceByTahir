package com.scaler.productservicebytahir.models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    @Id
    private Long id;
    private String title;
    private double price;
    @ManyToOne
    private  Category category;
    private String description;
    private String ImageUrl;
}
