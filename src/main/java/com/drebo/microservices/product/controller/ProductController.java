package com.drebo.microservices.product.controller;

import com.drebo.microservices.product.domain.dto.ProductDto;
import com.drebo.microservices.product.domain.dto.ProductListDto;
import com.drebo.microservices.product.domain.entity.Product;
import com.drebo.microservices.product.repository.ProductRepository;
import com.drebo.microservices.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController (ProductService productService, ProductRepository productRepository){
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        log.info("Creating Product: {}", productDto);
        return productService.createProduct(productDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductListDto getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id){
        log.info("Deleted Product id: {}", id);
        productService.deleteProduct(id);
    }
}
