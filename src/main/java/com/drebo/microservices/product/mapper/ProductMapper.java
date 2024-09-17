package com.drebo.microservices.product.mapper;

import com.drebo.microservices.product.domain.dto.ProductDto;
import com.drebo.microservices.product.domain.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    final private ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto mapTo(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
