package it.academy.services;

import it.academy.dto.ProductDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto);

    ProductDto findProductById(Serializable id);

    void deleteProductById(Serializable id);

    Pageable<ProductDto> getPageableRecords(Pageable<ProductDto> pageable);
}
