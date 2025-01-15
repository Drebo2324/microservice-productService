package com.drebo.microservices.product.service;

import com.drebo.microservices.product.domain.dto.ProductDto;
import com.drebo.microservices.product.domain.dto.ProductListDto;
import com.drebo.microservices.product.domain.entity.Product;
import com.drebo.microservices.product.mapper.ProductMapper;
import com.drebo.microservices.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto createProduct(ProductDto productDto){
        log.info("Creating product: {}", productDto);
        Product productEntity = productMapper.mapFrom(productDto);
        Product savedProduct = productRepository.save(productEntity);
        log.info("Product created: {}", savedProduct);
        return productMapper.mapTo(savedProduct);
    }

    public ProductListDto getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> allProductsDto = allProducts.stream().map(productMapper::mapTo).toList();
        log.info("All products retrieved");
        return new ProductListDto(allProductsDto);
    }

    public void deleteAllProducts(){
        productRepository.deleteAll();
        log.info("Deleted all products");
    }

    public void deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            log.info("Product deleted: {}", id);
        } else {
            log.warn("Product not found with id: {}", id);
        }
    }
}
