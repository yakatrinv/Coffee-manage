package it.academy.services.impl;

import it.academy.dto.MachineDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.MachineMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.models.Machine;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IMachineRepository;
import it.academy.repositories.impl.MachineRepository;
import it.academy.services.IMachineService;

import java.io.Serializable;

public class MachineService implements IMachineService {
    private final Mapper<Machine, MachineDto> mapper = new MachineMapper();

    private final Mapper<Pageable<Machine>, Pageable<MachineDto>> pageMapper =
            new PageableMapper<>(mapper);

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
}
