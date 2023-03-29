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
import java.util.List;

public class ModelService implements IModelService {
    private final IModelRepository repository
            = new ModelRepository();

    private final Mapper<Model, ModelDto> mapper = new ModelMapper();

    private final Mapper<Pageable<Model>, Pageable<ModelDto>> mapperP =
            new PageableMapper<>(mapper);

    @Override
    public void createModel(ModelDto entityDto) {
        Model model = mapper.dtoToEntity(entityDto);
        model = repository.save(model);
        mapper.entityToDto(model);
    }

    @Override
    public void updateModel(ModelDto entityDto) {
        Model model = mapper.dtoToEntity(entityDto);
        model = repository.update(model);
        mapper.entityToDto(model);
    }

    @Override
    public ModelDto findModelById(Serializable id) {
        Model model = repository.getById(id);
        return mapper.entityToDto(model);
    }

    @Override
    public void deleteModelById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<ModelDto> getPageableRecords(Pageable<ModelDto> pageableDto) {
        Pageable<Model> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }

    @Override
    public List<ModelDto> findAllModels() {
        List<Model> models = repository.getAll();
        return models
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
