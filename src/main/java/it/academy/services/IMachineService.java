package it.academy.services;

import it.academy.dto.MachineDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IMachineService {
    void createMachine(MachineDto machineDto);

    void updateMachine(MachineDto machineDto);

    MachineDto findMachineById(Serializable id);

    void deleteMachineById(Serializable id);

    List<MachineDto> findAllMachines();

    void addProductInMachine(Integer machineId, Integer productId);

    void deleteProductInMachine(Integer machineId, Integer productId);

    Pageable<MachineDto> getPageableRecords(Pageable<MachineDto> pageable);
}
