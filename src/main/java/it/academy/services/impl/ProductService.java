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
import java.util.List;

public class ProductService implements IProductService {
    private final IProductRepository repository
            = new ProductRepository();

    private final Mapper<Product, ProductDto> mapper = new ProductMapper();

    private final Mapper<Pageable<Product>, Pageable<ProductDto>> mapperP =
            new PageableMapper<>(mapper);


    @Override
    public void createProduct(ProductDto productDto) {
        Product product = mapper.dtoToEntity(productDto);
        product = repository.save(product);
        mapper.entityToDto(product);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Product product = mapper.dtoToEntity(productDto);
        product = repository.update(product);
        mapper.entityToDto(product);
    }

    @Override
    public ProductDto findProductById(Serializable id) {
        Product product = repository.getById(id);
        return mapper.entityToDto(product);
    }

    @Override
    public void deleteProductById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = repository.getAll();
        return products
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public Pageable<ProductDto> getProductsMachine(Serializable id, Pageable<ProductDto> pageableDto) {
        Pageable<Product> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getProductsMachine(id, pageable));
    }

    @Override
    public Pageable<ProductDto> getPageableRecords(Pageable<ProductDto> pageableDto) {
        Pageable<Product> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }
}
