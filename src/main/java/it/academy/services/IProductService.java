package it.academy.services;

import it.academy.dto.ProductDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IProductService {
    void createProduct(ProductDto productDto);

    void updateProduct(ProductDto productDto);

    ProductDto findProductById(Serializable id);

    void deleteProductById(Serializable id);

    Pageable<ProductDto> getPageableRecords(Pageable<ProductDto> pageable);

    List<ProductDto> findAllProducts();
}
