package com.ecom.backend.services;

import com.ecom.backend.dtos.ProductDto;
import com.ecom.backend.exceptions.ProductNotFoundException;
import com.ecom.backend.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(Long id) throws ProductNotFoundException;

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Long id, ProductDto product);

    ProductDto replaceProduct(Long id, ProductDto product);

    ProductDto createProduct(ProductDto product);

    void deleteProduct(Long id);
}
