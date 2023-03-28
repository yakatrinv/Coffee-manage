package it.academy.repositories;

import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;

public interface IMachineRepository extends ICrudRepository<Machine> {
    Pageable<Product> getProducts(Serializable id, Pageable<Product> pageable);

    Long getProductsCount(Serializable id, Pageable<Product> pageable);

    void addProductInMachine(Integer machineId, Integer productId);

    void deleteProductInMachine(Integer machineId, Integer productId);
}
