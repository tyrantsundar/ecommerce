package com.ecom.backend.services.impl;

import com.ecom.backend.dtos.ProductDto;
import com.ecom.backend.exceptions.ProductNotFoundException;
import com.ecom.backend.models.Category;
import com.ecom.backend.models.Product;
import com.ecom.backend.repositories.CategoryRepository;
import com.ecom.backend.repositories.ProductRepository;
import com.ecom.backend.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    private List<ProductDto> convertToDto(List<Product> productList){
        return productList.stream()
                .map(product -> convertToDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isEmpty()){
            throw new ProductNotFoundException(id,"NOT FOUND");
        }
        Product getProduct = optional.get();
        return convertToDto(getProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return convertToDto(productList);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto product) {
        return null;
    }

    @Override
    public ProductDto replaceProduct(Long id, ProductDto product) {
        return null;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product productToBeCreated = convertToEntity(productDto);
        Category category = productToBeCreated.getCategory();
        if(category.getId() == null){
            Category savedCategory = categoryRepository.save(category);
            productToBeCreated.setCategory(savedCategory);
        }
        Product savedProduct = productRepository.save(productToBeCreated);
        return convertToDto(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
