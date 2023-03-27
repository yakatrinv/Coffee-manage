package it.academy.mappers.impl;

import it.academy.dto.ProductDto;
import it.academy.mappers.Mapper;
import it.academy.models.Product;

public class ProductMapper implements Mapper<Product, ProductDto> {
    @Override
    public Product dtoToEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
    }

    @Override
    public ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
