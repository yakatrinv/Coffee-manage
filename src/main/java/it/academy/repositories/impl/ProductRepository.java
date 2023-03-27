package it.academy.repositories.impl;

import it.academy.models.Product;
import it.academy.repositories.IProductRepository;

import static it.academy.utils.Data.PRODUCT_CLASS;

public class ProductRepository extends CrudRepository<Product>
        implements IProductRepository {
    public ProductRepository() {
        super(PRODUCT_CLASS);
    }
}
