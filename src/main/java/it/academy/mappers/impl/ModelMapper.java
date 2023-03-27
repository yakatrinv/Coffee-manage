package it.academy.mappers.impl;

import it.academy.dto.ModelDto;
import it.academy.mappers.Mapper;
import it.academy.models.Model;

public class ModelMapper implements Mapper<Model, ModelDto> {
    @Override
    public Model dtoToEntity(ModelDto modelDto) {
        return Model.builder()
                .id(modelDto.getId())
                .brand(modelDto.getBrand())
                .nameModel(modelDto.getNameModel())
                .build();
    }

    @Override
    public ModelDto entityToDto(Model model) {
        return ModelDto.builder()
                .id(model.getId())
                .brand(model.getBrand())
                .nameModel(model.getNameModel())
                .build();
    }
}
