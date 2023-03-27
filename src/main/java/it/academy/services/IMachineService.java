package it.academy.services;

import it.academy.dto.MachineDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;

public interface IMachineService {
    MachineDto createMachine(MachineDto machineDto);

    MachineDto updateMachine(MachineDto machineDto);

    MachineDto findMachineById(Serializable id);

    void deleteMachineById(Serializable id);

    Pageable<MachineDto> getPageableRecords(Pageable<MachineDto> pageable);
}
