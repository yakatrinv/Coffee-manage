package it.academy.repositories;

import it.academy.models.Machine;

public interface IMachineRepository extends ICrudRepository<Machine> {
    void addProductInMachine(Integer machineId, Integer productId);

    void deleteProductInMachine(Integer machineId, Integer productId);
}
