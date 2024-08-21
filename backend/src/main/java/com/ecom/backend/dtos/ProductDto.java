package com.ecom.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
        private Long id;
        private String title;
        private String description;
        private double price;
        private String image;
        private Long categoryId;
}
