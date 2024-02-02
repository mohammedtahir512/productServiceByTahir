package com.scaler.productservicebytahir.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @Id
    private Long id;
    private String name;


}
