package com.drebo.microservices.product.controller;

import com.drebo.microservices.product.domain.dto.ProductDto;
import com.drebo.microservices.product.domain.dto.ProductListDto;
import com.drebo.microservices.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductListDto getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllProducts(){
        productService.deleteAllProducts();
    }
}
