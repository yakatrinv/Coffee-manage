package it.academy.services.impl;

import it.academy.dto.ProductDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.mappers.impl.ProductMapper;
import it.academy.models.Product;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IProductRepository;
import it.academy.repositories.impl.ProductRepository;
import it.academy.services.IProductService;

import java.io.Serializable;

public class ProductService implements IProductService {
    private final Mapper<Product, ProductDto> mapper = new ProductMapper();

    private final Mapper<Pageable<Product>, Pageable<ProductDto>> pageMapper =
            new PageableMapper<>(mapper);

    private final IProductRepository repository
            = new ProductRepository();

    @Override
    public ProductDto createProduct(ProductDto entityDto) {
        Product entity = mapper.dtoToEntity(entityDto);
        entity = repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public ProductDto updateProduct(ProductDto entityDto) {
        Product entity = mapper.dtoToEntity(entityDto);
        entity = repository.update(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public ProductDto findProductById(Serializable id) {
        Product entity = repository.getById(id);
        return mapper.entityToDto(entity);
    }

    @Override
    public void deleteProductById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<ProductDto> getPageableRecords(Pageable<ProductDto> pageableDto) {
        Pageable<Product> pageable = pageMapper.dtoToEntity(pageableDto);
        return pageMapper.entityToDto(repository.getPageableRecords(pageable));
    }
}
