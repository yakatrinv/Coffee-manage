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
    private final Mapper<Machine, MachineDto> mapper = new MachineMapper();

    private final Mapper<Product, ProductDto> productMapper = new ProductMapper();

    private final Mapper<Pageable<Machine>, Pageable<MachineDto>> pageMapper =
            new PageableMapper<>(mapper);

    private final Mapper<Pageable<Product>, Pageable<ProductDto>> productPageMapper =
            new PageableMapper<>(productMapper);

    private final IMachineRepository repository
            = new MachineRepository();

    @Override
    public MachineDto createMachine(MachineDto entityDto) {
        Machine entity = mapper.dtoToEntity(entityDto);
        entity = repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public MachineDto updateMachine(MachineDto entityDto) {
        Machine entity = mapper.dtoToEntity(entityDto);
        entity = repository.update(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public MachineDto findMachineById(Serializable id) {
        Machine entity = repository.getById(id);
        return mapper.entityToDto(entity);
    }

    @Override
    public void deleteMachineById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<MachineDto> getPageableRecords(Pageable<MachineDto> pageableDto) {
        Pageable<Machine> pageable = pageMapper.dtoToEntity(pageableDto);
        return pageMapper.entityToDto(repository.getPageableRecords(pageable));
    }

    @Override
    public List<MachineDto> findAllMachines() {
        List<Machine> entities = repository.getAll();
        return entities
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public Pageable<ProductDto> getProducts(Serializable id, Pageable<ProductDto> pageableDto) {
        Pageable<Product> pageable = productPageMapper.dtoToEntity(pageableDto);
        return productPageMapper.entityToDto(repository.getProducts(id, pageable));
    }

    @Override
    public void addProductInMachine(Integer machineId, Integer productId) {
        repository.addProductInMachine(machineId,productId);
    }

    @Override
    public void deleteProductInMachine(Integer machineId, Integer productId) {
        repository.deleteProductInMachine(machineId,productId);
    }
}
