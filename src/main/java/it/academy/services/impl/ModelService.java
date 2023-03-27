package it.academy.services.impl;

import it.academy.dto.ModelDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.ModelMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.models.Model;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IModelRepository;
import it.academy.repositories.impl.ModelRepository;
import it.academy.services.IModelService;

import java.io.Serializable;

public class ModelService implements IModelService {
    private final Mapper<Model, ModelDto> mapper = new ModelMapper();

    private final Mapper<Pageable<Model>, Pageable<ModelDto>> pageMapper =
            new PageableMapper<>(mapper);

    private final IModelRepository repository
            = new ModelRepository();

    @Override
    public ModelDto createModel(ModelDto entityDto) {
        Model entity = mapper.dtoToEntity(entityDto);
        entity = repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public ModelDto updateModel(ModelDto entityDto) {
        Model entity = mapper.dtoToEntity(entityDto);
        entity = repository.update(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public ModelDto findModelById(Serializable id) {
        Model entity = repository.getById(id);
        return mapper.entityToDto(entity);
    }

    @Override
    public void deleteModelById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<ModelDto> getPageableRecords(Pageable<ModelDto> pageableDto) {
        Pageable<Model> pageable = pageMapper.dtoToEntity(pageableDto);
        return pageMapper.entityToDto(repository.getPageableRecords(pageable));
    }
}
