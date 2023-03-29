package it.academy.services;

import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IMachineService {
    void createMachine(MachineDto machineDto);

    void updateMachine(MachineDto machineDto);

    MachineDto findMachineById(Serializable id);

    void deleteMachineById(Serializable id);

    Pageable<MachineDto> getPageableRecords(Pageable<MachineDto> pageable);

    List<MachineDto> findAllMachines();

    Pageable<ProductDto> getProducts(Serializable id, Pageable<ProductDto> pageableDto);

    void addProductInMachine(Integer machineId, Integer productId);

    void deleteProductInMachine(Integer machineId, Integer productId);
}
