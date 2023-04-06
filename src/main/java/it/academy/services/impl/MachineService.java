package it.academy.services.impl;

import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.MachineMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.mappers.impl.ProductMapper;
import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IMachineRepository;
import it.academy.repositories.impl.MachineRepository;
import it.academy.services.IMachineService;

import java.io.Serializable;
import java.util.List;

public class MachineService implements IMachineService {
    private final IMachineRepository repository
            = new MachineRepository();

    private final Mapper<Machine, MachineDto> machineMapper = new MachineMapper();

    private final Mapper<Product, ProductDto> productMapper = new ProductMapper();

    private final Mapper<Pageable<Machine>, Pageable<MachineDto>> mapperMachineP =
            new PageableMapper<>(machineMapper);

    @Override
    public void createMachine(MachineDto machineDto) {
        Machine machine = machineMapper.dtoToEntity(machineDto);
        machine = repository.save(machine);
        machineMapper.entityToDto(machine);
    }

    @Override
    public void updateMachine(MachineDto machineDto) {
        Machine machine = machineMapper.dtoToEntity(machineDto);
        machine = repository.update(machine);
        machineMapper.entityToDto(machine);
    }

    @Override
    public MachineDto findMachineById(Serializable id) {
        Machine machine = repository.getById(id);
        return machineMapper.entityToDto(machine);
    }

    @Override
    public void deleteMachineById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public List<MachineDto> findAllMachines() {
        List<Machine> machines = repository.getAll();
        return machines
                .stream()
                .map(machineMapper::entityToDto)
                .toList();
    }

    @Override
    public void addProductInMachine(Integer machineId, Integer productId) {
        repository.addProductInMachine(machineId, productId);
    }

    @Override
    public void deleteProductInMachine(Integer machineId, Integer productId) {
        repository.deleteProductInMachine(machineId, productId);
    }

    @Override
    public Pageable<MachineDto> getPageableRecords(Pageable<MachineDto> pageableDto) {
        Pageable<Machine> pageable = mapperMachineP.dtoToEntity(pageableDto);
        return mapperMachineP.entityToDto(repository.getPageableRecords(pageable));
    }
}
