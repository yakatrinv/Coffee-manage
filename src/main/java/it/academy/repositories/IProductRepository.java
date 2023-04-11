package it.academy.repositories;

import it.academy.models.Product;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;

public interface IProductRepository extends ICrudRepository<Product> {
    Pageable<Product> getProductsMachine(Serializable id, Pageable<Product> pageable);

    Long getProductsMachineCount(Serializable id, Pageable<Product> pageable);
}
