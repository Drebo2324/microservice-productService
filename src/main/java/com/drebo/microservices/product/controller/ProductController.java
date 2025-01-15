package com.drebo.microservices.product.controller;

import com.drebo.microservices.product.domain.dto.ProductDto;
import com.drebo.microservices.product.domain.dto.ProductListDto;
import com.drebo.microservices.product.repository.ProductRepository;
import com.drebo.microservices.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController (ProductService productService, ProductRepository productRepository){
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        log.info("Received new Product: {}", productDto);
        return productService.createProduct(productDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductListDto getAllProducts(){
        log.info("Retrieving all products");
        return productService.getAllProducts();
    }

    //TODO: FIX DELETE ENDPOINT
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") String id){
        log.info("Delete request-> product id: {}", id);
        productService.deleteProduct(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllProducts(){
        log.info("Deleting all products");
        productService.deleteAllProducts();
    }
}
